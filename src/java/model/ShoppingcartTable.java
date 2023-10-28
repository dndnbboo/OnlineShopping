/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author User
 */
public class ShoppingcartTable {
    public static List<Shoppingcart> findAllShoppingcart() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingWebPU");
        EntityManager em = emf.createEntityManager();
        List<Shoppingcart> movList = null;
        try {
            movList = (List<Shoppingcart>) em.createNamedQuery("Shoppingcart.findAll").getResultList();         
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            em.close();
            emf.close();
        }
        return movList;
    }
    public static List<Shoppingcart> findShoppingcartById(ShoppingcartPK id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingWebPU");
        EntityManager em = emf.createEntityManager();
        List<Shoppingcart> cart = null;
        try {
            TypedQuery<Shoppingcart> query = em.createNamedQuery("Shoppingcart.findByCartId", Shoppingcart.class);
            query.setParameter("cartId", id.getCartId()); 
            cart = query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
            emf.close();
        }
        return cart;
    }
    public static int removeShoppingcart(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingWebPU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Shoppingcart target = em.find(Shoppingcart.class, id);
            if (target == null) {
                return 0;
            }
            em.remove(target);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            
        }
        finally {
            em.close();
            emf.close();
        }
        return 1;
    }
    public static int insertShoppingcart(Shoppingcart cart) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("OnlineShoppingWebPU");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Shoppingcart target = em.find(Shoppingcart.class, cart.getShoppingcartPK());
            if (target != null) {
                return 0;
            }
            em.persist(cart);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        finally {
            em.close();
            emf.close();
        }
        return 1;
    }
}
