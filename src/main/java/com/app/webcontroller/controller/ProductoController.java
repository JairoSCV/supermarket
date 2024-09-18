package com.app.webcontroller.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.domain.dto.CategoriaDTO;
import com.app.domain.dto.ProductoDTO;
import com.app.domain.services.IProductoService;
import com.app.persistence.entity.Categoria;
import com.app.persistence.entity.Producto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/productos")
@Tag(
    name = "Controlador de Productos"
)
public class ProductoController {
    @Autowired
    private IProductoService productoService;

    @GetMapping("/findAll")
    @Operation(
        summary = "Listar todos los productos",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successful request"
            )
        }
    )
    public ResponseEntity<List<ProductoDTO>> getAll(){
        List<ProductoDTO> productoDTOs = productoService.getAll().stream()
        .map(product -> ProductoDTO.builder()
        .id(product.getId())
        .name(product.getName())
        .precioVenta(product.getPrecioVenta())
        .stock(product.getStock())
        .state(product.getState())
        .codigoBarras(product.getCodigoBarras())
        .categoriaDto(CategoriaDTO.builder()
            .id(product.getCategoria().getId())
            .description(product.getCategoria().getDescription())
            .state(product.getCategoria().getState())
            .build())
        .build()).toList();
        return new ResponseEntity<>(productoDTOs, HttpStatus.OK);
    }

    @GetMapping("/categoria/{id}")
    @Operation(
        summary = "Buscar un producto específico",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successful request"
            ),
            @ApiResponse(
                responseCode = "403",
                description = "Forbidden"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Product not found"
            )
        }
    )
    public ResponseEntity<List<ProductoDTO>> findByCategory(@PathVariable Long id){
        List<ProductoDTO> productoDTOs = productoService.findByIdCategory(id).stream()
        .map(product -> ProductoDTO.builder()
        .id(product.getId())
        .name(product.getName())
        .precioVenta(product.getPrecioVenta())
        .stock(product.getStock())
        .state(product.getState())
        .codigoBarras(product.getCodigoBarras())
        .categoriaDto(CategoriaDTO.builder()
            .id(product.getCategoria().getId())
            .description(product.getCategoria().getDescription())
            .state(product.getCategoria().getState())
            .build())
        .build()).toList();

        return new ResponseEntity<List<ProductoDTO>>(productoDTOs, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ProductoDTO> findProduct(@PathVariable Long id){
        Optional<Producto> optional = productoService.findById(id);
        if (optional.isPresent()){
            Producto product = optional.get();
            ProductoDTO productoDTO = ProductoDTO.builder()
                                    .id(product.getId())
                                    .name(product.getName())
                                    .precioVenta(product.getPrecioVenta())
                                    .stock(product.getStock())
                                    .state(product.getState())
                                    .codigoBarras(product.getCodigoBarras())
                                    .categoriaDto(CategoriaDTO.builder()
                                        .id(product.getCategoria().getId())
                                        .description(product.getCategoria().getDescription())
                                        .state(product.getCategoria().getState())
                                        .build())
                                    .build();

            return new ResponseEntity<>(productoDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<Producto> save(@RequestBody ProductoDTO productoDTO){
        Producto producto = Producto.builder()
                        .name(productoDTO.getName())
                        .precioVenta(productoDTO.getPrecioVenta())
                        .stock(productoDTO.getStock())
                        .state(productoDTO.getState())
                        .codigoBarras(productoDTO.getCodigoBarras())
                        .categoria(Categoria.builder()
                                .id(productoDTO.getCategoriaDto().getId())
                                .build())
                        .build();

        productoService.save(producto);

        return new ResponseEntity<Producto>(producto, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if (productoService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
