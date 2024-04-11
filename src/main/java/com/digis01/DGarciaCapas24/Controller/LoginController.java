/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaCapas24.Controller;

import com.digis01.DGarciaCapas24.ML.Alumno;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ALIEN 34
 */
@Controller
@RequestMapping("/")
public class LoginController {
    
    @GetMapping
    public String Login(Model model){
        model.addAttribute("alumno", new Alumno());
        return "Login";
    }
    
    @PostMapping
    public String Login(){ // ModelAttribute, Model
        //DB
        // result -- GetByEmail //GetByUsername  -- retorna un elemento 
        
        //SI  result.Correct 
            // redirecciono
        // SINO 
            // model - modelattribute
            // model - mensajeError
        return "";
    }
}
