package dialogos;

import base.Centro;
import base.Medico;
import base.Paciente;
import util.Util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * La clase GestionCentros tiene la finalidad de dar la opcion al usuario
 * de tener acceso a todos los datos de la aplicacion tanto medicos como pacientes
 *
 * @author Raquel Molina Diaz
 * @version 1
 * @since 16/02/2021
 */
public class GestionCentros extends JDialog implements ActionListener {
    private JPanel panel1;
    private JRadioButton rbMedicos;
    private JRadioButton rbPacientes;
    private JLabel lblMedicosPacientesCentro;
    private JLabel lblMedicosPacientesOtrosCentros;
    private JPanel panelListados;

    private JList listaMPCentro;
    private JList listaMPOtros;
    private DefaultListModel<Medico> dlmMedicosCentro;
    private DefaultListModel<Paciente> dlmPacienteCentro;
    private DefaultListModel<Medico> dlmMedicosOtroCentro;
    private DefaultListModel<Paciente> dlmPacienteOtroCentro;

    private JButton botIzquierda;
    private JButton botDerecha;
    private JPanel principal;
    private JPanel panelBotones;
    private JButton buttonOK;
    private Centro centro;
    private ArrayList<Medico> listaMedicos;
    private ArrayList<Paciente> listaPaciente;
    private ArrayList<Centro> listaCentros;
    private ResourceBundle resourceBundle;

    /**
     * Constructor de la clase GestionCentros. Esta clase recoge los medicos que hay en el Centro seleccionado en el JList de la clase Ventana
     * Esta clase se destina a llamar a diferentes clases creadas para modificar las relaciones entre los distintos objetos de una forma centralizada
     *
     * @param centro        el parametro centro hace referencia al centro que se selecciono el el JList de centros
     * @param listaMedico   el parametro listaMedicos hace referencia a la lista de medicos del modelo
     * @param listaPaciente el parametro listaPaciente hace referencia a la lista de pacientes del modelo
     * @param listaCentros  el parametro listaCentros hace referencia a la lista de centros del modelo
     */
    public GestionCentros(Centro centro, ArrayList<Medico> listaMedico, ArrayList<Paciente> listaPaciente, ArrayList<Centro> listaCentros) {
        resourceBundle = ResourceBundle.getBundle("idiomaResourcebundle");
        this.centro = centro;
        this.listaMedicos = listaMedico;
        this.listaPaciente = listaPaciente;
        this.listaCentros = listaCentros;
        activarControlPorTecladoYAceleradores();
        setTitle(centro.getNombreCentro());
        cambiarTituloLabels();
        iniciarListas();
        initActionListener(this);
        initDialog();
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Metodo que inicializa el Dialog
     */
    private void initDialog() {
        setContentPane(panel1);
        setModal(true);
        listarPorSeleccion();
    }

    /**
     * Metodo que asocia un ActionListener a los elementos con los cuales el usuario va a interactuar
     *
     * @param listener el parametro hace referencia al ActionListener que se asocia a los botones
     */
    private void initActionListener(ActionListener listener) {
        botIzquierda.addActionListener(listener);
        botDerecha.addActionListener(listener);
        rbMedicos.addActionListener(listener);
        rbPacientes.addActionListener(listener);
    }

    /**
     * Metodo que asocia un ActionEvent a los diferentes elementos a través de su ActionComand
     *
     * @param e el parametro e asocia un ActionEnent a diferentes botones
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "botIzquierda": {
                agregarMedicoAlCentroActual();
                break;
            }
            case "botDerecha": {
                cambiarMedicosHaciaOtroCentro();
                break;
            }
            case "grupoRB": {
                cambiarTituloLabels();
                dlmMedicosOtroCentro.removeAllElements();
                dlmMedicosCentro.removeAllElements();
                dlmPacienteOtroCentro.removeAllElements();
                dlmPacienteCentro.removeAllElements();
                if (rbMedicos.isSelected()) {
                    listaMPCentro.setModel(dlmMedicosCentro);
                    listaMPOtros.setModel(dlmMedicosOtroCentro);
                } else {
                    listaMPCentro.setModel(dlmPacienteCentro);
                    listaMPOtros.setModel(dlmPacienteOtroCentro);
                }
                listarPorSeleccion();
                break;

            }
        }
    }

    /**
     * Metodo que permite agregar a un medico de otro centro al centro con el que se esta
     * trabajando, para lo cual es necesario asignarle un medico que este en dicho centro
     */
    private void agregarMedicoAlCentroActual() {
        if (rbMedicos.isSelected()) {
            Medico medicoSeleccionado = (Medico) listaMPOtros.getSelectedValue();
            if (medicoSeleccionado != null) {
                medicoSeleccionado.setCentro(centro);
                refrescarListaMedicoCentro();
            } else {
                Util.mensajeError(resourceBundle.getString("seleccion.vacia.medico"));
            }

        } else {
            //paciente seleccionado nom,dni,medi,centro de ese paciente
            Paciente paciente = (Paciente) listaMPOtros.getSelectedValue();
            if (paciente != null) {
                ArrayList<Medico> medicosCentroSeleccionado = new ArrayList<Medico>();
                Centro centroSeleccionado = centro;
                for (Medico m : listaMedicos) {
                    if (m.getCentro() == centroSeleccionado) {
                        medicosCentroSeleccionado.add(m);
                    }
                }
                CambiarPacienteParaElCentroSeleccionado cambiarPacienteCentro = new CambiarPacienteParaElCentroSeleccionado(centroSeleccionado, medicosCentroSeleccionado, paciente);
            } else {
                Util.mensajeError(resourceBundle.getString("seleccion.vacia.paciente"));
            }
        }
    }

    /**
     * Metodo que permite cambiar al paciente de centro
     * creando una instancia de la clase CambiarMedicoCentro
     * ya que hay que reasignar el medico y el centro-
     */
    private void cambiarMedicosHaciaOtroCentro() {
        if (dlmMedicosCentro.size() != 0 || dlmPacienteCentro.size() != 0) {
            if (rbMedicos.isSelected()) {
                Medico medico = (Medico) listaMPCentro.getSelectedValue();
                if (medico != null) {
                    ArrayList<Centro> listaCentro = listaCentros;
                    CambiarMedicoCentro cambiarMedico = new CambiarMedicoCentro(medico, listaCentro);
                    //relistar
                } else {
                    Util.mensajeError(resourceBundle.getString("seleccion.vacia.medico"));
                }

            } else {
                Paciente paciente = (Paciente) listaMPCentro.getSelectedValue();
                if (paciente != null) {
                    ArrayList<Centro> listCentros = new ArrayList<Centro>();
                    ArrayList<Medico> listMedicos = new ArrayList<Medico>();
                    for (Centro c : listaCentros) {
                        listCentros.add(c);
                    }
                    for (Medico m : listaMedicos) {
                        listMedicos.add(m);
                    }
                    CambiarPacienteHaciaOtroCentro modificarPaciente = new CambiarPacienteHaciaOtroCentro(paciente, listMedicos, listCentros, centro);

                } else {
                    //no hay un paciente seleccionado
                    Util.mensajeError(resourceBundle.getString("seleccion.vacia.paciente"));
                }
            }
        } else {
            Util.mensajeError(resourceBundle.getString("lista.vacia"));
        }
    }

    /**
     * Metodo que inicia los DefaultListModel y asocia a las listas un modelo
     */
    private void iniciarListas() {
        dlmMedicosCentro = new DefaultListModel<Medico>();
        dlmPacienteCentro = new DefaultListModel<Paciente>();
        dlmMedicosOtroCentro = new DefaultListModel<Medico>();
        dlmPacienteOtroCentro = new DefaultListModel<Paciente>();

        if (rbMedicos.isSelected()) {
            listaMPCentro.setModel(dlmMedicosCentro);
            listaMPOtros.setModel(dlmMedicosOtroCentro);
            listaMPCentro.revalidate();
        } else {
            listaMPCentro.setModel(dlmPacienteCentro);
            listaMPOtros.setModel(dlmPacienteOtroCentro);
            listaMPCentro.revalidate();
        }
    }

    /**
     * Metodo que activa el control por teclado y los botones por defecto de la clase
     */
    private void activarControlPorTecladoYAceleradores() {
        botDerecha.setMnemonic(KeyEvent.VK_R);
        botIzquierda.setMnemonic(KeyEvent.VK_L);
        getRootPane().setDefaultButton(botDerecha);

    }

    /**
     * Metodo que lista pacientes o medicos en funcion al
     * radioButton que este seleccionado
     */
    private void listarPorSeleccion() {
        if (rbPacientes.isSelected()) {
            dlmPacienteCentro.clear();
            for (Paciente paciente : listaPaciente) {
                if (paciente.getCentro() == centro) {
                    dlmPacienteCentro.addElement(paciente);
                }
            }
            for (Paciente paciente : listaPaciente) {
                if (paciente.getCentro() != centro) {
                    dlmPacienteOtroCentro.addElement(paciente);
                }
            }
        } else {
            for (Medico medico : listaMedicos) {
                if (medico.getCentro() == centro) {
                    dlmMedicosCentro.addElement(medico);
                }
            }
            for (Medico medico : listaMedicos) {
                if (medico.getCentro() != centro) {
                    dlmMedicosOtroCentro.addElement(medico);
                }
            }
        }
    }

    /**
     * Metodo que cambia los titulos de la interfaz en funcion al tipo de objeto
     * que se este listando en las listas
     */
    private void cambiarTituloLabels() {
        if (rbMedicos.isSelected()) {
            lblMedicosPacientesCentro.setText(resourceBundle.getString("medicos.de") + centro.getNombreCentro());
            lblMedicosPacientesOtrosCentros.setText(resourceBundle.getString("medicos.otros.centros.lbl"));
        } else {
            lblMedicosPacientesCentro.setText(resourceBundle.getString("pacientes.de") + centro.getNombreCentro());
            lblMedicosPacientesOtrosCentros.setText(resourceBundle.getString("pacientes.de.otros.centros"));
        }
    }

    /**
     * Metodo que refresca la lista de medicos del centro de salud
     */
    private void refrescarListaMedicoCentro() {
        dlmMedicosCentro.removeAllElements();
        dlmMedicosOtroCentro.removeAllElements();
        for (Medico medico : listaMedicos) {
            if (medico.getCentro() == centro) {
                dlmMedicosCentro.addElement(medico);
            } else {
                dlmMedicosOtroCentro.addElement(medico);
            }
        }
    }

}