/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaCapas24.Controller;

import com.digis01.DGarciaCapas24.DAO.AlumnoDAOImplementation;
import com.digis01.DGarciaCapas24.ML.Alumno;
import com.digis01.DGarciaCapas24.ML.Rol;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ALIEN 34
 */
@Controller
@RequestMapping("/alumno")
public class AlumnoController {
    
    @Autowired
    private AlumnoDAOImplementation alumnoDAOImplementation;

    
    @GetMapping  //--Obtener/Mostrar una vista
    public String GetAll(Model model){
        List<Alumno> alumnos = alumnoDAOImplementation.GetAll();
        model.addAttribute("alumnos", alumnos);
        return "AlumnoGetAll";
    }
    
    //john carlos nancy mariano marco axel gerardo
    @GetMapping("/form/{idalumno}") //Muestra formulario vacio
    public String Form(@PathVariable int idalumno, Model model){
        
        if (idalumno == 0) {
            Alumno alumno = new Alumno();
            alumno.Rol  = new Rol();
            model.addAttribute("alumno", alumno);  //modelo vacio cuando hacemos un ADD
        } else {
            Alumno alumnoRecuperado = alumnoDAOImplementation.GetById(idalumno);
            model.addAttribute("alumno", alumnoRecuperado);
        }       
        
        return "Form";
    }
    
    @PostMapping("/form")  //Recuperar los datos de formulario
    public String Form(@ModelAttribute Alumno alumno){
        if (alumno.getIdAlumno() == 0) { // INSERTAR DATOS
            alumnoDAOImplementation.Add(alumno);  //EL metodo de Add a la BD
        } else { //ACTUALIZACIÓN DE DATOS
//            alumnoDAOImplementation.Update(alumno);
        }
        
        
        return "AlumnoGetAll";
    }
    
}
