/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaCapas24.ML;

/**
 *
 * @author ALIEN 34
 */
public class Direccion {
    private int IdDIreccion;
    private String Calle;
    public Colonia Colonia; // Propiedad de navegación
    public Alumno alumno;

    public int getIdDIreccion() {
        return IdDIreccion;
    }

    public void setIdDIreccion(int IdDIreccion) {
        this.IdDIreccion = IdDIreccion;
    }

    public String getCalle() {
        return Calle;
    }

    public void setCalle(String Calle) {
        this.Calle = Calle;
    }
    
    
}
