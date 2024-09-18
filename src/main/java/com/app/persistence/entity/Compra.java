package com.app.persistence.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "compras")
public class Compra implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Cliente.class)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
    @Column(columnDefinition = "DATE")
    private LocalDate fecha;
    @Column(name = "medio_pago")
    private String medioPago;
    private String comentario;
    private Boolean state;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<ComprasProducto> comprasProductos;
}
