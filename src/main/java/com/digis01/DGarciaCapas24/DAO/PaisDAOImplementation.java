/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.digis01.DGarciaCapas24.DAO;

import com.digis01.DGarciaCapas24.ML.Pais;
import com.digis01.DGarciaCapas24.ML.Result;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ALIEN 34
 */
@Repository
public class PaisDAOImplementation implements PaisDAO{

    @Autowired
    private EntityManager entityManager;
    
    @Override
    public Result GetAll() {
        
        Result result = new Result();
        
        List<com.digis01.DGarciaCapas24.JPA.Pais> paisesJPA = entityManager.createQuery("FROM Pais", com.digis01.DGarciaCapas24.JPA.Pais.class).getResultList();
        List<Pais> paises  = new ArrayList<>();
        
        for (com.digis01.DGarciaCapas24.JPA.Pais paisJPA : paisesJPA) {
            Pais pais = new Pais();
            pais.setIdPais(paisJPA.getIdPais());
            pais.setNombre(paisJPA.getNombre());
            paises.add(pais);
        }
        result.Object = paises;
        
        return result;
    }
    
}
