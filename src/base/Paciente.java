package base;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Clase Paciente que representa un Paciente. Implementa Serializale para que
 * sus instancias puedan ser guardades en un fichero binario
 *
 * @author Raquel
 */
public class Paciente implements Serializable {

    private String dni;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private double peso;
    private Medico medico;
    private Centro centro;

    /**
     * Constructor de la clase paciente
     *
     * @param dni
     * @param nombre
     * @param apellidos
     * @param fechaNacimiento
     * @param peso
     */
    public Paciente(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, double peso) {
        super();
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
    }

    /**
     * Constructor de la clase paciente que asocia un medico al paciente
     *
     * @param dni
     * @param nombre
     * @param apellidos
     * @param fechaNacimiento
     * @param peso
     * @param medico
     */
    public Paciente(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, double peso,
                    Medico medico, Centro centro) {
        super();
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.medico = medico;
        this.centro = centro;

    }

    /**
     * Metodo que devuelve el centro asociado a un paciente
     * @return centro
     */
    public Centro getCentro() {
        return centro;
    }

    /**
     * Metodo que permite cambiar el centro asociado a un paciente
     * @param centro
     */
    public void setCentro(Centro centro) {
        this.centro = centro;
    }

    /**
     * Metodo que devuelve un String que se corresponde con el dni de un paciente
     *
     * @return dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * Metodo que permite cambiar el dni asociado a un paciente
     *
     * @param dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Metodo que devuelve un String que se corresponde con el nombre de un paciente
     *
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que permite cambiar el nombre asociado a un paciente
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que devuelve un String que se corresponde con los apellidos de un
     * paciente
     *
     * @return apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Metodo que permite cambiar los apellidos asociados a un paciente
     *
     * @param apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Metodo que devuelve un LocalDate que se corresponde con la fecha de
     * nacimiento de un paciente
     *
     * @return fechaNacimiento
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Metodo que permite cambiar la fecha de nacimiento asociada a un paciente
     *
     * @param fechaNacimiento
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Metodo que devuelve un dooble que se corresponde con el peso de un paciente
     *
     * @return peso
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Metodo que permite cambiar el peso asociado a un paciente
     *
     * @param peso
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * Metodo que devuelve un objeto de tipo Medico que se corresponde con el medico
     * asociado a un paciente
     *
     * @return
     */
    public Medico getMedico() {
        return medico;
    }

    /**
     * Metodo que permite cambiar el medico asociado a un paciente
     *
     * @param medico
     */
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    /**
     * Metodo que devuelve una cadena con los atributos asociados a un paciente, el
     * cual puede tener un medico asociado o no.
     *
     * @return medico
     */
    @Override
    public String toString() {
        // Todo si tiene medico
        if (medico != null) {
            return "Dni: " + dni.toUpperCase() + " Nombre y apellidos: " + nombre.toUpperCase() + " " + apellidos.toUpperCase();
        } else {
            return "Dni: " + dni.toUpperCase() + " Nombre y apellidos: " + nombre.toUpperCase() + " " + apellidos.toUpperCase();
        }

    }

}
