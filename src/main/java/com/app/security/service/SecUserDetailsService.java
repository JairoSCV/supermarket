package com.app.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.excepciones.ExcepcionPersonalizada;
import com.app.security.entity.Rol;
import com.app.security.entity.Usuario;
import com.app.security.repository.RolRepository;
import com.app.security.repository.UsuarioRepository;

import java.util.*;

@Service
public class SecUserDetailsService implements IUsuarioService{

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

        for (Rol rol : usuario.getRoles()) {
            Rol existingRol = rolRepository.findRolByNombre(rol.getNombre());
            if (existingRol != null) {
                listaRoles.add(existingRol);
            } else {
                listaRoles.add(rol);
            }
        }

        Usuario usuarioNuevo = Usuario.builder()
                        .username(usuario.getUsername())
                        .password(encoder.encode(usuario.getPassword()))
                        //Aquí se crean los roles
                        .roles(listaRoles)
                        .build();
        return usuarioRepository.save(usuarioNuevo);
    }

}
