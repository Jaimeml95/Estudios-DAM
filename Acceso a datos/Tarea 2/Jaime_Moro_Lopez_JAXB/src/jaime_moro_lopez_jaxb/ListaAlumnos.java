package jaime_moro_lopez_jaxb;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tpmvo
 */
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Alumnos")
public class ListaAlumnos {
    private List<Alumno> lista = new ArrayList<>();

    public ListaAlumnos() {}

    @XmlElement(name = "Alumno")
    public List<Alumno> getListaAlumnos() { return lista; }
    public void setListaAlumnos(List<Alumno> lista) { this.lista = lista; }
}
