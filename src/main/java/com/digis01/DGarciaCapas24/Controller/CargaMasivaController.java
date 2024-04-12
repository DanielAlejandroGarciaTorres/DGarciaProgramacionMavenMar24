/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaCapas24.Controller;

import com.digis01.DGarciaCapas24.ML.Alumno;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ALIEN 34
 */
@Controller
@RequestMapping("/cargaMasiva")
public class CargaMasivaController {
    
    @GetMapping
    public String Carga(){
        return "CargaMasiva";
    }
    
    @PostMapping("/txt")
    public String CargaMasivaTxt(@RequestParam MultipartFile archivoTxt) throws IOException{
        InputStream inputStream = archivoTxt.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        //Wrapper
        String linea;
        
        while((linea = br.readLine()) != null){
            //Nombre|ApellidoPaterno|UserName|IdRol|Fecha
            String[] campos =  linea.split("\\|");
            Alumno alumno = new Alumno();
            alumno.setNombre(campos[0]);
            alumno.setApellidoPaterno(campos[1]);
            alumno.setUserName(campos[2]);
            alumno.Rol.setIdRol(Integer.parseInt(campos[3]));
            //Lamar a mi metodo JPA Add
        }
        
        return "";
    }
   
    @PostMapping("/excel")
    public String CargaMasivaExcel(){
        return "";
    }
}
