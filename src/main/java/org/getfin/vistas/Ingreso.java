package org.getfin.vistas;

import jakarta.persistence.*;

@Entity
@Table(name = "Ingreso")
public class Ingreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIngreso;

    @Column(name = "nombreIngreso", nullable = false)
    private String nombreIngreso;
    private String diaria;
    private String mensual;
    private String cantidad;
    private String anual;


}
