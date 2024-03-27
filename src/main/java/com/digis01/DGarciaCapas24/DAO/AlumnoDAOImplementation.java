/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaCapas24.DAO;

import com.digis01.DGarciaCapas24.ML.Alumno;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ALIEN 34
 */
@Repository
public class AlumnoDAOImplementation implements AlumnoDAO{
    
    private JdbcTemplate jdbcTemplate; // de encarga de recupera y/o enviar datos a nuestra bd
    
    @Autowired //inyección 
    public AlumnoDAOImplementation(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    
    @Override
    public List<Alumno> GetAll() {
        String query = "SELECT IdAlumno, Nombre FROM Alumno";
        return jdbcTemplate.query(query, new AlumnoRowMapper());
    }

    
}
