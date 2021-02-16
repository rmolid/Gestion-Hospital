package base;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase Paciente que representa un Paciente. Implementa Serializale para que
 * sus instancias puedan ser guardades en un fichero binario.
 *
 * @author Raquel
 * @version 1
 * @since 16/02/2021
 */
public class Paciente implements Serializable {

    private String dni;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private double peso;
    private Medico medico;
    private Centro centro;
    private ArrayList<Enfermedad> enfermedadesPaciente;

    /**
     * Constructor de la clase paciente que nos permite crear instancias de dicho objeto
     *
     * @param dni             el parametro hace referencia al dni de un paciente
     * @param nombre          el parametro hace referencia al nombre de un paciente
     * @param apellidos       el parametro hace referencia a los apellidos de un paciente
     * @param fechaNacimiento el parametro hace referencia a la fecha de nacimiento de un paciente
     * @param peso            el parametro hace referencia al peso de un paciente
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
     * Constructor de la calse paciente con un centro, medico y enfermedades
     *
     * @param dni                   el parametro hace referencia al dni de un paciente
     * @param nombre                el parametro hace referencia al nombre de un paciente
     * @param apellidos             el parametro hace referencia a los apellidos de un paciente
     * @param fechaNacimiento       el parametro hace referencia a la fehca de nacimiento de un paciente
     * @param peso                  el parametro hace referencia al peso de un paciente
     * @param medico                el parametro hace referencia medico que tenga un paciente
     * @param centro                el parametro hace referencia al centro que tenga un paciente
     * @param enfermedaadesPaciente el parametro hace referencia al conjunto de enfermedades de un paciente
     */
    public Paciente(String dni, String nombre, String apellidos, LocalDate fechaNacimiento, double peso, Medico medico, Centro centro, ArrayList<Enfermedad> enfermedaadesPaciente) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.medico = medico;
        this.centro = centro;
        this.enfermedadesPaciente = enfermedaadesPaciente;
    }

    /**
     * Metodo que retorna un ArrayList que contiene las enfermedades que padece un paciente
     *
     * @return enfermedadesPaciente conjunto de enfermedades que padece un paciente
     */
    public ArrayList<Enfermedad> getEnfermedaadesPaciente() {
        return enfermedadesPaciente;
    }

    /**
     * Metodo que permite modificar las enfermedades que padece un paciente
     *
     * @param enfermedaadesPaciente el parametro representa las nuevas enfermedades del paciente
     */
    public void setEnfermedaadesPaciente(ArrayList<Enfermedad> enfermedaadesPaciente) {
        this.enfermedadesPaciente = enfermedaadesPaciente;
    }

    /**
     * Metodo que devuelve el centro asociado a un paciente
     *
     * @return centro que tiene asignado un paciente
     */
    public Centro getCentro() {
        return centro;
    }

    /**
     * Metodo que permite cambiar el centro asociado a un paciente
     *
     * @param centro el parametro hace referencia al nuevo centro del paciente
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
     * @param dni el parametro se corresponde con el nuevo dni de un paciente
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
     * @param nombre el parametro se corresponde con el nuevo nombre del paciente
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
     * @param apellidos el parametro hace referencia a los nuevos apellidos de un paciente
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
     * @param fechaNacimiento el parametro hace referencia a la nueva fecha de nacimiento de un paciente
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
     * @param peso el parametro hace referencia al nuevo peso de un paciente
     */
    public void setPeso(double peso) {
        this.peso = peso;
    }

    /**
     * Metodo que devuelve un objeto de tipo Medico que se corresponde con el medico
     * asociado a un paciente
     *
     * @return medico
     */
    public Medico getMedico() {
        return medico;
    }

    /**
     * Metodo que permite cambiar el medico asociado a un paciente
     *
     * @param medico el parametro hace referencia al nuevo medico de un paciente
     */
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    /**
     * Metodo que devuelve una cadena con los atributos asociados a un paciente, el
     * cual puede tener un medico asociado o no.
     *
     * @return dni del paciente y nombre y apellidos de este
     */
    @Override
    public String toString() {
        // Todo si tiene medico
        if (medico != null) {
            return "DNI: " + dni.toUpperCase() + " NOMBRE Y APELLIDOS: " + nombre.toUpperCase() + " " + apellidos.toUpperCase();
        } else {
            return "DNI: " + dni.toUpperCase() + " NOMBRE Y APELLIDOS: " + nombre.toUpperCase() + " " + apellidos.toUpperCase();
        }

    }
}