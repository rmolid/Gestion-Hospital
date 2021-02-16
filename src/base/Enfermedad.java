package base;

import java.io.Serializable;

/**
 * Clase Enfermedad representa una enfermedad que puede tener un paciente.
 * Implementa Serializale para que sus instancias puedan ser guardades en un fichero binario
 *
 * @author Raquel Molina Diaz
 * @version 1
 * @since 16/02/2021
 */
public class Enfermedad implements Serializable {

    private String nombre;
    private String sintomas;
    private String medicamento;
    private String origen;

    /**
     * Constructor de la clase Enfermeda, que nos permite crear instancia de dicha clase
     *
     * @param nombre      este parametro se corresponde con el nombre de la enfermedad
     * @param sintomas    esta parametro se corresponde con los sintoman que una enfermedad puede tener
     * @param medicamento este parametro se corresponde con el medicamento que trata la enfermedad
     * @param origen      este parametro hace referencia al lugar de origen de la enfermedad
     */
    public Enfermedad(String nombre, String sintomas, String medicamento, String origen) {
        this.nombre = nombre;
        this.sintomas = sintomas;
        this.medicamento = medicamento;
        this.origen = origen;

    }

    /**
     * Metodo que devuelve un String que se corresponde con el nombre de una enfermedad
     *
     * @return nombre de una enfermedad
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que permite cambiar el nombre de una enfermedad
     *
     * @param nombre el parametro se corresponde con el nuevo  nombre de la enfermedad
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que devuleve un String que se corresponde con los sintomas que tiene una enfermedad
     *
     * @return sintomas de una enfermedad
     */
    public String getSintomas() {
        return sintomas;
    }

    /**
     * Metodo que permite cambiar los sintomas que tiene una enfermedad
     *
     * @param sintomas el parametro sintomas es un String que se corresponde con los nuevos sintomas de la enfermedad
     */
    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    /**
     * Metodo que devuleve un String que se corresponde con el medicamento usado para tratar una enfermedad
     *
     * @return medicamento usado para tratar una enfermedad
     */
    public String getMedicamento() {
        return medicamento;
    }

    /**
     * Metodo que permite cambiar el medicamento usado para tratar una enfermedad
     *
     * @param medicamento el parametro medicamento es un String que hace referencia al nuevo medicamento
     *                    usado para tratar una enfermedad
     */
    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    /**
     * Metodo que devuleve un String que se corresponde con lugar de origen de una enfermedad
     *
     * @return origen hace referencia al lugar de origen de la enfermedad
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * Metodo que permite cambiar el lugar de origen de una enfermedad
     *
     * @param origen el parametro origen es un String que hace referencia al nuevo lugar de origen de una enfermedad
     */
    public void setOrigen(String origen) {
        this.origen = origen;
    }

    /**
     * Metodo que devuelve un String que contiene el nombre de la enfermedad
     *
     * @return nombre de la enfermedad
     */
    @Override
    public String toString() {
        return "ENFERMEDAD: " + nombre.toUpperCase();
    }

}