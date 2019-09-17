/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author cc08779599940
 */
public class JPAUtil {
    private static final EntityManagerFactory FACTORY;
    
    static{ 
        FACTORY = Persistence.createEntityManagerFactory("HospitalPU");
    }
    
    public static EntityManager getEntityManager(){
        return FACTORY.createEntityManager();
    }
}
