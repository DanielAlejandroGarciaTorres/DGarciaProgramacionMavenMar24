/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaCapas24.RestController;

import com.digis01.DGarciaCapas24.ML.Alumno;
import com.digis01.DGarciaCapas24.ML.Rol;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ALIEN 34
 */
@RestController
@RequestMapping("/api")
public class DemoRestController {
    
    // redireccion - eduardo doriam mariano gerardo axel 
    // escribe el texto - carlos, nancy
    // documento xml - john 
    // error - marco 
    
    //http://localhost:8080/api/saludo -- endpoint
    @GetMapping("/saludo")
    public String Saludar(){
        return "AlumnoGetAll";
    }
    
    //jackson
    
    @GetMapping("/saludo2")
    public Map<String, Object> Saludar2(){
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hola desde REST");
        
        return response;
        
    }
    
    
    @PostMapping("/saludo3")
    public Alumno Saludar3(@RequestBody Alumno alumno){
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Hola " + alumno.getNombre());
        
        return alumno;
        
    }
    
    @GetMapping("/rol")
    public Rol mostraRol(){
        Rol rol = new Rol();
        rol.setIdRol(5);
        rol.setNombre("Gerente");
                
        return rol;
    }
    
    
    // Generar un hola mundo
    // Generar un hola mundo con saludo dedicado
    // Suma (NumeroUno, NumeroDos) -> retorar con Map
    // Resta ()
    // Multiplicacion (Objeto ) -> Objeto {"numeroUno", "numeroDos", "resultado"}
    // Division
    
    
}
