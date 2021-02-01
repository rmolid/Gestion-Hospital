package dialogos;

import util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * La clase Preferencias es la encargada de llevar a cabo un control
 * del idioma y el color de la aplicacion y guardar y cargar dicha configuracion
 *
 * @author Raquel Molina Diaz
 */
public class Preferencias extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel lblTituloPreferencias;
    private JRadioButton espanholRadioButton;
    private JRadioButton inglésRadioButton;
    private JLabel lblEspanha;
    private JLabel lblTemaAplicacion;
    private JButton botSelecionarTema;
    private JLabel lblGuardarColor;
    private JPanel fondoPanel;
    private JPanel botonesPanel;
    private JPanel otroPanel;
    private ResourceBundle resourceBundle;
    private Color colorDefecto;

    /**
     * Constructor de la clase Preferencias
     */
    public Preferencias() {
        resourceBundle = ResourceBundle.getBundle("idiomaResourcebundle");
        initDialog();
        cargarConfiguracion();
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

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

        botSelecionarTema.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                colorDefecto = JColorChooser.showDialog(null, "color tema", null);

                if(colorDefecto == null) {
                    colorDefecto = Color.white;
                }

            }
        });
    }

    /**
     * Metodo que guarda la configuracion y da la opcion de salir de la aplicacion para
     * aplicar los cambios
     */
    private void onOK() {
        guardarConfiguracion();
        int opcion = Util.mostrarDialogoSiNo(resourceBundle.getString("preferencias.dialogo"));
        if(opcion == JOptionPane.YES_OPTION){
            System.exit(2);
        }
        dispose();
    }

    /**
     * Metodo que cierra la venta de Preferencias
     */
    private void onCancel() {
        dispose();
    }

    /**
     * Metodo que guarda en el archivo preferencias.conf, el idioma, pais y color
     * de la aplicacion seleccionados por el usuario.
     */
    private void guardarConfiguracion(){
        Properties propiedades = new Properties();
        String idioma;
        String pais;

        if(espanholRadioButton.isSelected()){
            idioma = "es";
            pais = "ES";
        }else{
            idioma = "en";
            pais = "UK";
        }
        propiedades.setProperty("idioma", idioma);
        propiedades.setProperty("pais", pais);
        propiedades.setProperty("color",String.valueOf(colorDefecto.getRGB()));
        try {
            propiedades.store(new FileWriter("data/preferencias.conf"),"Documento de Preferencias");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Metodo que carga los ultimos valores del archivo preferencias.conf
     * guardados y los aplica al iniciar la aplicacion.
     */
    private void cargarConfiguracion() {
        Properties properties = new Properties();

        try {
            properties.load(new FileReader("data/preferencias.conf"));
            String pais = properties.getProperty("pais");
            if(pais.equals("ES")){
                espanholRadioButton.setSelected(true);
            }else {
                inglésRadioButton.setSelected(true);
            }
            colorDefecto = new Color(Integer.parseInt(properties.getProperty("color")));
            lblGuardarColor.setBackground(colorDefecto);
            fondoPanel.setBackground(colorDefecto);
            contentPane.setBackground(colorDefecto);
            botonesPanel.setBackground(colorDefecto);
            otroPanel.setBackground(colorDefecto);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}