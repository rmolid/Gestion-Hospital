package dialogos;

import base.Centro;
import base.Medico;
import base.Paciente;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * La clase CambiarPacienteParaElCentroSeleccionado permite cambiar pacientes
 * de otros centros hacia el centro que se selecciono en la lista de centro, permitiendose
 * en esta clase asignar un medico disponible en el centro actual seleccionado
 *
 * @author Raquel Molina Diaz
 * @version 1
 * @since 16/02/2021
 */
public class CambiarPacienteParaElCentroSeleccionado extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel lblNombre;
    private JLabel lblApellidos;
    private JLabel lblDni;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtDNI;
    private JLabel lblTitulo;
    private JComboBox cbMedicos;
    private DefaultComboBoxModel dcbmMedicos;
    private ArrayList<Medico> listaMedicos;
    private Paciente paciente;
    private Centro centro;

    /**
     * Constructor de la clase CambiarPacienteParaElCentroSeleccionado
     *
     * @param centro       el parametro hace referencia al centro con con el cual se esta trabajando actualmente
     * @param listaMedicos el parametro listaMedicos hace referencia a la lista de medicos que hay en el centro
     * @param paciente     el parametro paciente hace referencia al paciente que se quiere cambiar de centro de salud
     */
    public CambiarPacienteParaElCentroSeleccionado(Centro centro, ArrayList<Medico> listaMedicos, Paciente paciente) {
        this.paciente = paciente;
        this.listaMedicos = listaMedicos;
        this.centro = centro;
        iniciarCombo();
        setearValores();
        initIU();
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    /**
     * Metodo que inicializa el DefaultComboBoxModel y le setea el modelo al comboBox
     */
    private void iniciarCombo() {
        dcbmMedicos = new DefaultComboBoxModel();
        cbMedicos.setModel(dcbmMedicos);
    }

    /**
     * Metodo que setea en los campos de texto los valores
     * del paciente seleccionado
     */
    private void setearValores() {
        txtNombre.setText(paciente.getNombre());
        txtApellidos.setText(paciente.getApellidos());
        txtDNI.setText(paciente.getDni());
        for (Medico m : listaMedicos) {
            dcbmMedicos.addElement(m);
        }
    }

    /**
     * Metodo que inicializa el JDialog
     */
    private void initIU() {
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
     * Metodo que cambia al paciente hacia el centro actual que se esta gestionando
     * asignandole un nuevo medico de este centro.
     */
    private void onOK() {
        paciente.setMedico((Medico) cbMedicos.getSelectedItem());
        paciente.setCentro(centro);
        dispose();
    }

    /**
     * Metodo que ciera el JDialog
     */
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}