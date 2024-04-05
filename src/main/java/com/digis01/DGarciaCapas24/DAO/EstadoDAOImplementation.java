/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaCapas24.DAO;

import com.digis01.DGarciaCapas24.ML.Estado;
import com.digis01.DGarciaCapas24.ML.Result;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ALIEN 34
 */
@Repository
public class EstadoDAOImplementation implements EstadoDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Result GetByPais(int idPais) {
        Result result = new Result();
        TypedQuery<com.digis01.DGarciaCapas24.JPA.Estado> query = entityManager.createQuery("FROM Estado WHERE Pais.IdPais =:idPais", com.digis01.DGarciaCapas24.JPA.Estado.class);
        query.setParameter("idPais", idPais);
        List<com.digis01.DGarciaCapas24.JPA.Estado> estadosJPA = query.getResultList();
        
        List<Estado> estados = new ArrayList<>();
        
        for (com.digis01.DGarciaCapas24.JPA.Estado estadoJPA : estadosJPA) {
            Estado estado = new Estado();
            estado.setIdEstado(estadoJPA.getIdEstado());
            estado.setNombre(estadoJPA.getNombre());
            estados.add(estado);
        }
        
        result.Object = estados;
        
        
        return result;
    }
    
    

}
