/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.digis01.DGarciaCapas24.DAO;

import com.digis01.DGarciaCapas24.ML.AlumnoDireccion;
import com.digis01.DGarciaCapas24.ML.Result;
import java.util.List;

/**
 *
 * @author ALIEN 34
 */
public interface AlumnoDAO { // Contrato
    
//    List<Alumno> GetAll(); // Metodo abstracto
//    Boolean Add(Alumno alumno);
//    Alumno GetById(int idAlumno);
    // todo lo de sp trabja alumno dirección
    Result GetAllSP();
    Result AddSP(AlumnoDireccion alumnoDirccion);
    Result UpdateSP(AlumnoDireccion alumnoDireccion);
    Result GetByIdSP(int idAlumno);
    
    Result GetAllJPA();
    Result AddJPA(AlumnoDireccion alumnoDireccion);
    Result UpdateJPA(AlumnoDireccion alumnoDireccion);
    Result GetByIdJPA(int idAlumno);
}
