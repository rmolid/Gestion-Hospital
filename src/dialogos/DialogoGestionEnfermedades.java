package dialogos;

import base.Enfermedad;
import base.Paciente;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * La clase DialogoGestionEnfermedades permite gestionar los pacientes que tienen una enfermedad
 *
 * @author Raquel Molina Diaz
 * @version 1
 * @since 16/02/2021
 */
public class DialogoGestionEnfermedades extends JDialog implements ActionListener {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JList<Paciente> listConEnfermedad;
    private JList<Paciente> listSinEnfermedad;
    private DefaultListModel<Paciente> dlmPacienteConEnfermedad;
    private DefaultListModel<Paciente> dlmPacienteSinEnfermedad;
    private ArrayList<Paciente> pacientesConEnfermedad;
    private ArrayList<Paciente> pacientesSinLaEnfermedad;
    private ArrayList<Enfermedad> listaTotalEnfermedades;
    private JButton botIzquierda;
    private JButton botDerecha;
    private ArrayList<Paciente> listaPacientes;
    private Enfermedad enfermedad;

    /**
     * Contructor de la clase DialogoGestionEnfermedades que recibe la enfermeda seleccionada en el JList de enfermedades
     * la lista de pacientes del modelo y la lista de enfermedades del modelo
     *
     * @param enfermedad        el parametro enfermedad hace referencia a la enfermedad que fue seleccionada el el JList de enfermedades
     * @param listaPacientes    el parametro listaPacientes hace referencia a la lista de pacientes que hay en el modelo
     * @param listaEnfermedades el parametro listaEnfermedades hace referencia a la lista de enfermedades que hay en el modelo.
     */
    public DialogoGestionEnfermedades(Enfermedad enfermedad, ArrayList<Paciente> listaPacientes, ArrayList<Enfermedad> listaEnfermedades) {
        this.enfermedad = enfermedad;
        this.listaPacientes = listaPacientes;
        this.listaTotalEnfermedades = listaEnfermedades;
        pacientesSinLaEnfermedad = new ArrayList<Paciente>();
        pacientesConEnfermedad = new ArrayList<Paciente>();
        iniciarActionListener(this);
        iniciarListas();
        activarControlPorTecladoYAceleradores();
        setTitle(enfermedad.getNombre());
        initDialogo();
        listar();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Metodo que inicializa el cuadro de dialogo
     */
    private void initDialogo() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Metodo que cierra el cuadro de dialogo
     */
    private void onOK() {
        // add your code here
        dispose();
    }

    /**
     * Metodo que cierra el cuadro de dialogo
     */
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    /**
     * Metodo que activa el control por teclado y los botones por defecto de la clase
     */
    private void activarControlPorTecladoYAceleradores() {
        botDerecha.setMnemonic(KeyEvent.VK_R);
        botIzquierda.setMnemonic(KeyEvent.VK_L);
    }

    /**
     * Metodo que lista a los pacientes en las diferentes listas en funcion
     * a si tienen la enfermedad o no la tienen.
     */
    private void listar() {
        for (Paciente paciente : listaPacientes) {
            if (paciente.getEnfermedaadesPaciente().contains(enfermedad)) {
                dlmPacienteConEnfermedad.addElement(paciente);
                pacientesConEnfermedad.add(paciente);
            } else {
                dlmPacienteSinEnfermedad.addElement(paciente);
                pacientesSinLaEnfermedad.add(paciente);
            }
        }
    }

    /**
     * Metodo que asocia un ActionListener a los elementos con los que
     * el usuario interactua.
     *
     * @param listener el parametro hace referencia al ActionListener que se asocia a los botones
     */
    private void iniciarActionListener(ActionListener listener) {
        botIzquierda.addActionListener(listener);
        botDerecha.addActionListener(listener);
    }

    /**
     * Metodo que inicializa los DefaultListModel y les setea un modelo a los JList
     */
    private void iniciarListas() {
        dlmPacienteConEnfermedad = new DefaultListModel<Paciente>();
        listConEnfermedad.setModel(dlmPacienteConEnfermedad);

        dlmPacienteSinEnfermedad = new DefaultListModel<Paciente>();
        listSinEnfermedad.setModel(dlmPacienteSinEnfermedad);

    }


    /**
     * Metodo que recoge el ActionCommand de los botones y llama a los
     * metodos correspondientes para darles funcionalidad.
     *
     * @param e el parametro e asocia un ActionEnent a diferentes botones
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "agregarEnfermedad": {
                agregarPacientes();
                refrescarListas();
                break;
            }
            case "eliminarEnfermedad": {
                eliminarPacientes();
                refrescarListas();
                break;
            }
        }
    }

    /**
     * Metodo que permite eliminar una enfermedad a uno o varios pacientes
     */
    private void eliminarPacientes() {

        int seleccionados[] = listConEnfermedad.getSelectedIndices();

        for (int i : seleccionados) {
            Paciente p = pacientesConEnfermedad.get(i);
            p.getEnfermedaadesPaciente().remove(enfermedad);
        }
    }

    /**
     * Metodo que permite agregar una enfermedad a uno a varios pacientes
     */
    private void agregarPacientes() {
        int seleccionados[] = listSinEnfermedad.getSelectedIndices();

        for (int i : seleccionados) {
            Paciente p = pacientesSinLaEnfermedad.get(i);
            p.getEnfermedaadesPaciente().add(enfermedad);
        }

    }

    /**
     * Metodo que refrescas las listas y los modelos.
     */
    private void refrescarListas() {
        dlmPacienteSinEnfermedad.removeAllElements();
        dlmPacienteConEnfermedad.removeAllElements();
        pacientesConEnfermedad.clear();
        pacientesSinLaEnfermedad.clear();

        for (Paciente p : listaPacientes) {
            if (p.getEnfermedaadesPaciente().contains(enfermedad)) {
                dlmPacienteConEnfermedad.addElement(p);
                pacientesConEnfermedad.add(p);
            } else {
                dlmPacienteSinEnfermedad.addElement(p);
                pacientesSinLaEnfermedad.add(p);
            }
        }
    }
}