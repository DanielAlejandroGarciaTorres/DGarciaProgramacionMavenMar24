/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaCapas24.Controller;

import com.digis01.DGarciaCapas24.DAO.AlumnoDAOImplementation;
import com.digis01.DGarciaCapas24.ML.Alumno;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    @GetMapping
    public String GetAll(Model model){
        List<Alumno> alumnos = alumnoDAOImplementation.GetAll();
        model.addAttribute("alumnos", alumnos);
        return "AlumnoGetAll";
    }
}
