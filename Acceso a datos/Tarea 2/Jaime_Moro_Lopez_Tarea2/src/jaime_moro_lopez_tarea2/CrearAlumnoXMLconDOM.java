/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaime_moro_lopez_tarea2;

/**
 *
 * @author tpmvo
 */
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class CrearAlumnoXMLconDOM {
    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile("alumnos.dat", "r")) {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            
            Element raiz = doc.createElement("Alumnos");
            doc.appendChild(raiz);

            while (raf.getFilePointer() < raf.length()) {
                String ape = "";
                for (int i = 0; i < 20; i++) ape += raf.readChar();
                int edad = raf.readInt();
                double nota = raf.readDouble();

                Element alumno = doc.createElement("Alumno");
                raiz.appendChild(alumno);
                
                crearElemento("Apellido", ape.trim(), alumno, doc);
                crearElemento("Edad", String.valueOf(edad), alumno, doc);
                crearElemento("Nota", String.valueOf(nota), alumno, doc);
            }

            // Guardar el XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("alumnos.xml"));
            transformer.transform(source, result);
            
            System.out.println("Fichero alumnos.xml creado con éxito.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void crearElemento(String dato, String valor, Element raiz, Document doc) {
        Element elem = doc.createElement(dato);
        Text texto = doc.createTextNode(valor);
        raiz.appendChild(elem);
        elem.appendChild(texto);
    }
}
