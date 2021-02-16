package dialogos;

import base.Enfermedad;
import base.Paciente;
import mvc.modelo.Modelo;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * La clase DialogoEnfermedad permite agregar enfermedades al paciente que este seleccionado
 * el el JList de pacientes de la clase Ventana.
 *
 * @author Raquel Molina Diaz
 * @version 1
 * @since 16/02/2021
 */
public class DialogoEnfermedad extends JDialog implements ActionListener {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JButton botIzquierda;
    private JButton botDerecha;
    private JList listaTotalEnfermedades;
    private JList listaEnfermedadesPaciente;
    private DefaultListModel<Enfermedad> dlmEnfermedadesTotal;
    private DefaultListModel<Enfermedad> dlmPacienteEnfermedades;
    private ArrayList<Enfermedad> listaEnfermedades;
    private ArrayList<Enfermedad> enfermedadsDelPaciente;
    private Paciente paciente;
    private Modelo modelo;
    private ResourceBundle bundle;

    /**
     * Constructor de la clase DialogoEnfermedad
     *
     * @param list                   el parametro list representa la lista de enfermedades totales.
     * @param enfermedadsDelPaciente el parametro enfermedadsDelPaciente representa las enfermedaades que tiene el paciente
     * @param paciente               el parametro paciente representa el paciente que se selecciono en el JList de pacientes.
     */
    public DialogoEnfermedad(ArrayList<Enfermedad> list, ArrayList<Enfermedad> enfermedadsDelPaciente, Paciente paciente) {
        this.paciente = paciente;
        this.listaEnfermedades = new ArrayList<>();
        this.enfermedadsDelPaciente = enfermedadsDelPaciente;
        bundle = ResourceBundle.getBundle("idiomaResourcebundle");
        for (Enfermedad enfermedad : list) {
            if (!this.enfermedadsDelPaciente.contains(enfermedad)) {
                this.listaEnfermedades.add(enfermedad);
            }
        }

        if (paciente != null) {
            setTitle(paciente.getNombre() + " " + paciente.getApellidos());
        } else {
            setTitle(bundle.getString("Sin.Paciente"));
        }

        iniciarListas();
        initDialog();
        pack();
        setLocationRelativeTo(null);
        iniciarActionListener(this);
        ;
        refrescarTotalEnfermedades();
        refrescarPacienteEnfermedades();
        setVisible(true);

    }

    /**
     * Metodo que inicializa las listas y les setea un modelo
     */
    private void iniciarListas() {
        dlmEnfermedadesTotal = new DefaultListModel<Enfermedad>();
        listaTotalEnfermedades.setModel(dlmEnfermedadesTotal);

        dlmPacienteEnfermedades = new DefaultListModel<Enfermedad>();
        listaEnfermedadesPaciente.setModel(dlmPacienteEnfermedades);
    }

    /**
     * Metodo que refresca el DefaultListModel de las enfermedades
     * que padece un paciente
     */
    private void refrescarPacienteEnfermedades() {
        for (Enfermedad e : enfermedadsDelPaciente) {
            dlmPacienteEnfermedades.addElement(e);
        }
    }

    /**
     * Metodo que refresca el DefaultListModel de las enfermedad
     * que hay en total en el la aplicacion.
     */
    private void refrescarTotalEnfermedades() {
        for (Enfermedad e : listaEnfermedades) {
            dlmEnfermedadesTotal.addElement(e);
        }
    }

    /**
     * Metodo que inicializa el JDialog
     */
    private void initDialog() {
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
     * Metodo que asocia un ActionListener a los botones con los que el usuario interactua
     *
     * @param listener el parametro hace referenencia al ActionListener  que se asocia a los botones
     */
    private void iniciarActionListener(ActionListener listener) {
        botIzquierda.addActionListener(listener);
        botDerecha.addActionListener(listener);
    }

    /**
     * Metodo que recoge el ActionCommand de los diferentes botones y otorga la funcionalidad correspondiente
     *
     * @param e el parametro e asocia un ActionEvent los botones con los que se interactua
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "agregarEnfermedad": {
                agregarEnfermedad();
                break;
            }

            case "eliminarEnfermedad": {
                eliminarEnfermedad();
                break;
            }
        }
    }

    /**
     * Metodo que elimina una o varias enfermedades de un paciente
     */
    private void eliminarEnfermedad() {
        int seleccionadas[] = listaEnfermedadesPaciente.getSelectedIndices();
        for (int i = 0; i < seleccionadas.length; i++) {
            dlmEnfermedadesTotal.addElement(enfermedadsDelPaciente.get(seleccionadas[i]));
            listaEnfermedades.add(enfermedadsDelPaciente.get(seleccionadas[i]));
            dlmPacienteEnfermedades.removeElement(enfermedadsDelPaciente.get(seleccionadas[i]));

        }
        for (int i = 0; i < seleccionadas.length; i++) {
            enfermedadsDelPaciente.remove(seleccionadas[i] - i);
        }
    }

    /**
     * Metodo que agrega una o varias enfermedades a un paciente
     */
    private void agregarEnfermedad() {
        int seleccionadas[] = listaTotalEnfermedades.getSelectedIndices();

        for (int i = 0; i < seleccionadas.length; i++) {
            dlmPacienteEnfermedades.addElement(listaEnfermedades.get(seleccionadas[i]));
            enfermedadsDelPaciente.add(listaEnfermedades.get(seleccionadas[i]));
            dlmEnfermedadesTotal.removeElement(listaEnfermedades.get(seleccionadas[i]));

        }
        for (int i = 0; i < seleccionadas.length; i++) {
            listaEnfermedades.remove(seleccionadas[i] - i);
        }
    }

    /**
     * Metodo que permite obtener la lista de enfermedades del paciente
     *
     * @return enfermedadsDelPaciente
     */
    public ArrayList<Enfermedad> getListaEnfermedadesDialogo() {
        return enfermedadsDelPaciente;
    }

    /**
     * Metodo que permite cambiar la lista de enfermedades del paciente
     *
     * @param listaEnfermedades el parametro hace referencia a la nueva lista de enfermedades del paciente.
     */
    public void setListaEnfermedades(ArrayList<Enfermedad> listaEnfermedades) {
        this.listaEnfermedades = listaEnfermedades;
    }
}