package dialogos;

import base.Centro;
import base.Medico;
import base.Paciente;
import util.Util;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * La clase CambiarPacienteHaciaOtroCentro  se encarga de gestionar el cambio
 * de centro y de medico de los pacientes del centro actual, hacia otro centro
 * lo que requiere que se reasigne el nuevo centro y nuevo medico.
 *
 * @author Raquel Molina Diaz
 * @version 1
 * @since 16/02/2021
 */
public class CambiarPacienteHaciaOtroCentro extends JDialog implements ItemListener {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel lblNombre;
    private JLabel lblApellidos;
    private JLabel lblDni;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtDNI;
    private JLabel lblMedicos;
    private JComboBox<Medico> cbMedicos;
    private JComboBox<Centro> cbCentro;
    private DefaultComboBoxModel<Medico> dcbmMedicos;
    private DefaultComboBoxModel<Centro> dcbmCentros;
    private JLabel lblCentro;
    private ArrayList<Medico> listaMedicos;
    private ArrayList<Centro> listaCentros;
    private Paciente paciente;
    private Centro centro;

    /**
     * Constructor de la clase CambiarPacienteHaciaOtroCentro
     *
     * @param paciente     el parametro paciente hace referencia al paciente con el que se va a trabajar
     * @param listaMedicos el parametro listaMedicos hace referencia a todos los medicos que hay en el modelo
     * @param listaCentros el parametro listaCentros hace referencia a todos los centros que hay en el modelo
     * @param centro       el parametro centro hace referencia al centro actual del paciente con el que se trabaja
     */
    public CambiarPacienteHaciaOtroCentro(Paciente paciente, ArrayList<Medico> listaMedicos, ArrayList<Centro> listaCentros, Centro centro) {
        this.listaCentros = listaCentros;
        this.listaMedicos = listaMedicos;
        this.paciente = paciente;
        this.centro = centro;

        iniciarCombos();
        initItemListener(this);
        setearValores();
        initIu();
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * Metodo que asocia un ItemListener a los elementos con los cuales el usuario va a interactuar
     *
     * @param listener el parametro se corresponde con un ItemListener asociado al comboBox
     */
    private void initItemListener(ItemListener listener) {
        cbCentro.addItemListener(listener);
    }

    /**
     * Metodo que inicializa el JDialog
     */
    private void initIu() {
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
     * Metodo que inicializa los DefaultComboBoxModel y les setea un modelo a a los comboBox
     */
    private void iniciarCombos() {
        dcbmCentros = new DefaultComboBoxModel<Centro>();
        cbCentro.setModel(dcbmCentros);

        dcbmMedicos = new DefaultComboBoxModel<Medico>();
        cbMedicos.setModel(dcbmMedicos);
    }

    /**
     * Metodo que setea los valores en los campos de texto
     * sobre el paciente que queremos modificar.
     */
    private void setearValores() {
        txtNombre.setText(paciente.getNombre());
        txtApellido.setText(paciente.getApellidos());
        txtDNI.setText(paciente.getDni());
        for (Centro c : listaCentros) {
            dcbmCentros.addElement(c);
        }
    }

    /**
     * Metodo que se encarga se cambiar al paciente de medico y de centro y en el caso
     * de que alguno de estos valores esté a null no permite modificar el paciente
     */
    private void onOK() {
        if (cbMedicos.getSelectedItem() != null && cbCentro.getSelectedItem() != null) {
            paciente.setMedico((Medico) cbMedicos.getSelectedItem());
            paciente.setCentro((Centro) cbCentro.getSelectedItem());
            dispose();
        } else {
            Util.mensajeError("Todo paciente debe de tener un centro y un medico asignado");
        }
    }

    /**
     * Metodo que cierra el cuadro de dialogo
     */
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }


    /**
     * Metodo que asocia un ItemEvent para poder actualizar el comboBox de Medicos
     * en funcion al centro seleccionado
     *
     * @param e el parametro de corresponde con un ItemEven que permite actualizar el combo de medicos
     *          en funcion del centro seleccionado en el comboBox de centros de salud
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        actualizarComboBoxMedicosPorCentro((Centro) e.getItem());
    }

    /**
     * Metodo que se encarga de actualizar el comboBox de medicos
     * en funcion al centro que este seleccionado.
     *
     * @param centro el parametro centro hace referencia al centro seleccionado en el comboBox
     */
    private void actualizarComboBoxMedicosPorCentro(Centro centro) {
        ArrayList<Medico> listadoMedicos = new ArrayList<Medico>();
        for (Medico m : listaMedicos) {
            if (centro.equals(m.getCentro())) {
                listadoMedicos.add(m);
            }
        }
        cbMedicos.removeAllItems();
        for (Medico m : listadoMedicos)
            cbMedicos.addItem(m);
    }
}