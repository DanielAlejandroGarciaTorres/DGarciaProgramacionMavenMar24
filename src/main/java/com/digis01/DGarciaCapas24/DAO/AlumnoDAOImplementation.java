/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaCapas24.DAO;

import com.digis01.DGarciaCapas24.JPA.Alumno;
import com.digis01.DGarciaCapas24.ML.AlumnoDireccion;
import com.digis01.DGarciaCapas24.ML.Result;
import jakarta.persistence.EntityManager;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
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
    private EntityManager entityManager; // me permitetrabajar con JPA

    @Autowired //inyección 
    public AlumnoDAOImplementation(DataSource dataSource, EntityManager entityManager) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.entityManager = entityManager;
    }

    //    @Override
//    public Boolean Add(Alumno alumno) {
//        //Logica para ADD
//        //Query 
//        return true;
//    }
//    @Override
//    public Alumno GetById(int idAlumno) {
//        String query = "SELECT IdAlumno, Nombre, ApellidoPaterno, UserName, IdRol, FechaNacimiento FROM Alumno WHERE IdAlumno = " + idAlumno;
//        Alumno alumno = jdbcTemplate.queryForObject(query, new AlumnoRowMapper());
//        return alumno;
//    }
    @Override
    public Result GetAllSP() { // lo hagan con un sp getall
//        String query = "SELECT IdAlumno, Nombre FROM Alumno";
//        return jdbcTemplate.query(query, new AlumnoRowMapper());
        Result result = new Result();
        try {
            List<AlumnoDireccion> alumnosDirecciones = jdbcTemplate.execute("{CALL AlumnoGetAll(?)}", (CallableStatementCallback<List<AlumnoDireccion>>) callableStatement -> {
                callableStatement.registerOutParameter(1, Types.REF_CURSOR);
                callableStatement.execute();
                ResultSet rs = (ResultSet) callableStatement.getObject(1);
                List<AlumnoDireccion> alumnos = new ArrayList<>();
                AlumnoRowMapper alumnoRowMapper = new AlumnoRowMapper();
                while (rs.next()) {
                    alumnos.add(alumnoRowMapper.mapRow(rs, rs.getRow()));
                }
                return alumnos;
            });
            result.Object = alumnosDirecciones;
            result.Correct = true;
        } catch (Exception ex) {
            result.Correct = false;
            result.ErrorMessage = ex.getLocalizedMessage();
            result.Ex = ex;
        }
        return result;
    }

    @Override
    public Result AddSP(AlumnoDireccion alumnoDireccion) {
        Result result = new Result();

        try {
            int rowsAffected = jdbcTemplate.execute("{CALL AlumnoAdd(?, ?, ?, ?, ? , ?, ?, ?, ?, ? )}", (CallableStatementCallback<Integer>) callableStatement -> {
                callableStatement.setString("pNombre", alumnoDireccion.Alumno.getNombre());
                callableStatement.setString("pApellidoPaterno", alumnoDireccion.Alumno.getApellidoPaterno());
                callableStatement.setString("pUserName", alumnoDireccion.Alumno.getUserName());
                callableStatement.setInt("pIdRol", alumnoDireccion.Alumno.Rol.getIdRol());
                java.sql.Date fechaNacimientoSql = new java.sql.Date(alumnoDireccion.Alumno.getFechaNacimiento().getTime());
                callableStatement.setDate("pFechaNacimiento", fechaNacimientoSql);
                callableStatement.setString("pCalle", alumnoDireccion.Direccion.getCalle());
                callableStatement.setString("pNumeroInterior", alumnoDireccion.Direccion.getNumeroInterior());
                callableStatement.setString("pNumeroExterior", alumnoDireccion.Direccion.getNumeroExterior());
                callableStatement.setInt("pIdColonia", alumnoDireccion.Direccion.Colonia.getIdColonia());
                callableStatement.registerOutParameter("IdRecuperado", Types.NUMERIC);

                callableStatement.execute(); // ejecuta mi query
                return callableStatement.getUpdateCount(); // 
            });
            
            if(rowsAffected != 0) {
                result.Correct = true;
            }

        } catch (Exception ex) {
            result.Correct = false;
            result.ErrorMessage = ex.getLocalizedMessage();
            result.Ex = ex;
        }
        return result;
    }

    @Override
    public Result UpdateSP(AlumnoDireccion alumnoDireccion) {
        Result result = new Result();

        try {
            int rowsAffected = jdbcTemplate.execute("{CALL AlumnoUpdate(?, ?, ?, ?, ? , ?, ?, ?, ?, ? )}", (CallableStatementCallback<Integer>) callableStatement -> {
                callableStatement.setInt("pIdAlumno", alumnoDireccion.Alumno.getIdAlumno());
                callableStatement.setString("pNombre", alumnoDireccion.Alumno.getNombre());
                callableStatement.setString("pApellidoPaterno", alumnoDireccion.Alumno.getApellidoPaterno());
                callableStatement.setString("pUserName", alumnoDireccion.Alumno.getUserName());
                callableStatement.setInt("pIdRol", alumnoDireccion.Alumno.Rol.getIdRol());
                java.sql.Date fechaNacimientoSql = new java.sql.Date(alumnoDireccion.Alumno.getFechaNacimiento().getTime());
                callableStatement.setDate("pFechaNacimiento", fechaNacimientoSql);
                callableStatement.setString("pCalle", alumnoDireccion.Direccion.getCalle());
                callableStatement.setString("pNumeroInterior", alumnoDireccion.Direccion.getNumeroInterior());
                callableStatement.setString("pNumeroExterior", alumnoDireccion.Direccion.getNumeroExterior());
                callableStatement.setInt("pIdColonia", alumnoDireccion.Direccion.Colonia.getIdColonia());

                callableStatement.execute(); // ejecuta mi query
                return callableStatement.getUpdateCount(); // 
            });
            
            if(rowsAffected != 0) {
                result.Correct = true;
            }

        } catch (Exception ex) {
            result.Correct = false;
            result.ErrorMessage = ex.getLocalizedMessage();
            result.Ex = ex;
        }
        return result;
    }

    @Override
    public Result GetByIdSP(int idAlumno) {
        Result result = new Result();
        try {
            AlumnoDireccion alumnoDireccion = jdbcTemplate.execute("{CALL AlumnoGetById(?,?)}", (CallableStatementCallback<AlumnoDireccion>) callableStatement -> {
                callableStatement.setInt("pIdAlumno", idAlumno);
                callableStatement.registerOutParameter("datosCursor", Types.REF_CURSOR);
                callableStatement.execute();
                ResultSet rs = (ResultSet) callableStatement.getObject("datosCursor");
                AlumnoRowMapper alumnoRowMapper = new AlumnoRowMapper();
                if (rs.next()) {
                    return alumnoRowMapper.mapRow(rs, rs.getRow());
                } else {
                    return null;
                }
            });
            result.Object = alumnoDireccion;
            result.Correct = true;
        } catch (Exception ex) {
            result.Correct = false;
            result.ErrorMessage = ex.getLocalizedMessage();
            result.Ex = ex;
        }
        return result;
    }

    @Override
    public Result GetAllJPA() {
        //JPQL
        Result result = new Result();
        List<Alumno> alumnos = entityManager.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();
        Date fecha = alumnos.get(0).getFechaNacimiento();
        result.Object = alumnos;
        return result;
    }

}
