/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ad.JpaUtil;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author Carlos
 */
public class JpaUtil {
    
    private static final String UNIDAD_PERSISTENCIA = "com.ad_Moro_Lopez_Jaime_Tarea4_2_jar_1.0PU";
    
    private static EntityManagerFactory factory;
    
    public static EntityManagerFactory getEntityManagerFactory(){
        if(factory == null){
            try {
                factory = Persistence.createEntityManagerFactory(UNIDAD_PERSISTENCIA);
            } catch (Throwable ex) {
                System.err.println("Fallo al crear EntityManagerFactory" + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        
        return factory;
    }
    
    public static void shutdown(){
        if (factory != null){
            factory.close();
        }
    }
    
    
}
