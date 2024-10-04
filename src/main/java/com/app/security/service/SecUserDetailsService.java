package com.app.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.excepciones.ExcepcionPersonalizada;
import com.app.security.controller.dto.AuthCreateUserRequest;
import com.app.security.controller.dto.AuthLoginRequest;
import com.app.security.controller.dto.AuthResponse;
import com.app.security.entity.Rol;
import com.app.security.entity.RolEnum;
import com.app.security.entity.Usuario;
import com.app.security.repository.RolRepository;
import com.app.security.repository.UsuarioRepository;
import com.app.security.util.JwtUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SecUserDetailsService implements IUsuarioService{

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RolRepository rolRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optional = usuarioRepository.findUsuarioByUsername(username);
        if (!optional.isPresent()) {
            throw new ExcepcionPersonalizada("El usuario no existe!");
        }

        //Creamos un objeto usuario de nuestra entidad que almacene 
        Usuario usuario = optional.get();

        //Antes de crear el User de UserDetails, creamos un arreglo de los roles
        Set<GrantedAuthority> authorities = new HashSet<>();
        //y asignamos cada rol al arreglo Set
        usuario.getRoles().forEach(rol -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(rol.getNombre().name()))));

        //Creamos el User de la clase UserDetails
        return new User(usuario.getUsername(), usuario.getPassword(), authorities);
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {

        Set<Rol> listaRoles = new HashSet<>();

        usuario.getRoles().forEach(role -> {
            //Se crea una instancia de Rol donde se valida y almacena un Rol existente o NO
            Rol existingRol = rolRepository.findRolByNombre(role.getNombre());
            if (existingRol != null) {
                //Si un rol ya existe, se utiliza la referencia del rol existente de la base de datos
                listaRoles.add(existingRol);
            } else {
                //Si un rol no existe, se agrega la instancia del rol proporcionado por el usuario
                listaRoles.add(role);
            }
        });

        Usuario usuarioNuevo = Usuario.builder()
                        .username(usuario.getUsername())
                        .password(encoder.encode(usuario.getPassword()))
                        //Aquí se crean los roles
                        .roles(listaRoles)
                        .build();
        return usuarioRepository.save(usuarioNuevo);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest){
        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse(username, "User logged successfully", accessToken,true);

        return authResponse;
    }

    public Authentication authenticate(String username, String password){
        UserDetails userDetails = this.loadUserByUsername(username);
        if(userDetails == null){
            throw new BadCredentialsException("Invalid username or password");
        }
        if (!encoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public AuthResponse createUser(AuthCreateUserRequest authCreateUserRequest){
        // Extraemos los datos que se requiere
        String username = authCreateUserRequest.username();
        String password = authCreateUserRequest.password();
        List<String> rolesSolicitud = authCreateUserRequest.roleRequest().roleListName();

        //Aqui se almacenarán los roles
        Set<Rol> roles = new HashSet<>();
        
        //Recorremos los roles que se encuentran en la lista
        rolesSolicitud.forEach(r -> {
            try {
                // Convertimos String a RolEnum
                RolEnum rolEnum = RolEnum.valueOf(r);
        
                // Verificando si el rol existe en el repositorio
                Rol existing = rolRepository.findRolByNombre(rolEnum);
                
                // Si el rol existe, lo añadimos; si no, creamos uno nuevo
                if (existing != null) {
                    roles.add(existing);
                } else {
                    Rol rolAux = Rol.builder().nombre(rolEnum).build();
                    roles.add(rolAux);
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        });

        // En caso la lista esté vacía, nos retorna una excepción
        if(roles.isEmpty()){
            throw new IllegalArgumentException("Los roles especificados no existen");
        }

        Usuario usuario = Usuario.builder()
                            .username(username)
                            .password(encoder.encode(password))
                            .roles(roles).build();
        
        Usuario usuarioGuardar = usuarioRepository.save(usuario);

        ArrayList<SimpleGrantedAuthority> rolesAuthorities = new ArrayList<>();

        usuarioGuardar.getRoles()
        .forEach(rol -> rolesAuthorities.add(new SimpleGrantedAuthority("ROLE_".concat(rol.getNombre().name()))));

        Authentication authentication = new UsernamePasswordAuthenticationToken(usuarioGuardar, null, rolesAuthorities);

        String tokenAcceso = jwtUtils.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse(username, "User created successfully", tokenAcceso, true);
        return authResponse;
    }
}
