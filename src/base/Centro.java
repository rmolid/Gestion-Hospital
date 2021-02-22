package base;

import javax.swing.*;
import java.io.Serializable;

/**
 * Clase Centro representa un centro de salud. Implementa Serializale para que
 * sus instancias puedan ser guardades en un fichero binario
 *
 * @author Raquel Molina Diaz
 * @version 1
 * @since 16/02/2021
 */
public class Centro implements Serializable {

    private Icon imagenCentro;
    private String nombreCentro;
    private String localidadCentro;
    private int capacidad;
    private Boolean esPrivado;

    /**
     * Constructor de la clase Centro
     *
     * @param imagenCentro    el parametro imagenCentro indica la imagen de un centro
     * @param nombreCentro    el parametro nombreCentro indica el nombre del centro de salud
     * @param localidadCentro el parametro localidadCentro indica la localidad del centro de salud.
     * @param capacidad       el parametro capacidad idica la capacidad del centro de salud
     * @param esPrivado       el parametro esPrivado indica si un centro es privado o publico.
     */
    public Centro(Icon imagenCentro, String nombreCentro, String localidadCentro, int capacidad, Boolean esPrivado) {
        this.imagenCentro = imagenCentro;
        this.nombreCentro = nombreCentro;
        this.localidadCentro = localidadCentro;
        this.capacidad = capacidad;
        this.esPrivado = esPrivado;
    }

    /**
     * Metodo que devuelve un Icon que se corresponde con la imagen del
     * centro de salud
     *
     * @return imagenCentro imagen del centro de salud
     */
    public Icon getImagenCentro() {

        return imagenCentro;
    }

    /**
     * Metodo set que permite cambiar la imagen del centro
     *
     * @param imagenCentro parametro que se corresponde con la imagen del centro
     */
    public void setImagenCentro(Icon imagenCentro) {
        this.imagenCentro = imagenCentro;
    }

    /**
     * Metodo que devuelve un String que se corresponde con el nombre del
     * dentro de salud
     *
     * @return nombreCentro nombre del centro de salud
     */
    public String getNombreCentro() {
        return nombreCentro;
    }

    /**
     * Metodo set que permite cambiar el nombre del centro
     *
     * @param nombreCentro parametro que se corresponde con el nombre del centro
     */
    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    /**
     * Metodo que devuelve un String que se corresponde con la localidad
     * del centro de salud
     *
     * @return localidadCentro localidad de un centro de salud
     */
    public String getLocalidadCentro() {
        return localidadCentro;
    }

    /**
     * Metodo set que permite cambiar la localidad del centro
     *
     * @param localidadCentro parametro que se corresponde con la localidad del centro
     */
    public void setLocalidadCentro(String localidadCentro) {
        this.localidadCentro = localidadCentro;
    }

    /**
     * Metodo que devuelve un int que se corresponde con la
     * capacidad de pacientes que tiene el centro de salud
     *
     * @return capacidad se corresponde con el aforo del centro de salud
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Metodo set que permite cambiar la capacidad del centro
     *
     * @param capacidad el parametro se corresponde con el aforo del centro
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Metodo que devuelve True en caso de que el centro
     * sea privado y flase si es publico
     *
     * @return esPrivado retorna true en caso de que sea privado y false si es publico
     */
    public Boolean getEsPrivado() {
        return esPrivado;
    }

    /**
     * Metodo que permite cambiar un centro de privado a publico
     *
     * @param esPrivado El parametro indica si es privado (true) o publico(false) un centro
     */
    public void setEsPrivado(Boolean esPrivado) {
        this.esPrivado = esPrivado;
    }

    /**
     * Metodo que devuelve una cadena con el nombre y localidad del centro de salud
     *
     * @return String que se corresponde con el nombre y la localidad de un centro de salud.
     */
    @Override
    public String toString() {
        return "CENTRO: " + nombreCentro.toUpperCase() + "\n"
                + " LOCALIDAD: " + localidadCentro.toUpperCase();

    }
}