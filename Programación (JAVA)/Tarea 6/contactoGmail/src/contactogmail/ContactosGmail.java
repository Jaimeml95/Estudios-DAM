/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactogmail;

/**
 *
 * @author tpmvo
 */
public class ContactosGmail {

    public static void main(String[] args) {
        //Instanciamos las clases para poder usar sus metodos
        ManejarContactos mc = new ManejarContactos();
        ManejarFicheros mf = new ManejarFicheros();
        //Ejecutamos leer fichero y guardamos su contenido en contactos
        mc.contactos = mf.leerFicheroCSV("D:\\JAIME\\ESTUDIOS\\CS-DAM\\Programas\\JavaDAM\\contactoGmail\\contactos.csv");
        //Mostramos los contactos almacenados
        mc.mostrarContactos();
        //Añadimos un nuevo contacto
        mc.aniadirContacto();
        //Ordenamos los contactos por apellidos y los mostramos
        mc.ordenarContactos(mc.contactos);
        //Mostramos los contactos (ordenados por email)
        mc.mostrarContactos();
        //Escribimos los contactos en un fichero
        mf.escribirFicheroTexto("ficheroContactos.csv", mc.contactos);
    }
}
