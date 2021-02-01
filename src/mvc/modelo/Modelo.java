package mvc.modelo;

import base.Centro;
import base.Medico;
import base.Paciente;
import util.Util;

import java.io.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * La clase Modelo almacena los datos que registra la aplicacion
 *
 * @author Raquel Molina Diaz
 */
public class Modelo {
    private ArrayList<Medico> listaMedicos;
    private ArrayList<Paciente> listaPacientes;
    private ArrayList<Centro> listaCentros;
    private ResourceBundle bundle;

    /**
     * Constructor de la clase Modelo que instancia un HashSet de medicos y un
     * ArrayList de pacientes
     */
    public Modelo() {
        bundle = ResourceBundle.getBundle("idiomaResourcebundle");
        listaMedicos = new ArrayList<Medico>();
        listaPacientes = new ArrayList<Paciente>();
        listaCentros = new ArrayList<Centro>();
    }

    /**
     * Metodo que da de alta un medico y lo agrega a la lista de medicos
     *
     * @param dni
     * @param nombre
     * @param apellidos
     * @param edad
     * @param sexo
     */
    public void altaMedico(String dni, String nombre, String apellidos, int edad, Boolean sexo, Centro centro) {
        Medico medico = new Medico(dni, nombre, apellidos, edad, sexo, centro);
        listaMedicos.add(medico);
    }

    /**
     * Metodo que agrega un paciente a la lista de pacientes
     *
     * @param paciente
     */
    public void altaPaciente(Paciente paciente) {
        listaPacientes.add(paciente);
    }

    /**
     * Metodo que agrega un centro a la lista de centros
     *
     * @param centro
     */
    public void altaCentro(Centro centro) {
        listaCentros.add(centro);
    }

    /**
     * Metodo que devuelve un HashSet de objetos Medico
     *
     * @return listaMedicos
     */
    public ArrayList<Medico> getListaMedicos() {
        return listaMedicos;
    }

    /**
     * Metodo que devuelve un ArrayList de objetos Centro
     *
     * @return listaCentro
     */
    public ArrayList<Centro> getListaCentros() {
        return listaCentros;
    }

    /**
     * Metodo que devuelve un ArrayList de objetos Paciente
     *
     * @return listaPacientes
     */
    public ArrayList<Paciente> getListaPacientes() {
        return listaPacientes;
    }


    /**
     * Metodo que se encarga de cargar los datos de la aplicacion
     *
     * @param file
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void cargarDatos(File file) throws ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream deserializador = new ObjectInputStream(fis);
            listaCentros = (ArrayList<Centro>) deserializador.readObject();
            listaMedicos = (ArrayList<Medico>) deserializador.readObject();
            listaPacientes = (ArrayList<Paciente>) deserializador.readObject();
            deserializador.close();
        } catch (IOException e) {
            Util.mensajeError(bundle.getString("fichero.error.lectura"));
        }
    }

    /**
     * Metodo encargado de guardar datos de la aplicacion
     *
     * @param file
     * @throws IOException
     */
    public void guardarDatos(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream serializador = new ObjectOutputStream(fos);

        serializador.writeObject(listaCentros);
        serializador.writeObject(listaMedicos);
        serializador.writeObject(listaPacientes);
        serializador.close();
    }

}
