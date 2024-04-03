/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaCapas24.Controller;

import com.digis01.DGarciaCapas24.DAO.AlumnoDAOImplementation;
import com.digis01.DGarciaCapas24.DAO.RolDAOImplementation;
import com.digis01.DGarciaCapas24.ML.Alumno;
import com.digis01.DGarciaCapas24.ML.AlumnoDireccion;
import com.digis01.DGarciaCapas24.ML.Colonia;
import com.digis01.DGarciaCapas24.ML.Direccion;
import com.digis01.DGarciaCapas24.ML.Result;
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
    @Autowired 
    private RolDAOImplementation rolDAOImplementation;

    
    @GetMapping  //--Obtener/Mostrar una vista
    public String GetAll(Model model){
        Result result = alumnoDAOImplementation.GetAllSP();
        if (result.Correct) {
            model.addAttribute("alumnosDireccion", (List<AlumnoDireccion>) result.Object);
            return "AlumnoGetAll";
        } else {
            return "";
        }
        
    }
    
    //john carlos nancy mariano marco axel gerardo
    @GetMapping("/form/{idalumno}") //Muestra formulario vacio
    public String Form(@PathVariable int idalumno, Model model){
        
        Result result = rolDAOImplementation.GetAll();
        model.addAttribute("roles", (List<Rol>) result.Object);
        
//        model.addAttribute("roles", (List<Rol>) rolDAOImplementation.GetAll().Object);

        
        if (idalumno == 0) {
            AlumnoDireccion alumnoDireccion = new AlumnoDireccion();
            alumnoDireccion.Alumno = new Alumno();
            alumnoDireccion.Alumno.Rol  = new Rol();
            alumnoDireccion.Direccion = new Direccion();
            alumnoDireccion.Direccion.Colonia = new Colonia();
            
            model.addAttribute("alumnoDireccion", alumnoDireccion);  //modelo vacio cuando hacemos un ADD
        } else {
           // Alumno alumnoRecuperado = alumnoDAOImplementation.GetById(idalumno);
            //model.addAttribute("alumno", alumnoRecuperado);
        }       
        
        return "Form";
    }
    
    @PostMapping("/form")  //Recuperar los datos de formulario
    public String Form(@ModelAttribute AlumnoDireccion alumnoDireccion){
        Result result;
        if (alumnoDireccion.Alumno.getIdAlumno() == 0) { // INSERTAR DATOS
  //          alumnoDAOImplementation.Add(alumno);  //EL metodo de Add a la BD
                result = alumnoDAOImplementation.AddSP(alumnoDireccion);
            
        } else { //ACTUALIZACIÓN DE DATOS
//            alumnoDAOImplementation.Update(alumno);
        }
        
        
        return "AlumnoGetAll";
    }
    
}
