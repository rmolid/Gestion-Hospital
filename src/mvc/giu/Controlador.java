package mvc.giu;

import base.Centro;
import base.Medico;
import base.Paciente;
import dialogos.DialogoDiferentesGraficas;
import dialogos.GestionCentros;
import dialogos.Preferencias;
import mvc.modelo.Modelo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import util.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Clase Controlador que permite mantener la relacion entre
 * la clase Vista y el Modelo
 *
 * @author Raquel Molina Diaz
 */
public class Controlador implements ActionListener, FocusListener, ItemListener, ListSelectionListener, MouseListener {

    private Modelo modelo;
    private Ventana ventana;
    private ResourceBundle bundle;

    /**
     * Constructor de la clase controlador
     *
     * @param modelo
     * @param ventana
     */
    public Controlador(Modelo modelo, Ventana ventana) {
        super();

        this.modelo = modelo;
        this.ventana = ventana;
        cambiarColores();
        bundle = ResourceBundle.getBundle("idiomaResourcebundle");
        initActionHandlers(this);
        initFocusLister(this);
        initItemStatChanged(this);
        initListListeners(this);
        initMouseClickedListeners(this);
       // crearDir();


    }

/*
    private void crearDir(){
        File d = new File("./data");
        File documento = new File(d, "preferencias.conf");
        d.mkdir();
        try {
            documento.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
    /**
     * Metodo que asocia un MouseListener a la lista de centros
     *
     * @param e
     */
    private void initMouseClickedListeners(MouseListener e) {
        ventana.listCentros.addMouseListener(e);
    }

    /**
     * Metodo que asocia componentes graficos con el manejador de eventos de tipos
     * ListSelectionListener
     *
     * @param listener de tipo ListSelectionListener
     */
    private void initListListeners(ListSelectionListener listener) {
        ventana.listPacientes.addListSelectionListener(listener);
        ventana.listMedicos.addListSelectionListener(listener);
        ventana.listCentros.addListSelectionListener(listener);
    }

    /**
     * Metodo que asocia un evento de tipo ItemListener a combo box de centros
     *
     * @param evento
     */
    private void initItemStatChanged(ItemListener evento) {

        ventana.cbCentroPaciente.addItemListener(evento);
    }

    /**
     * Metodo que asocia un Action Listener a los elementos con los que el usuario
     * interactua.
     *
     * @param listener de tipo ActionListener
     */
    private void initActionHandlers(ActionListener listener) {
        ventana.botNuevoMedico.addActionListener(listener);
        ventana.botNuevoPaciente.addActionListener(listener);
        ventana.nuevoCentro.addActionListener(listener);
        ventana.botEliminarMedico.addActionListener(listener);
        ventana.botEliminarPaciente.addActionListener(listener);
        ventana.botEliminarCentro.addActionListener(listener);
        ventana.botImagen.addActionListener(listener);
        ventana.botGuardar.addActionListener(listener);
        ventana.botCargar.addActionListener(listener);
        ventana.botModificarMedico.addActionListener(listener);
        ventana.botModificarPaciente.addActionListener(listener);
        ventana.botModificarCentro.addActionListener(listener);
        ventana.botEliminarFoto.addActionListener(listener);
        ventana.itemCargar.addActionListener(listener);
        ventana.itemGuardar.addActionListener(listener);
        ventana.itemSalir.addActionListener(listener);
        ventana.itemConfiguracion.addActionListener(listener);
        ventana.botGrafico1.addActionListener(listener);
        ventana.botTiposGraficas.addActionListener(listener);

        //Preferencias
        ventana.botAjustes.addActionListener(listener);
    }

    /**
     * Metodo que asocia un FocusListener a los diferentes elementos
     * de la ventana
     *
     * @param listener
     */
    private void initFocusLister(FocusListener listener) {
        //Campos de Medico
        ventana.txtDniMedico.addFocusListener(listener);
        ventana.txtNombreMedico.addFocusListener(listener);
        ventana.txtApellidosMedico.addFocusListener(listener);
        ventana.spinnerEdadMedico.addFocusListener(listener);
        ventana.hombreRadioButton.addFocusListener(listener);
        ventana.mujerRadioButton.addFocusListener(listener);
        ventana.cbCentroMedico.addFocusListener(listener);
        //Campos de Paciente
        ventana.txtDniPaciente.addFocusListener(listener);
        ventana.txtNombrePaciente.addFocusListener(listener);
        ventana.txtApellidosPaciente.addFocusListener(listener);
        ventana.txtPesoPaciente.addFocusListener(listener);
        ventana.pacienteDatePicker.addFocusListener(listener);
        ventana.cbCentroPaciente.addFocusListener(listener);
        ventana.cbMedicoDePaciente.addFocusListener(listener);
        //Campos de Centro
        ventana.txtNombreCentro.addFocusListener(listener);
        ventana.txtLocalidad.addFocusListener(listener);
        ventana.spinnerCapacidad.addFocusListener(listener);
        ventana.cbPrivado.addFocusListener(listener);
    }

    /**
     * Metodo que asocia un evento de tipo MouseEvent a la lista
     * de los centros para crear una instancia de la clase Gestion
     * Centros con el centro seleccionado.
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            if (modelo.getListaCentros().size() == 0) {
                Util.mensajeError(bundle.getString("CenterListEmpty"));
            } else {
                ventana.listCentros.getSelectedIndex();
                Centro centro = (Centro) ventana.listCentros.getSelectedValue();
                ArrayList<Medico> listaMedicos = modelo.getListaMedicos();
                ArrayList<Paciente> listaPacientes = modelo.getListaPacientes();
                ArrayList<Centro> listaCentros = modelo.getListaCentros();
                GestionCentros gestionCentros = new GestionCentros(centro, listaMedicos, listaPacientes, listaCentros);
            }
        }
    }

    /**
     * Metodo que acosia un ActionEvent a los diferentes ActionComand
     * de los botones de la aplicacion.
     *
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        String comando = event.getActionCommand();
        switch (comando) {
            case "nuevoMedico": {
                nuevoMedico();
                break;
            }
            case "nuevoPaciente": {
                nuevoPaciente();
                break;
            }
            case "nuevoCentro": {
                nuevoCentro();
                break;
            }

            case "eliminarMedico": {
                eliminarMedico();
                break;
            }
            case "eliminarPaciente": {
                eliminarPaciente();
                break;
            }
            case "eliminarCentro": {
                eliminarCentro();
                break;

            }
            case "subirImagen": {
                subirImagenCentro();
                break;
            }
            case "GuardarDatos": {
                guardarDatos();
                break;
            }
            case "cargarDatos": {
                cargarDatos();
                break;
            }
            case "Ajustes": {
                Preferencias preferencias = new Preferencias();
                break;
            }
            case "cargarMenu": {
                cargarDatos();
                break;
            }
            case "guardarMenu": {
                guardarDatos();
                break;
            }
            case "ConfigApp": {
                Preferencias preferencias = new Preferencias();
                break;
            }
            case "SalirAplicacion": {
                int opc = Util.mostrarDialogoSiNo(bundle.getString("Salir.Aplicacion.Pregunta"));
                if (opc == JOptionPane.YES_OPTION) {
                    System.exit(2);
                }
                break;
            }
            case "modificarMedico": {
                Medico medico = ventana.listMedicos.getSelectedValue();
                mofificarMedico(medico);
                refrescarListaMedicos();
                refrescarComboboxCentros();
                break;
            }
            case "ModificarPaciente": {
                Paciente paciente = ventana.listPacientes.getSelectedValue();
                modificarPaciente(paciente);
                refrescarListaPacientes();
                break;
            }
            case "modificarCentro": {
                Centro centro = ventana.listCentros.getSelectedValue();
                modificarCentro(centro);
                refrescarListaCentros();
                break;
            }
            case "botDeleteFoto": {
                eliminarFotoCentro();
                break;
            }

            case "grafico1": {
                pacientesPorCentroGrafico();
                break;
            }
            case"DosTiposGraficas":{
                DialogoDiferentesGraficas dialogoDiferentesGraficas = new DialogoDiferentesGraficas(modelo);
            }
        }
    }

    /**
     * Metodo que genera un gráfico de barras en el cual
     * el eje de las x recoge los centros de salud que estan
     * dados de alta en la aplicacion y en el eje y el numero
     * de pacientes que hay por cada centro de salud.
     */
    private void pacientesPorCentroGrafico() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ArrayList<Paciente> listaPacientes = modelo.getListaPacientes();
        int contador = 0;
        for (Paciente paciente : listaPacientes) {
            Centro centro = paciente.getCentro();
            for (int i = 0; i < listaPacientes.size(); i++) {
                if (centro == listaPacientes.get(i).getCentro()) {
                    contador++;
                }
            }
            dataset.setValue(contador, bundle.getString("leyenda.grafica.pacientes.por.centros"), centro.getNombreCentro());
            contador = 0;
        }
        JFreeChart grafica = ChartFactory.createBarChart(bundle.getString("titulo.grafica.pacientes.por.centros"),
                bundle.getString("axis.label.1"), bundle.getString("axis.label.2"), dataset, PlotOrientation.VERTICAL, true, false, false);
        ChartFrame vista = new ChartFrame(bundle.getString("leyenda.grafica.pacientes.por.centros"), grafica);
        vista.pack();
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    /**
     * Meotodo que elimina la foto del centro seleccionado en el
     * caso de que la lista de centros tenga algún elemento y en
     * el caso de que no este ningun elemento seleccionado le
     * indica al usuario con un mensaje de error que no hay
     * ningun elemento seleccionado
     */
    private void eliminarFotoCentro() {


        if (ventana.dlmCentro.isEmpty()) {

            Util.mensajeError(bundle.getString("list.center.empty"));
        } else {
            if (ventana.listCentros.getSelectedValue() == null) {

                Util.mensajeError(bundle.getString("no.center.seleccionado"));
            } else {

                int opc = Util.mostrarDialogoSiNo(bundle.getString("pregunta.eliminar.centro.foto"));
                if (opc == JOptionPane.YES_OPTION) {
                    ImageIcon imagenIconoDefault = new ImageIcon("imagenes/default.png");
                    Image imagen = imagenIconoDefault.getImage();
                    int ancho = 97;
                    int alto = 97;
                    Image redimensionada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                    imagenIconoDefault = new ImageIcon(redimensionada);
                    ventana.lblImagen.setIcon(imagenIconoDefault);
                }
            }
        }

    }

    /**
     * Metodo que modifica los atributos de un centro de salud seleccionado
     * en la DefaultListModel de centros.
     *
     * @param centro
     */
    private void modificarCentro(Centro centro) {
        if (!ventana.dlmCentro.isEmpty()) {
            if (centro != null) {
                String nombre = ventana.txtNombreCentro.getText();
                String localidad = ventana.txtLocalidad.getText();
                int capacidad = (Integer) ventana.spinnerCapacidad.getValue();
                boolean esPrivado = ventana.cbPrivado.isSelected();
                Icon imagen = ventana.lblImagen.getIcon();

                if (!nombre.isEmpty()) {
                    centro.setNombreCentro(nombre);
                }
                if (!localidad.isEmpty()) {
                    centro.setLocalidadCentro(localidad);
                }
                if (capacidad > 0) {
                    centro.setCapacidad(capacidad);
                } else {
                    Util.mensajeError(bundle.getString("modificar.capacidad.centro"));
                }
                if (esPrivado) {
                    centro.setEsPrivado(true);
                } else {
                    centro.setEsPrivado(false);
                }
                if (imagen != null) {
                    centro.setImagenCentro(imagen);
                }
            }


        } else {
            Util.mensajeError(bundle.getString("list.center.empty"));
        }
    }

    /**
     * Metodo que modifica los atributos de un paciente
     *
     * @param paciente
     */
    private void modificarPaciente(Paciente paciente) {
        boolean modificado = false;
        String dni = ventana.txtDniPaciente.getText();
        String nombre = ventana.txtNombrePaciente.getText();
        String apellidos = ventana.txtApellidosPaciente.getText();
        LocalDate fecha = ventana.pacienteDatePicker.getDate();

        Medico medico = (Medico) ventana.cbMedicoDePaciente.getSelectedItem();
        Centro centro = (Centro) ventana.cbCentroPaciente.getSelectedItem();
        double pesoPasiente = 0;

        if (!ventana.dlmPaciente.isEmpty()) {
            if (ventana.listPacientes.getSelectedValue() == null) {
                Util.mensajeError(bundle.getString("seleccione.paciente.de.lista"));
            } else {
                try {
                    pesoPasiente = Double.valueOf(ventana.txtPesoPaciente.getText());

                } catch (NumberFormatException e) {
                    Util.mensajeError(bundle.getString("debe.ingregar.numero"));
                    modificado = false;
                }

                if (paciente != null) {
                    if (!dni.isEmpty()) {
                        modificado = true;
                    }
                    if (!nombre.isEmpty()) {
                        modificado = true;
                    }
                    if (!apellidos.isEmpty()) {
                        modificado = true;
                    }
                    if (fecha != null) {
                        modificado = true;
                    } else {
                        fecha = paciente.getFechaNacimiento();
                    }
                    if (medico != null) {
                        modificado = true;
                    }
                    if (centro != null) {
                        modificado = true;
                    }
                    if (pesoPasiente > 0) {
                        modificado = true;
                    } else {
                        modificado = false;
                    }

                } else {

                    Util.mensajeError(bundle.getString("PatientListEmpty"));
                }

                if (modificado) {

                    int opcion = Util.mostrarDialogoSiNo(bundle.getString("modificar.paciente"));

                    if (opcion == Util.ACEPTAR) {
                        paciente.setDni(dni);
                        paciente.setNombre(nombre);
                        paciente.setApellidos(apellidos);
                        paciente.setFechaNacimiento(fecha);
                        paciente.setMedico(medico);
                        paciente.setCentro(centro);
                        paciente.setPeso(pesoPasiente);
                        Util.mostrarEjecucionCorrecta(bundle.getString("modificacion.correcta"));
                    } else {

                        Util.mensajeError(bundle.getString("no.modificar.paciente"));
                    }
                }
            }
        } else {
            Util.mensajeError(bundle.getString("PatientListEmpty"));
        }
    }

    /**
     * Metodo que modifica los atributos de un medico
     *
     * @param medicoAModificar
     */
    private void mofificarMedico(Medico medicoAModificar) {
        if (medicoAModificar != null) {
            String dni = ventana.txtDniMedico.getText();
            String nombre = ventana.txtNombreMedico.getText();
            String apellidos = ventana.txtApellidosMedico.getText();
            int edad = (Integer) ventana.spinnerEdadMedico.getValue();
            boolean esHombre = ventana.hombreRadioButton.isSelected();
            Centro centro = (Centro) ventana.cbCentroMedico.getSelectedItem();
            boolean seModifica = false;
            if (!dni.isEmpty()) {
                seModifica = true;
            }

            if (!nombre.isEmpty()) {
                seModifica = true;
            }

            if (!apellidos.isEmpty()) {
                seModifica = true;
            }
            if (edad > 0) {
                seModifica = true;
            } else {
                Util.mensajeError(bundle.getString("modificar.edad.medico"));
            }


            if (medicoAModificar.getEsHombre() == esHombre) {
                medicoAModificar.setEsHombre(true);
            } else {
                seModifica = true;
            }

            if (medicoAModificar.getCentro() != centro) {
                seModifica = true;
            }

            if (seModifica) {
                int opcion = Util.mostrarDialogoSiNo(bundle.getString("DoctorModicicacionConfirm"));
                if (opcion == Util.ACEPTAR) {
                    medicoAModificar.setDni(dni);
                    medicoAModificar.setNombre(nombre);
                    medicoAModificar.setApellido(apellidos);
                    medicoAModificar.setEdad(edad);
                    medicoAModificar.setEsHombre(false);
                    medicoAModificar.setCentro(centro);
                    Util.mostrarEjecucionCorrecta(bundle.getString("modificacion.correcta"));

                } else {
                    Util.mensajeError(bundle.getString("modificacion.medico.incorrecta"));
                }
            }
        } else {
            if (ventana.JTabbedPane.getSelectedIndex() == 0) {
                Util.mensajeError(bundle.getString("seleccione.medico"));
            }
        }
    }


    /**
     * Metodo que muestra un dialogo de seleccion de fichero para cargar los datos
     */

    private void cargarDatos() {
        JFileChooser fileChooser = new JFileChooser();
        int opt = fileChooser.showOpenDialog(ventana.frame);
        if (opt == JFileChooser.APPROVE_OPTION) {
            try {
                modelo.cargarDatos(fileChooser.getSelectedFile());

                refrescarListaPacientes();
                refrescarListaCentros();
                refrescarListaMedicos();
                refrescarComboboxCentros();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo que limpia los campos de los JTexField de medicos, borrando lo que se
     * escribio en ellos
     */
    private void limpiarCamposMedico() {
        ventana.txtDniMedico.setText("");
        ventana.txtNombreMedico.setText("");
        ventana.txtApellidosMedico.setText("");
        ventana.spinnerEdadMedico.setValue(0);
    }

    /**
     * Metodo que limpia los campos de los JTexField de pacientes, borrando lo que se
     * escribio en ellos
     */
    private void limpiarCamposPaciente() {
        ventana.txtDniPaciente.setText("");
        ventana.txtNombrePaciente.setText("");
        ventana.txtApellidosPaciente.setText("");
        ventana.txtPesoPaciente.setText("");
        ventana.pacienteDatePicker.setText("");
    }

    /**
     * Metodo que limpia los campos de los JTexField de centros, borrando lo que se
     * escribio en ellos
     */
    private void limpiarCamposCentro() {
        ventana.txtNombreCentro.setText("");
        ventana.txtLocalidad.setText("");
        ventana.spinnerCapacidad.setValue(0);
        Icon icon = null;
        ventana.lblImagen.setIcon(null);

    }


    /**
     * Metodo que comprueba en que ventana nos encontramos para obtener
     * los valores que se muestran en esa ventanta
     *
     * @param listSelectionEvent
     */
    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {

        if (ventana.JTabbedPane.getSelectedIndex() == 1) {
            Paciente paciente = ventana.listPacientes.getSelectedValue();
            if (paciente != null) {
                mostrarValoresPaciente(paciente);

            } else {
                limpiarCamposPaciente();

            }
        } else if (ventana.JTabbedPane.getSelectedIndex() == 0) {
            Medico medico = ventana.listMedicos.getSelectedValue();
            if (medico != null) {
                mostarValoresMedico(medico);
            } else {
                limpiarCamposMedico();
            }
        } else {
            Centro centro = ventana.listCentros.getSelectedValue();
            if (centro != null) {
                mostrarValoresCentro(centro);
            } else {
                limpiarCamposCentro();
            }
        }
    }

    /**
     * Metodo que escribe en los JTextField el valor de los atributos del paciente
     *
     * @param paciente
     */
    private void mostrarValoresPaciente(Paciente paciente) {
        ventana.txtNombrePaciente.setText(paciente.getNombre());
        ventana.txtDniPaciente.setText(paciente.getDni());
        ventana.txtApellidosPaciente.setText(paciente.getApellidos());
        ventana.txtPesoPaciente.setText(Double.toString(paciente.getPeso()));
        ventana.dcbmCentro.setSelectedItem(paciente.getCentro());
        ventana.dcbmMedico.setSelectedItem(paciente.getMedico());
        ventana.pacienteDatePicker.setDate(paciente.getFechaNacimiento());
    }

    /**
     * Metodo escribe en los JTextField el valor de los atributos del centro
     *
     * @param centro
     */
    private void mostrarValoresCentro(Centro centro) {
        ventana.txtNombreCentro.setText(centro.getNombreCentro());
        ventana.txtLocalidad.setText(centro.getLocalidadCentro());
        ventana.spinnerCapacidad.setValue(centro.getCapacidad());

        if (centro.getEsPrivado()) {
            ventana.cbPrivado.setSelected(true);
        } else {
            ventana.cbPrivado.setSelected(false);
        }
        if (centro.getImagenCentro() == null) {

            ImageIcon imagenIconoDefault = new ImageIcon("imagenes/default.png");
            Image imagen = imagenIconoDefault.getImage();
            int ancho = 97;
            int alto = 97;
            Image redimensionada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            imagenIconoDefault = new ImageIcon(redimensionada);
            ventana.lblImagen.setIcon(imagenIconoDefault);
        } else {
            ventana.lblImagen.setIcon(centro.getImagenCentro());
        }


    }

    /**
     * Metodo que escribe en los JTextField el valor de los atributos del medico
     *
     * @param medico
     */
    private void mostarValoresMedico(Medico medico) {
        ventana.txtDniMedico.setText(medico.getDni());
        ventana.txtNombreMedico.setText(medico.getNombre());
        ventana.txtApellidosMedico.setText(medico.getApellido());
        ventana.spinnerEdadMedico.setValue(medico.getEdad());

        if (medico.getEsHombre()) {
            ventana.hombreRadioButton.setSelected(true);
        } else {
            ventana.mujerRadioButton.setSelected(true);
        }
        ventana.dcbmCentro.setSelectedItem(medico.getCentro());


    }

    /**
     * Metodo que muestra un dialogo de seleccion de fichero para guardar los datos
     */
    private void guardarDatos() {
        JFileChooser fileChooser = new JFileChooser();
        int opt = fileChooser.showSaveDialog(ventana.frame);
        if (opt == JFileChooser.APPROVE_OPTION) {
            try {
                modelo.guardarDatos(fileChooser.getSelectedFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo que se encarga de subir una imagen y cargarla
     * en la lblImagen del centro, además realiza una copia
     * de la imagen en un ruta llamada imgCentros
     */
    private void subirImagenCentro() {
        JFileChooser fileChooser = new JFileChooser();
        int opc = fileChooser.showOpenDialog(null);

        if (opc == JFileChooser.APPROVE_OPTION) {
            File directorio = new File("imgCentros");
            directorio.mkdir();
            File fotoSeleccionada = fileChooser.getSelectedFile();
            File destinoFoto = new File(directorio.getAbsolutePath() + "/" + fotoSeleccionada.getName());
            String ruta = fotoSeleccionada.getAbsolutePath();

            ImageIcon imagenIcono = new ImageIcon(ruta);
            Image imagen = imagenIcono.getImage();
            int ancho = 97;
            int alto = 97;
            Image redimensionada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            imagenIcono = new ImageIcon(redimensionada);
            try {
                Files.copy(fotoSeleccionada.toPath(), destinoFoto.toPath(), StandardCopyOption.REPLACE_EXISTING);
                ventana.lblImagen.setIcon(imagenIcono);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metodo que elimina a un centro de la lista de centros
     * comprobando si la lista de centros no se encuentre vacia
     * o en caso de que la lista no esta vacia, que el usuario
     * haya seleccionado un centro a eliminar.
     */
    private void eliminarCentro() {
        int contadorM = 0;
        int contadorP = 0;
        ArrayList listaCentros = modelo.getListaCentros();
        if (listaCentros.isEmpty()) {
            Util.mensajeError(bundle.getString("CenterListEmpty"));
        } else {
            Centro eliminado = ventana.listCentros.getSelectedValue();
            if (eliminado != null) {
                for (Medico m : modelo.getListaMedicos()) {
                    if (m.getCentro() == eliminado) {
                        contadorM = contadorM + 1;
                    }
                }
                for (Paciente p : modelo.getListaPacientes()) {
                    if (p.getCentro() == eliminado) {
                        contadorP = contadorP + 1;
                    }
                }
                if (contadorP == 0 && contadorM == 0) {
                    int opcion = Util.mostrarDialogoSiNo(bundle.getString("DeleteCenterConfirm"));
                    if (opcion == Util.ACEPTAR) {
                        listaCentros.remove(eliminado);
                        ventana.dlmCentro.removeElement(eliminado);
                        Util.mostrarEjecucionCorrecta(bundle.getString("DeleteCenterOk"));
                        refrescarComboboxCentros();
                    } else {
                        Util.mensajeError(bundle.getString("DeleteCenterNotOk"));
                    }
                } else {
                    Util.mensajeError(bundle.getString("no.eliminar.centros.con.objetos"));
                }

            } else {
                Util.mensajeError(bundle.getString("CenterNotSelected"));
            }
        }
    }

    /**
     * Metodo que elimina a un paciente de la lista de pacientes
     * comprobando si la lista de paciente no se encuentre vacia
     * o en caso de que la lista no esta vacia, que el usuario
     * haya seleccionado un paciente a eliminar.
     */
    private void eliminarPaciente() {
        ArrayList listaPacientes = modelo.getListaPacientes();
        if (listaPacientes.isEmpty()) {

            Util.mensajeError(bundle.getString("PatientListEmpty"));
        } else {
            Paciente eliminado = ventana.listPacientes.getSelectedValue();
            if (eliminado != null) {
                int opcion = Util.mostrarDialogoSiNo(bundle.getString("DeletePatientConfirm"));
                if (opcion == Util.ACEPTAR) {
                    listaPacientes.remove(eliminado);
                    ventana.dlmPaciente.removeElement(eliminado);
                    Util.mostrarEjecucionCorrecta(bundle.getString("DeletePatientOk"));
                } else {
                    Util.mensajeError(bundle.getString("DeletePatientNotOk"));
                }
            } else {
                Util.mostrarEjecucionCorrecta(bundle.getString("PatientNotSelected"));
            }
        }
    }

    /**
     * Metodo que elimina a un medico de la lista de medicos
     * comprobando si la lista de medicos no se encuentre vacia
     * o en caso de que la lista no esta vacia, que el usuario
     * haya seleccionado un medico a eliminar.
     */
    private void eliminarMedico() {
        int contador = 0;
        ArrayList listaMedicos = modelo.getListaMedicos();
        if (listaMedicos.isEmpty()) {
            Util.mensajeError(bundle.getString("DoctorEmptyList"));
        } else {
            Medico eliminado = ventana.listMedicos.getSelectedValue();
            Centro centroDelMedico = eliminado.getCentro();
            if (eliminado != null) {
                for (Paciente p : modelo.getListaPacientes()) {
                    if (p.getMedico() == eliminado) {
                        contador = contador + 1;
                    }
                }
                if (contador == 0) {
                    int opcion = Util.mostrarDialogoSiNo(bundle.getString("DoctorDeleteConfirm"));
                    if (opcion == Util.ACEPTAR) {
                        listaMedicos.remove(eliminado);
                        ventana.dlmMedico.removeElement(eliminado);
                        Util.mostrarEjecucionCorrecta(bundle.getString("DoctorDeleteOk"));
                        actualizarComboBoxMedicosPorCentro(centroDelMedico);

                    } else {
                        Util.mensajeError(bundle.getString("DoctorDeleteNotOk"));
                    }
                } else {
                    Util.mensajeError(bundle.getString("no.eliminar.medicos.con.objetos"));
                }

            } else {
                Util.mensajeError(bundle.getString("DoctorNotSelected"));
            }
        }
    }

    /**
     * Metodo que se encarga de dar de alta un nuevo centro de salud
     * descartando aquellos que esten repetidos y cuando los campos
     * no esten en blanco
     */
    private void nuevoCentro() {
        Icon imagenCentro = ventana.lblImagen.getIcon();
        String nombreCentro = ventana.txtNombreCentro.getText();
        String localidadCentro = ventana.txtLocalidad.getText();
        int capacidad = (Integer) ventana.spinnerCapacidad.getValue();
        boolean esPrivado = ventana.cbPrivado.isSelected();
        boolean nombreRepetido = false;
        boolean error = false;

        if (nombreCentro.isEmpty()) {
            error = true;
            ventana.txtNombreCentro.setBackground(Color.red);
        }
        for (Centro centro : modelo.getListaCentros()) {
            if (ventana.txtNombreCentro.getText().equals(centro.getNombreCentro())) {
                nombreRepetido = true;
            }
        }
        if (localidadCentro.isEmpty()) {
            error = true;
            ventana.txtLocalidad.setBackground(Color.red);
        }

        if (capacidad <= 0) {
            error = true;
            ventana.spinnerCapacidad.getEditor().getComponent(0).setForeground(Color.red);
        }

        if (error) {
            Util.mensajeError(bundle.getString("ErrorRedFields"));
        } else {
            if (nombreRepetido) {
                Util.mensajeError(bundle.getString("RepeatCenter"));
            } else {
                Centro centro = new Centro(imagenCentro, nombreCentro, localidadCentro, capacidad, esPrivado);
                modelo.altaCentro(centro);
                limpiarCamposCentro();
            }
            refrescarListaCentros();
            refrescarComboboxCentros();
        }

    }

    /**
     * Metodo que se encarga de dar de alta un paciente, tras comprobar que los campos
     * no estan en blanco y que el DNI no esta repetido
     */
    private void nuevoPaciente() {
        boolean error = false;
        boolean repetidoDni = false;
        String dni = ventana.txtDniPaciente.getText();
        String nombre = ventana.txtNombrePaciente.getText();
        String apellidos = ventana.txtApellidosPaciente.getText();
        LocalDate fechaNacimiento = ventana.pacienteDatePicker.getDate();
        String peso = ventana.txtPesoPaciente.getText();

        Medico medicoAsignado = (Medico) ventana.cbMedicoDePaciente.getSelectedItem();
        Centro centroAsignado = (Centro) ventana.cbCentroPaciente.getSelectedItem();
        if (dni.isEmpty()) {
            error = true;
            ventana.txtDniPaciente.setBackground(Color.red);
        }

        if (nombre.isEmpty()) {
            error = true;
            ventana.txtNombrePaciente.setBackground(Color.red);
        }

        if (apellidos.isEmpty()) {
            error = true;
            ventana.txtApellidosPaciente.setBackground(Color.red);
        }

        double pesoPaciente = 0;
        try {
            pesoPaciente = Double.parseDouble(peso);
            if (pesoPaciente <= 0) {
                error = true;
                ventana.txtPesoPaciente.setBackground(Color.red);
            }
        } catch (NumberFormatException e) {
            error = true;
            ventana.txtPesoPaciente.setBackground(Color.red);
        }

        if (fechaNacimiento == null) {
            error = true;
            ventana.pacienteDatePicker.getComponentDateTextField().setBackground(Color.red);
        }

        boolean errrorCombo = false;
        if (ventana.cbCentroPaciente.getSelectedItem() == null) {
            errrorCombo = true;
            ventana.cbCentroPaciente.setBackground(Color.orange);
        }

        if (ventana.cbMedicoDePaciente.getSelectedItem() == null) {
            errrorCombo = true;
            ventana.cbMedicoDePaciente.setBackground(Color.orange);
        }

        //Comprobar si el dni del paciente se repite
        for (Paciente p : modelo.getListaPacientes()) {
            if (p.getDni().equals(ventana.txtDniPaciente.getText().trim())) {
                repetidoDni = true;
            }
        }

        if (repetidoDni) {
            Util.mensajeError(bundle.getString("DniRepeat"));
        }

        if (error) {
            Util.mensajeError(bundle.getString("ErrorRedFields"));
        } else {
            if (errrorCombo) {
                if (modelo.getListaCentros().size() == 0 || centroAsignado == null || modelo.getListaMedicos() == null || medicoAsignado == null) {
                    Util.mensajeError(bundle.getString("CenterAndDoctorOfPatient"));
                }
            } else {
                if (!repetidoDni) {
                    Paciente paciente = new Paciente(dni, nombre, apellidos, fechaNacimiento, Double.parseDouble(peso), medicoAsignado, centroAsignado);
                    modelo.altaPaciente(paciente);
                    limpiarCamposPaciente();
                }
                refrescarListaPacientes();
            }
        }

    }


    /**
     * Metodo que se encarga de dar de alta un medico, comprobando que los
     * campos que introduce el usuario no estan en blanco y que el dni del
     * medico a dar de alta no esta repetido.
     */
    private void nuevoMedico() {
        boolean error = false;
        boolean repetidoDni = false;
        String dni = ventana.txtDniMedico.getText();
        String nombre = ventana.txtNombreMedico.getText();
        String apellidos = ventana.txtApellidosMedico.getText();
        int edad = (Integer) ventana.spinnerEdadMedico.getValue();
        boolean esHombre = ventana.hombreRadioButton.isSelected();
        Centro centro = (Centro) ventana.cbCentroMedico.getSelectedItem();

        if (dni.isEmpty()) {
            error = true;
            ventana.txtDniMedico.setBackground(Color.red);
        }
        if (nombre.isEmpty()) {
            error = true;
            ventana.txtNombreMedico.setBackground(Color.red);
        }
        if (apellidos.isEmpty()) {
            error = true;
            ventana.txtApellidosMedico.setBackground(Color.red);
        }
        if (edad <= 0) {
            error = true;
            ventana.spinnerEdadMedico.getEditor().getComponent(0).setForeground(Color.red);
        }

        for (Medico m : modelo.getListaMedicos()) {
            if (m.getDni().equals(ventana.txtDniMedico.getText().trim())) {
                repetidoDni = true;
            }
        }
        if (repetidoDni) {
            Util.mensajeError(bundle.getString("DniRepeat"));
            ventana.txtDniMedico.setBackground(Color.red);
        }
        if (error) {
            Util.mensajeError(bundle.getString("ErrorRedFields"));
        } else {
            if (modelo.getListaCentros().size() == 0 || centro == null) {

                Util.mensajeError(bundle.getString("CenterOfDoctor"));
            } else {
                if (!repetidoDni) {
                    modelo.altaMedico(dni, nombre, apellidos, edad, esHombre, centro);
                    refrescarListaMedicos();
                    actualizarComboBoxMedicosPorCentro(centro);
                }

            }
        }
    }

    /**
     * Metodo que se encarga de mantener actualizada la lista de centros
     * con todos los centros que hay en la lista de centros.
     */
    private void refrescarListaCentros() {
        ventana.dlmCentro.clear();
        for (Centro c : modelo.getListaCentros())
            ventana.dlmCentro.addElement(c);
    }


    /**
     * Metodo que actualiza la lista de los medicos con todos
     * los medicos que contiene listaMedicos
     */
    private void refrescarListaMedicos() {
        ventana.dlmMedico.removeAllElements();

        for (Medico medico : modelo.getListaMedicos()) {
            ventana.dlmMedico.addElement(medico);
        }
    }

    /**
     * Metodo que refresca la lista de pacientes para que este actualizada
     */
    private void refrescarListaPacientes() {
        ventana.dlmPaciente.clear();
        for (Paciente p : modelo.getListaPacientes())
            ventana.dlmPaciente.addElement(p);
    }

    /**
     * Metodo que refresca el comobox de centros para que este actualizado
     */
    private void refrescarComboboxCentros() {
        ventana.dcbmCentro.removeAllElements();
        for (Centro centro : modelo.getListaCentros())
            ventana.dcbmCentro.addElement(centro);

    }

    /**
     * Metodo que actualiza el comboBox de Medicos en funcion
     * al centro que este seleccionado en en combobox de centros
     *
     * @param centro
     */
    private void actualizarComboBoxMedicosPorCentro(Centro centro) {
        ArrayList<Medico> listaMedicos = new ArrayList<Medico>();
        for (Medico m : modelo.getListaMedicos()) {
            if (centro.equals(m.getCentro())) {
                listaMedicos.add(m);
            }
        }
        ventana.cbMedicoDePaciente.removeAllItems();
        for (Medico m : listaMedicos)
            ventana.cbMedicoDePaciente.addItem(m);
    }

    /**
     * Metodo que asigna un Listener de tipo FocusEvent
     * a elementos con los que el usuiario interactua
     * para cambiar el color a blanco si ganan el foco
     *
     * @param e
     */
    @Override
    public void focusGained(FocusEvent e) {
        //FocusGained Medico
        if (ventana.txtDniMedico.hasFocus()) {
            ventana.txtDniMedico.setBackground(Color.white);
        }
        if (ventana.txtNombreMedico.hasFocus()) {
            ventana.txtNombreMedico.setBackground(Color.white);
        }
        if (ventana.txtApellidosMedico.hasFocus()) {
            ventana.txtApellidosMedico.setBackground(Color.white);
        }
        if (Integer.parseInt(String.valueOf(ventana.spinnerEdadMedico.getValue())) > 0) {
            ventana.spinnerEdadMedico.getEditor().getComponent(0).setForeground(Color.black);
        }
        if (Integer.parseInt(String.valueOf(ventana.spinnerEdadMedico.getValue())) <= 0) {
            ventana.spinnerEdadMedico.getEditor().getComponent(0).setForeground(Color.red);
        } else {
            ventana.spinnerEdadMedico.getEditor().getComponent(0).setForeground(Color.black);
        }
        //FocusGained Pacientes

        if (ventana.txtDniPaciente.hasFocus()) {
            ventana.txtDniPaciente.setBackground(Color.white);
        }
        if (ventana.txtNombrePaciente.hasFocus()) {
            ventana.txtNombrePaciente.setBackground(Color.white);
        }
        if (ventana.txtApellidosPaciente.hasFocus()) {
            ventana.txtApellidosPaciente.setBackground(Color.white);
        }
        if (ventana.txtPesoPaciente.hasFocus()) {
            ventana.txtPesoPaciente.setBackground(Color.white);
        }

        if (ventana.cbMedicoDePaciente.hasFocus()) {
            ventana.cbMedicoDePaciente.setBackground(Color.white);
        }
        if (ventana.cbCentroPaciente.hasFocus()) {
            ventana.cbCentroPaciente.setBackground(Color.white);
        }

        //FocusGained

        if (ventana.txtNombreCentro.hasFocus()) {
            ventana.txtNombreCentro.setBackground(Color.white);
        }
        if (ventana.txtLocalidad.hasFocus()) {
            ventana.txtLocalidad.setBackground(Color.white);
        }

        if (Integer.parseInt(String.valueOf(ventana.spinnerCapacidad.getValue())) <= 0) {
            ventana.spinnerCapacidad.getEditor().getComponent(0).setForeground(Color.red);
        } else {
            ventana.spinnerCapacidad.getEditor().getComponent(0).setForeground(Color.black);
        }


    }

    /**
     * Metodo que asigna un Listener de tipo FocusEvent
     * a elementos con los que el usuiario interactua
     * para cambiar el color a rojo si estos pierden el
     * foco en el caso de  que algun elemento este en blanco
     * o los valores numericos no sean correctos.
     *
     * @param e
     */
    @Override
    public void focusLost(FocusEvent e) {
        //FocusLost Medicos
        if (!ventana.txtDniMedico.hasFocus() && ventana.txtDniMedico.getText().isEmpty()) {
            ventana.txtDniMedico.setBackground(Color.red);
        }
        if (!ventana.txtNombreMedico.hasFocus() && ventana.txtNombreMedico.getText().isEmpty()) {
            ventana.txtNombreMedico.setBackground(Color.red);
        }
        if (!ventana.txtApellidosMedico.hasFocus() && ventana.txtApellidosMedico.getText().isEmpty()) {
            ventana.txtApellidosMedico.setBackground(Color.red);
        }

        if (Integer.parseInt(String.valueOf(ventana.spinnerEdadMedico.getValue())) <= 0) {
            ventana.spinnerEdadMedico.getEditor().getComponent(0).setForeground(Color.red);
        } else {
            ventana.spinnerEdadMedico.getEditor().getComponent(0).setForeground(Color.black);
        }

        //FocusLost Pacientes

        if (!ventana.txtDniPaciente.hasFocus() && ventana.txtDniPaciente.getText().isEmpty()) {
            ventana.txtDniPaciente.setBackground(Color.red);
        }
        if (!ventana.txtNombrePaciente.hasFocus() && ventana.txtNombrePaciente.getText().isEmpty()) {
            ventana.txtNombrePaciente.setBackground(Color.red);
        }
        if (!ventana.txtApellidosPaciente.hasFocus() && ventana.txtApellidosPaciente.getText().isEmpty()) {
            ventana.txtApellidosPaciente.setBackground(Color.red);
        }
        if (ventana.txtPesoPaciente.getText().isEmpty()) {
            ventana.txtPesoPaciente.setBackground(Color.red);
        } else {
            try {
                double peso = Double.parseDouble(ventana.txtPesoPaciente.getText());
                if (peso <= 0) {
                    ventana.txtPesoPaciente.setForeground(Color.red);
                } else {
                    ventana.txtPesoPaciente.setForeground(Color.black);
                }
            } catch (NumberFormatException error) {
                ventana.txtPesoPaciente.setForeground(Color.red);
            }
        }

        //FocusLost Centro
        if (!ventana.txtNombreCentro.hasFocus() && ventana.txtNombreCentro.getText().isEmpty()) {
            ventana.txtNombreCentro.setBackground(Color.red);
        }
        if (!ventana.txtLocalidad.hasFocus() && ventana.txtLocalidad.getText().isEmpty()) {
            ventana.txtLocalidad.setBackground(Color.red);
        }
    }

    /**
     * Metodo que asocia un Listener de tipo ItemEvent para
     * permitir que el combo box de medicos en funcion al centro
     * de salud seleccionado se pueda actualizar.
     *
     * @param e
     */
    @Override
    public void itemStateChanged(ItemEvent e) {

        actualizarComboBoxMedicosPorCentro((Centro) e.getItem());
    }

    /**
     * Metodo que crea el directorio data y el fichero preferencias.conf
     * al iniciar la aplicacion y setea un idioma y pais por defecto y
     * posteriormente los valores que esten en el fichero preferencias.conf
     */
    private void cambiarColores() {
        Properties properties = new Properties();
        File d = new File("./data");
        File documento = new File(d, "preferencias.conf");

        if (!d.exists()) {
            d.mkdir();
        }
        if (documento.exists()) {
            try {
                properties.load(new FileReader(documento));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Color color = new Color(Integer.parseInt(
                    (properties.getProperty("color") != null) ? properties.getProperty("color") : String.valueOf(Color.WHITE)
            )
            );
            ventana.panel1.setBackground(color);
            ventana.panelMedico.setBackground(color);
            ventana.panelCentro.setBackground(color);
            ventana.panelPaciente.setBackground(color);
            ventana.datosCentro.setBackground(color);
            ventana.datosMedico.setBackground(color);
            ventana.datosPaciente.setBackground(color);
            ventana.panelDeBotones.setBackground(color);
            ventana.panelBotonesPacientes.setBackground(color);
            ventana.listaPacientesPanel.setBackground(color);
            ventana.panelListaCentros.setBackground(color);
            ventana.datosImagenCentro.setBackground(color);
            ventana.panelBotonesCentros.setBackground(color);
        } else {
            try {
                documento.createNewFile();
                properties.setProperty("color", String.valueOf(Color.white.getRGB()));
                properties.setProperty("idioma", "es");
                properties.setProperty("pais", "ES");
                try {
                    properties.store(new FileWriter(documento),"Documento de Preferencias");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    properties.load(new FileReader(documento));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Color color = new Color(Integer.parseInt(properties.getProperty("color")));
                ventana.panel1.setBackground(color);
                ventana.panelMedico.setBackground(color);
                ventana.panelCentro.setBackground(color);
                ventana.panelPaciente.setBackground(color);
                ventana.datosCentro.setBackground(color);
                ventana.datosMedico.setBackground(color);
                ventana.datosPaciente.setBackground(color);
                ventana.panelDeBotones.setBackground(color);
                ventana.panelBotonesPacientes.setBackground(color);
                ventana.listaPacientesPanel.setBackground(color);
                ventana.panelListaCentros.setBackground(color);
                ventana.datosImagenCentro.setBackground(color);
                ventana.panelBotonesCentros.setBackground(color);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}