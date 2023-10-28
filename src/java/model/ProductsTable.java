/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author User
 */
public class ProductsTable {
    public static List<Products> findAllProducts() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingWebPU");
        EntityManager em = emf.createEntityManager();
        List<Products> pdList = null;
        try {
            pdList = (List<Products>) em.createNamedQuery("Products.findAll").getResultList();         
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            em.close();
            emf.close();
        }
        return pdList;
    }
    
    public static Products findProductsById(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingWebPU");
        EntityManager em = emf.createEntityManager();
        
        try {
            return em.find(Products.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
            emf.close();
        }
    }
}
