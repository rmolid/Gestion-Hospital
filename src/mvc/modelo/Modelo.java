package mvc.modelo;

import base.Centro;
import base.Enfermedad;
import base.Medico;
import base.Paciente;
import util.Util;

import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * La clase Modelo almacena los datos que registra la aplicacion
 *
 * @author Raquel Molina Diaz
 * @version 1
 * @since 16/02/2021
 */
public class Modelo {
    private ArrayList<Medico> listaMedicos;
    private ArrayList<Paciente> listaPacientes;
    private ArrayList<Centro> listaCentros;
    private ArrayList<Enfermedad> listaEnfermedades;
    private ResourceBundle bundle;

    /**
     * Constructor de la clase Modelo los arrayList de Medicos, Pacientes
     * Centros y enfermedades.
     */
    public Modelo() {
        bundle = ResourceBundle.getBundle("idiomaResourcebundle");
        listaMedicos = new ArrayList<Medico>();
        listaPacientes = new ArrayList<Paciente>();
        listaCentros = new ArrayList<Centro>();
        listaEnfermedades = new ArrayList<Enfermedad>();
    }

    /**
     * Metodo que da de alta un medico y lo agrega a la lista de medicos
     *
     * @param dni       el parametro dni se corresponde con el dni de un medico
     * @param nombre    el parametro nombre se corresponde con el nombre de un medico
     * @param apellidos el parametro apellidos se corresponde con los apellidos de un medico
     * @param edad      el parametro edad se corresponde con la edad de un medico
     * @param sexo      el parametro sexo se corresponde con el genero de un medico
     * @param centro    el parametros centro se corresponde con el centro que tenga asignado un paciente
     */
    public void altaMedico(String dni, String nombre, String apellidos, int edad, Boolean sexo, Centro centro) {
        Medico medico = new Medico(dni, nombre, apellidos, edad, sexo, centro);
        listaMedicos.add(medico);
    }

    /**
     * Metodo que agrega un paciente a la lista de pacientes
     *
     * @param paciente el parametro paciente se corresponde con un paciente que va a ser dado de alta
     */
    public void altaPaciente(Paciente paciente) {
        listaPacientes.add(paciente);
    }

    /**
     * Metodo que agrega un centro a la lista de centros
     *
     * @param centro el parametro centro se corresponde con un centro que va a ser dado de alta
     */
    public void altaCentro(Centro centro) {
        listaCentros.add(centro);
    }

    /**
     * Metodo que agrega una enfermedad a la lista de enfermedades
     *
     * @param enfermedad el parametro enfermedad se corresponde con una enfermedad que va a ser dado de alta
     */
    public void altaEnfermedad(Enfermedad enfermedad) {
        listaEnfermedades.add(enfermedad);
    }

    /**
     * Metodo que devuelve un ArrayList de objetos Medico
     * que contiene todos los medicos que hay en la aplicacion en ese momento
     *
     * @return listaMedicos
     */
    public ArrayList<Medico> getListaMedicos() {
        return listaMedicos;
    }

    /**
     * Metodo que devuelve un ArrayList de objetos Centro
     * que contiene todos los centros que hay en la aplicacion en ese momento
     *
     * @return listaCentro
     */
    public ArrayList<Centro> getListaCentros() {
        return listaCentros;
    }

    /**
     * Metodo que devuelve un ArrayList de objetos Paciente
     * que contiene todos los pacientes que hay en la aplicacion en ese momento
     *
     * @return listaPacientes
     */
    public ArrayList<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    /**
     * Metodo que devuelve una lista de enfermedades que contiene todas
     * las enfermedades que hay en la aplicacion en ese momento
     *
     * @return listaEnfermedades
     */
    public ArrayList<Enfermedad> getListaEnfermedades() {
        return listaEnfermedades;
    }


    /**
     * Metodo que se encarga de cargar los datos de la aplicacion
     *
     * @param file el parametro file hace referencia al fichero que contiene los datos que se van a cargar
     * @throws ClassNotFoundException el metodo puede lanzar una excepcion del tipo ClassNotFoundException
     * si no encuentra el fichero
     */
    public void cargarDatos(File file) throws ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream deserializador = new ObjectInputStream(fis);
            listaCentros = (ArrayList<Centro>) deserializador.readObject();
            listaMedicos = (ArrayList<Medico>) deserializador.readObject();
            listaPacientes = (ArrayList<Paciente>) deserializador.readObject();
            listaEnfermedades = (ArrayList<Enfermedad>) deserializador.readObject();
            deserializador.close();
        } catch (IOException e) {
            Util.mensajeError(bundle.getString("fichero.error.lectura"));
        }
    }

    /**
     * Metodo encargado de guardar datos de la aplicacion
     *
     * @param file el parametro file hace referencia al fichero donde se van a guardar los datos.
     * @throws IOException el metodo puede lanzar una excepcion del tipo IOException si no encuentra
     * el fichero
     */
    public void guardarDatos(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream serializador = new ObjectOutputStream(fos);

        serializador.writeObject(listaCentros);
        serializador.writeObject(listaMedicos);
        serializador.writeObject(listaPacientes);
        serializador.writeObject(listaEnfermedades);
        serializador.close();
    }

}
