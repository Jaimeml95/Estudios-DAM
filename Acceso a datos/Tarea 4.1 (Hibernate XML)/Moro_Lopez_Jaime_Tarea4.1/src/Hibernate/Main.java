/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hibernate;

import Ficheros.NewHibernateUtil; 
import Modelos.Curso; 
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {

    public static void main(String[] args) {
        
        
        SessionFactory sf = NewHibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

       
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ==="); 
            System.out.println("1. Identificador del curso"); 
            System.out.println("2. Nombre de curso"); 
            System.out.println("0. Salir"); 
            System.out.print("Elige una opción: "); 
            
           
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                sc.nextLine(); 
            } else {
                System.out.println("Error: Introduce un número válido.");
                sc.next(); 
                continue;
            }

            
            if (opcion == 1 || opcion == 2) {
                String hql = "";
                Query q = null;

                if (opcion == 1) {
                    
                    System.out.print("Introduce el Identificador ");
                    String idBusqueda = sc.nextLine();
                    
               
                    hql = "FROM Modelos.Curso c WHERE str(c.idCurso) LIKE :patron";
                    q = session.createQuery(hql);
                    q.setParameter("patron", "%" + idBusqueda + "%");
                    
                } else {
                  
                    System.out.print("Introduce el Nombre del curso: ");
                    String nombreBusqueda = sc.nextLine();
                    
                    
                    hql = "FROM Modelos.Curso c WHERE c.nombre LIKE :patron";
                    q = session.createQuery(hql);
                    q.setParameter("patron", "%" + nombreBusqueda + "%");
                }

                
                List<Curso> listaCursos = q.list();

                
                if (listaCursos.isEmpty()) {
                    System.out.println(">>> No se han encontrado cursos con esos criterios.");
                } else {
                    System.out.println("\n--- RESULTADOS ENCONTRADOS ---");
                    System.out.printf("%-5s %-20s %-20s\n", "ID", "CURSO", "PROFESOR");
                    System.out.println("------------------------------------------------");
                    
                    for (Curso c : listaCursos) {
                        
                        String nombreProfe = (c.getProfesor() != null) ? c.getProfesor().getNombre() : "Sin Profesor";
                        
                        System.out.printf("%-5d %-20s %-20s\n", 
                                c.getIdCurso(), 
                                c.getNombre(), 
                                nombreProfe);
                    }
                }
            }

        } while (opcion != 0);

        System.out.println("Saliendo del programa...");
        
       
        session.close();
        sf.close();
        sc.close();
    }
}