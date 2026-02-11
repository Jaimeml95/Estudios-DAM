/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ad.moro_lopez_jaime_tarea4_2;

import com.ad.entidades.Curso;
import com.ad.JpaUtil.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Carlos
 */
public class Main {

    public static void main(String[] args) {
        
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n=== MENÚ INSTITUTO (JPA) ===");
            System.out.println("1. Buscar Curso por ID");
            System.out.println("2. Buscar Curso por Nombre");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");
            
            try {
                
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(">> Error: Por favor, introduce un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    buscarPorId(scanner, em);
                    break;
                case 2:
                    buscarPorNombre(scanner, em);
                    break;
                case 0:
                    System.out.println("Cerrando aplicación...");
                    break;
                default:
                    System.out.println(">> Opción no válida.");
            }
        }

       
        em.close();
        JpaUtil.shutdown(); 
    }

   

    private static void buscarPorId(Scanner scanner, EntityManager em) {
        System.out.print("Introduce el ID del curso: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            
            
            Curso curso = em.find(Curso.class, id);

            if (curso != null) {
                mostrarDatosCurso(curso);
            } else {
                System.out.println(">> No existe ningún curso con el ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println(">> El ID debe ser un número entero.");
        }
    }

    private static void buscarPorNombre(Scanner scanner, EntityManager em) {
        System.out.print("Introduce el nombre del curso (o parte): ");
        String texto = scanner.nextLine();

        
        TypedQuery<Curso> query = em.createQuery(
            "SELECT c FROM Curso c WHERE c.nombre LIKE :busqueda", Curso.class);
        
        query.setParameter("busqueda", "%" + texto + "%");

        List<Curso> resultados = query.getResultList();

        if (!resultados.isEmpty()) {
            System.out.println("--- Resultados encontrados ---");
            for (Curso c : resultados) {
                mostrarDatosCurso(c);
            }
        } else {
            System.out.println(">> No se encontraron cursos con ese nombre.");
        }
    }

    
    private static void mostrarDatosCurso(Curso c) {
        String nombreProfe = "Sin Asignar";
        
        
        if (c.getProfesorId() != null) {
            nombreProfe = c.getProfesorId().getNombre();
        }

        System.out.println("------------------------------------------------");
        System.out.println(" ID: " + c.getIdCurso());
        System.out.println(" ASIGNATURA: " + c.getNombre());
        System.out.println(" PROFESOR: " + nombreProfe);
        System.out.println("------------------------------------------------");
    }
    }