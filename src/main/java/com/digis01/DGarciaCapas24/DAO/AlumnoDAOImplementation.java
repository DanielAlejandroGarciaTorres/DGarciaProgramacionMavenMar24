/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaCapas24.DAO;

import com.digis01.DGarciaCapas24.ML.Alumno;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ALIEN 34
 */
@Repository
public class AlumnoDAOImplementation implements AlumnoDAO {

    private JdbcTemplate jdbcTemplate; // de encarga de recupera y/o enviar datos a nuestra bd

    @Autowired //inyección 
    public AlumnoDAOImplementation(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Alumno> GetAll() { // lo hagan con un sp getall
//        String query = "SELECT IdAlumno, Nombre FROM Alumno";
//        return jdbcTemplate.query(query, new AlumnoRowMapper());
        return jdbcTemplate.execute("{CALL AlumnoGetAll(?)}", (CallableStatementCallback<List<Alumno>>) callableStatement -> {
            callableStatement.registerOutParameter(1, Types.REF_CURSOR);
            callableStatement.execute();
            ResultSet rs = (ResultSet) callableStatement.getObject(1);
            List<Alumno> alumnos = new ArrayList<>();
            AlumnoRowMapper alumnoRowMapper = new AlumnoRowMapper();
            while (rs.next()) {
                alumnos.add(alumnoRowMapper.mapRow(rs, rs.getRow()));
            }
            return alumnos;
        });
    }

    @Override
    public Boolean Add(Alumno alumno) {
       //Logica para ADD
       //Query 
       return true;
    }

}
