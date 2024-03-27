/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaCapas24.DAO;

import com.digis01.DGarciaCapas24.ML.Alumno;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author ALIEN 34
 */
public class AlumnoRowMapper implements RowMapper<Alumno> {


    @Override
    public Alumno mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        Alumno alumno = new Alumno();
        alumno.setIdAlumno(rs.getInt("IdAlumno"));
        alumno.setNombre(rs.getString("Nombre"));
        
        return alumno;
    }
    
}
