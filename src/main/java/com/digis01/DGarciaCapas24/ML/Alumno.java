/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaCapas24.ML;

/**
 *
 * @author ALIEN 34
 */
public class Alumno {
    
    private int IdAlumno; // propiedad
    private String Nombre;
    private String ApellidoPaterno;
    private String UserName;
    

    
    public Alumno(){
        
    }
    
    public Alumno(String Nombre, String ApellidoPaterno) {
        this.Nombre = Nombre;
        this.ApellidoPaterno = ApellidoPaterno;
    }
    
    public Alumno(int IdAlumno, String Nombre, String Apellido) {
        this.IdAlumno = IdAlumno;
        this.Nombre = Nombre;
        this.ApellidoPaterno = Apellido;
    }
    
    public int getIdAlumno() {
        return IdAlumno;
    }

    public void setIdAlumno(int IdAlumno) {
        this.IdAlumno = IdAlumno;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return ApellidoPaterno;
    }

    public void setApellido(String Apellido) {
        this.ApellidoPaterno = Apellido;
    }

    public String getApellidoPaterno() {
        return ApellidoPaterno;
    }

    public void setApellidoPaterno(String ApellidoPaterno) {
        this.ApellidoPaterno = ApellidoPaterno;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    
    
    
}
