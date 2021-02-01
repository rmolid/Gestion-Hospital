package base;

import java.io.Serializable;

/**
 * Clase que representa un Medico. Implementa Serializable para que sus
 * instancias se puedan guardar en un fichero binario
 *
 * @author Raquel
 *
 */
public class Medico implements Serializable {

    private String dni;
    private String nombre;
    private String apellido;
    private int edad;
    private Boolean esHombre;
    private Centro centro;

    /**
     * Constructor de la clase medico
     *
     * @param dni
     * @param nombre
     * @param apellido
     * @param edad
     * @param esHombre
     */
    public Medico(String dni, String nombre, String apellido, int edad, Boolean esHombre) {
        super();
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.esHombre = esHombre;

    }
    /**
     * Constructor de la clase medico con un centro
     *
     * @param dni
     * @param nombre
     * @param apellido
     * @param edad
     * @param esHombre
     */
    public Medico(String dni, String nombre, String apellido, int edad, Boolean esHombre, Centro centro) {
        super();
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.esHombre = esHombre;
        this.centro = centro;
    }

    /**
     * Metodo que devuelve un String que se corresponde con el dni de un medico
     *
     * @return dni que identifica a un medico.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Metodo que permite cambiar el dni asociado a un medico
     *
     * @param dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Merodo que devuelve un String que se corresponde con el nombre de un medico
     *
     * @return nombre de un medico
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Medodo que permite cambiar el nombre asociado a un medico
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que devuelve un String que se corresponde con el apellido de un medico
     *
     * @return apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Metodo que permite cambiar el apellido asociado a un medico
     *
     * @param apellido
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Metodo que devuelve un entero que se corresponde con la edad de un medico
     *
     * @return edad
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Metodo que permite cambiar la edad asociada a un medico
     *
     * @param edad
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Metodo que devuelve el Centro en el que trabaja el medico
     * @return centro
     */
    public Centro getCentro() {
        return centro;
    }

    /**
     * Metodo que permite cambiar el centro en el que trabaja un medico
     * @param centro
     */
    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    /**
     * Metodo que devuelve un booleano que indica el genero de un medico
     *
     * @return genero

     */
    public Boolean getEsHombre() {
        return esHombre;
    }

    /**
     * Metodo que permite cambiar el genero de un medico
     *
     * @param esHombre
     */
    public void setEsHombre(Boolean esHombre) {
        this.esHombre = esHombre;
    }

    /**
     * Metodo que devuelve una cadena con los atributos de un medico
     *
     * @return dni
     * @return nombre
     * @return apellido
     * @return edad
     * @return esHombre
     */
    @Override
    public String toString() {
        return "DNI: " + dni + "\n"+ " NOMBRE: " + nombre + " " + apellido;
    }

}