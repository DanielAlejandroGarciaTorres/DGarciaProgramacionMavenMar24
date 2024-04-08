/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaCapas24.Controller;

import com.digis01.DGarciaCapas24.DAO.AlumnoDAOImplementation;
import com.digis01.DGarciaCapas24.DAO.EstadoDAOImplementation;
import com.digis01.DGarciaCapas24.DAO.PaisDAOImplementation;
import com.digis01.DGarciaCapas24.DAO.RolDAOImplementation;
import com.digis01.DGarciaCapas24.ML.Alumno;
import com.digis01.DGarciaCapas24.ML.AlumnoDireccion;
import com.digis01.DGarciaCapas24.ML.Colonia;
import com.digis01.DGarciaCapas24.ML.Direccion;
import com.digis01.DGarciaCapas24.ML.Estado;
import com.digis01.DGarciaCapas24.ML.Pais;
import com.digis01.DGarciaCapas24.ML.Result;
import com.digis01.DGarciaCapas24.ML.Rol;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    private PaisDAOImplementation paisDaoImplentation;
    @Autowired
    private EstadoDAOImplementation estadoDAOImplementation;

    @GetMapping  //--Obtener/Mostrar una vista
    public String GetAll(Model model) {
//        Result result = alumnoDAOImplementation.GetAllSP();
        Result result = alumnoDAOImplementation.GetAllJPA();
        if (result.Correct) {
            model.addAttribute("alumnosDireccion", (List<AlumnoDireccion>) result.Object);
            return "AlumnoGetAll";
        } else {
            return "";
        }

    }

    //john carlos nancy mariano marco axel gerardo
    @GetMapping("/form/{idalumno}") //Muestra formulario vacio
    public String Form(@PathVariable int idalumno, Model model) {

        Result result = rolDAOImplementation.GetAll();
        model.addAttribute("roles", (List<Rol>) result.Object);
        model.addAttribute("paises", (List<Pais>) paisDaoImplentation.GetAll().Object);

//        model.addAttribute("roles", (List<Rol>) rolDAOImplementation.GetAll().Object);
        if (idalumno == 0) {
            AlumnoDireccion alumnoDireccion = new AlumnoDireccion();
            alumnoDireccion.Alumno = new Alumno();
            alumnoDireccion.Alumno.Rol = new Rol();
            alumnoDireccion.Direccion = new Direccion();
            alumnoDireccion.Direccion.Colonia = new Colonia();

            model.addAttribute("alumnoDireccion", alumnoDireccion);  //modelo vacio cuando hacemos un ADD
        } else {
            
            // Alumno alumnoRecuperado = alumnoDAOImplementation.GetById(idalumno);
            //model.addAttribute("alumno", alumnoRecuperado);
            AlumnoDireccion alumnoDireccion = (AlumnoDireccion) alumnoDAOImplementation.GetByIdJPA(idalumno).Object;
            model.addAttribute("alumnoDireccion", alumnoDireccion);
            model.addAttribute("Estados", (List<Estado>) estadoDAOImplementation.GetByPais(alumnoDireccion.Direccion.Colonia.Municipio.Estado.Pais.getIdPais()).Object);
        }

        return "Form";
    }

    @PostMapping("/form")  //Recuperar los datos de formulario
    public String Form(@ModelAttribute AlumnoDireccion alumnoDireccion, @RequestParam("imagenFile") MultipartFile imagenFile) {
        Result result;
       
        try {
            if(!imagenFile.isEmpty()) {
                byte[] bytes = imagenFile.getBytes();
                String imageBase64 = Base64.encodeBase64String(bytes);
                alumnoDireccion.Alumno.setImagen(imageBase64);
            }
        } catch (Exception ex) {
            
        }        
        if (alumnoDireccion.Alumno.getIdAlumno() == 0) { // INSERTAR DATOS
            //          alumnoDAOImplementation.Add(alumno);  //EL metodo de Add a la BD
//            result = alumnoDAOImplementation.AddSP(alumnoDireccion);
            result = alumnoDAOImplementation.AddJPA(alumnoDireccion);

        } else { //ACTUALIZACIÓN DE DATOS
            result = alumnoDAOImplementation.UpdateSP(alumnoDireccion);
//            alumnoDAOImplementation.Update(alumno);
        }

        return "AlumnoGetAll";
    }

    @GetMapping("/getEstadoByPais")
    @ResponseBody
    public List<Estado> GetEstadoBtPais(@RequestParam("IdPais") int IdPais){
        List<Estado> estados = (List<Estado>) estadoDAOImplementation.GetByPais(IdPais).Object;
        return estados;
    }
}
