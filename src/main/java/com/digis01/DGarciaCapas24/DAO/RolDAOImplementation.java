/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaCapas24.DAO;

import com.digis01.DGarciaCapas24.ML.Result;
import com.digis01.DGarciaCapas24.ML.Rol;
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
public class RolDAOImplementation  implements RolDAO{

    private JdbcTemplate jdbcTemplate; // de encarga de recupera y/o enviar datos a nuestra bd

    @Autowired //inyección 
    public RolDAOImplementation(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Result GetAll() {
        Result result = new Result();
        // sp - RolGetAll
        String query = "SELECT IdRol, Nombre FROM Rol";
        List<Rol> roles = jdbcTemplate.query(query, new RolRowMapper());
        
        result.Object = roles;
        
        return result;
    }
    
}
