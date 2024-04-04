/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaCapas24.DAO;

import com.digis01.DGarciaCapas24.ML.Alumno;
import com.digis01.DGarciaCapas24.ML.AlumnoDireccion;
import com.digis01.DGarciaCapas24.ML.Colonia;
import com.digis01.DGarciaCapas24.ML.Direccion;
import com.digis01.DGarciaCapas24.ML.Rol;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
/**
 *
 * @author ALIEN 34
 */
public class AlumnoRowMapper implements RowMapper<AlumnoDireccion> {


    @Override
    public AlumnoDireccion mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        AlumnoDireccion alumnoDireccion = new AlumnoDireccion();
        alumnoDireccion.Alumno = new Alumno();
        alumnoDireccion.Alumno.setIdAlumno(rs.getInt("IdAlumno"));
        alumnoDireccion.Alumno.setNombre(rs.getString("Nombre"));
        alumnoDireccion.Alumno.setApellidoPaterno(rs.getString("ApellidoPaterno"));
        alumnoDireccion.Alumno.setUserName(rs.getString("UserName"));
        alumnoDireccion.Alumno.Rol = new Rol();
        alumnoDireccion.Alumno.Rol.setIdRol(rs.getInt("IdRol"));
        alumnoDireccion.Alumno.setFechaNacimiento(rs.getDate("FechaNacimiento"));
        alumnoDireccion.Direccion = new Direccion();
        alumnoDireccion.Direccion.setCalle(rs.getString("Calle"));
        alumnoDireccion.Direccion.setNumeroInterior(rs.getString("NumeroInterior"));
        alumnoDireccion.Direccion.setNumeroExterior(rs.getString("NumeroExterior"));
        alumnoDireccion.Direccion.Colonia = new Colonia();
        alumnoDireccion.Direccion.Colonia.setIdColonia(rs.getInt("IdColonia"));
        
        
        return alumnoDireccion;
    }
    
}
