/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaCapas24.JPA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 *
 * @author ALIEN 34
 */
@Entity
public class Colonia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcolonia")
    private int IdColonia;
    @Column(name = "nombre")
    private String Nombre;
    @ManyToOne
    @JoinColumn(name = "idmunicipio")
    public Municipio Municipio; // Propieddad de navegación

    public int getIdColonia() {
        return IdColonia;
    }

    public void setIdColonia(int IdColonia) {
        this.IdColonia = IdColonia;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    
}
