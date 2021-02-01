package base;

import javax.swing.*;
import java.io.Serializable;

/**
 * Clase Centro representa un centro de salud. Implementa Serializale para que
 * sus instancias puedan ser guardades en un fichero binario
 *
 * @author Raquel
 *
 */
public class Centro implements Serializable{
    private Icon imagenCentro;
    private String nombreCentro;
    private String localidadCentro;
    private int capacidad;
    private Boolean esPrivado;

    /**
     * Constructor de la clase Centro
     * @param imagenCentro
     * @param nombreCentro
     * @param localidadCentro
     * @param capacidad
     * @param esPrivado
     */
    public Centro(Icon imagenCentro, String nombreCentro, String localidadCentro, int capacidad, Boolean esPrivado) {
        this.imagenCentro = imagenCentro;
        this.nombreCentro = nombreCentro;
        this.localidadCentro = localidadCentro;
        this.capacidad = capacidad;
        this.esPrivado = esPrivado;
    }

    /**
     *Metodo que devuelve un Icon que se corresponde con la imagen del
     * centro de salud
     * @return imagenCentro
     */
    public Icon getImagenCentro() {
        return imagenCentro;
    }

    /**
     * Metodo set que permite cambiar la imagen del centro
     * @param imagenCentro
     */
    public void setImagenCentro(Icon imagenCentro) {
        this.imagenCentro = imagenCentro;
    }

    /**
     * Metodo que devuelve un String que se corresponde con el nombre del
     * dentro de salud
     * @return nombreCentro
     */
    public String getNombreCentro() {
        return nombreCentro;
    }

    /**
     * Metodo set que permite cambiar el nombre del centro
     * @param nombreCentro
     */
    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    /**
     * Metodo que devuelve un String que se corresponde con la localidad
     * del centro de salud
     * @return localidadCentro
     */
    public String getLocalidadCentro() {
        return localidadCentro;
    }

    /**
     * Metodo set que permite cambiar la localidad del centro
     * @param localidadCentro
     */
    public void setLocalidadCentro(String localidadCentro) {
        this.localidadCentro = localidadCentro;
    }

    /**
     * Metodo que devuelve un int que se corresponde con la
     * capacidad de pacientes que tiene el centro de salud
     * @return capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Metodo set que permite cambiar la capacidad del centro
     * @param capacidad
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Metodo que devuelve True en caso de que el centro
     * sea privado y flase si es publico
     * @return esPrivado
     */
    public Boolean getEsPrivado() {
        return esPrivado;
    }

    /**
     * Metodo que permite cambiar un centro de privado a publico
     * @param esPrivado
     */
    public void setEsPrivado(Boolean esPrivado) {
        this.esPrivado = esPrivado;
    }

    /**
     * Metodo que devuelve una cadena con los atributos del centro
     * @return imagenCentro
     * @return nombreCentro
     * @return localidadCentro
     * @return capacidad
     * @return esPrivado
     */
    @Override
    public String toString() {
        return "CENTRO: " + nombreCentro + "\n"
                + " Localidad: " + localidadCentro;

    }
}
