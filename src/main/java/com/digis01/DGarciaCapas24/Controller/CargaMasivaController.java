/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaCapas24.Controller;

import com.digis01.DGarciaCapas24.ML.Alumno;
import com.digis01.DGarciaCapas24.ML.ResultExcel;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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
    public String Carga() {
        return "CargaMasiva";
    }

    @PostMapping("/txt")
    public String CargaMasivaTxt(@RequestParam MultipartFile archivoTxt) throws IOException {
        InputStream inputStream = archivoTxt.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        //Wrapper
        String linea;

        while ((linea = br.readLine()) != null) {
            //Nombre|ApellidoPaterno|UserName|IdRol|Fecha
            String[] campos = linea.split("\\|");
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
    public String CargaMasivaExcel(@RequestParam MultipartFile archivoExcel) throws IOException {
        if (archivoExcel != null && !archivoExcel.isEmpty()) {
            String extension = StringUtils.getFilenameExtension(archivoExcel.getOriginalFilename());
            if (extension.equals("xlsx")) {
                String root = System.getProperty("user.dir");
                String path = "src/main/resources/static/archivos/";
                String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
                String fileName = archivoExcel.getOriginalFilename();
                String absolutePath = root + "/" + path + fecha + fileName;
                archivoExcel.transferTo(new File(absolutePath));
                List<Alumno> alumnos = LecturaArchivo(archivoExcel);
                if(!alumnos.isEmpty()){
                    List<ResultExcel> resultExcel = ValidarExcel(alumnos); 
                }
                
            } else {
                return null; // no fue la misma extensión
            }
        } else {
            return null; // no hay archivo o el archivo esta vacio
        }
        return "";
    }
    

    public List<Alumno> LecturaArchivo(MultipartFile archivoExcel) throws IOException {
        List<Alumno> alumnos = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(archivoExcel.getInputStream());
        Sheet workSheet = workbook.getSheetAt(0);
        for (Row row : workSheet) {
            Alumno alumno = new Alumno();
            alumno.setNombre(row.getCell(0).toString());

            alumnos.add(alumno);
        }
        
        workbook.close();
        return alumnos;

    }
    
    public List<ResultExcel> ValidarExcel(List<Alumno> alumnos){
        
        List<ResultExcel> errores = new ArrayList<>();
        int fila = 1;
        String errorMessage = "";

        for (Alumno alumno : alumnos) {
            if(alumno.getNombre().equals("")){
                errorMessage += "Nombre sin información";
            }
            // apellido
            // username
            errores.add(new ResultExcel(fila, errorMessage));
            errorMessage = "";
            fila++;
        }
        
        return errores;
    }
}
