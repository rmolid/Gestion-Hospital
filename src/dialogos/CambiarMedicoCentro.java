package dialogos;

import base.Centro;
import base.Medico;
import util.Util;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * La clase CambiarMedicoCentro se encarga de cambiar al medico
 * hacia otro centro de salud.
 *
 * @author Raquel Molina Diaz
 */
public class CambiarMedicoCentro extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel principal;
    private JLabel lblTitulo;
    private JTextField txtDNI;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JComboBox cbCentro;
    private JLabel lblDni;
    private JLabel lblNombre;
    private JLabel lblApellidos;
    private JLabel lblCentro;
    private Medico medico;
    private ArrayList<Centro> listaCentros;
    private DefaultComboBoxModel<Centro> dlcbmCentro;
    private ResourceBundle bundle;

    /**
     * Constructor de la clase CambiarMedicoCentro
     *
     * @param medico
     * @param centros
     */
    public CambiarMedicoCentro(Medico medico, ArrayList<Centro> centros) {
        bundle = ResourceBundle.getBundle("idiomaResourcebundle");
        this.medico = medico;
        this.listaCentros = centros;

        initDialog();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Metodo que inicializa el JDialog
     */
    private void initDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        iniciarComboBox();
        listarDatosMedicoSeleccionado();
        listarCentrosCombo();


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
     * Metodo que inicializa el comboBox y setea el modelo
     */
    private void iniciarComboBox() {
        dlcbmCentro = new DefaultComboBoxModel<Centro>();
        cbCentro.setModel(dlcbmCentro);
    }

    /**
     * Metodo que lista los centros que hay en la lista de centros
     * en el combo box
     */
    private void listarCentrosCombo() {
        for (Centro c : listaCentros) {
            dlcbmCentro.addElement(c);
        }
    }

    /**
     * Metodo que cambia al medico de centro de salud, por
     * el centro que este seleccionado en el combobox
     */
    private void onOK() {
        medico.setCentro((Centro) cbCentro.getSelectedItem());
        dispose();
    }

    /**
     * Metodo que cierra el JDialog
     */
    private void onCancel() {

        dispose();
    }

    /**
     * Metodo que setea los datos del medico con el que
     * se va a trabajar en los campos de texto.
     */
    private void listarDatosMedicoSeleccionado() {
        txtDNI.setText(medico.getDni());
        txtNombre.setText(medico.getNombre());
        txtApellidos.setText(medico.getApellido());
        dlcbmCentro.setSelectedItem(medico.getCentro());
    }
}
