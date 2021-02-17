package mvc.giu;

import base.Centro;
import base.Enfermedad;
import base.Medico;
import base.Paciente;
import com.github.lgooddatepicker.components.DatePicker;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ResourceBundle;

/**
 * La clase Ventana es la clase que contiene los elementos visuales
 * de la aplicacion principal
 *
 * @author Raquel Molina Diaz
 * @version 1
 * @since 16/02/2021
 */
public class Ventana {
    JFrame frame;
    JPanel panel1;
    JButton botCargar;
    JButton botGuardar;
    JButton botAjustes;
    JButton botUsuarios;
    JPanel panelMedico;
    JPanel panelPaciente;
    JPanel panelCentro;
    JPanel datosMedico;
    JTextField txtDniMedico;
    JLabel lblDniMedico;
    JLabel lblNombreMedico;
    JTextField txtNombreMedico;
    JLabel lblApellidoMedico;
    JRadioButton hombreRadioButton;
    JRadioButton mujerRadioButton;
    JComboBox cbCentroMedico;
    DefaultComboBoxModel<Centro> dcbmCentro;
    JButton botNuevoMedico;
    JLabel lblGeneroMedico;
    JTextField txtApellidosMedico;
    JSpinner spinnerEdadMedico;
    JLabel lblEdadMedico;
    JList<Medico> listMedicos;
    DefaultListModel<Medico> dlmMedico;
    DatePicker pacienteDatePicker;
    JMenu menuArchivo;
    JMenu menuEdicion;
    JMenuItem itemCargar;
    JMenuItem itemGuardar;
    JMenuItem itemSalir;
    JMenuItem itemConfiguracion;
    JMenuItem itemUsuarios;
    JPanel datosPaciente;
    JLabel lblDniPaciente;
    JTextField txtDniPaciente;
    JLabel lblNombrePaciente;
    JTextField txtNombrePaciente;
    JLabel lblApellidosPaciente;
    JTextField txtApellidosPaciente;
    JLabel lblFechaPaciente;
    JLabel lblPeso;
    JTextField txtPesoPaciente;
    JLabel lblDoctorPaciente;
    JComboBox cbMedicoDePaciente;
    JLabel lblCentroPaciente;
    JComboBox cbCentroPaciente;
    JList<Paciente> listPacientes;
    DefaultListModel<Paciente> dlmPaciente;
    DefaultComboBoxModel<Medico> dcbmMedico;
    DefaultListModel<Centro> dlmCentro;
    JButton botNuevoPaciente;
    JPanel datosImagenCentro;
    JPanel panelImagen;
    JLabel lblImagen;
    JButton botImagen;
    JPanel datosCentro;
    JTextField txtNombreCentro;
    JCheckBox cbPrivado;
    JTextField txtLocalidad;
    JSpinner spinnerCapacidad;
    JList<Centro> listCentros;
    JButton nuevoCentro;
    JPanel panelBoton;
    JTabbedPane JTabbedPane;
    JButton botEliminarPaciente;
    JButton botEliminarMedico;
    JButton botModificarMedico;
    JButton botModificarPaciente;
    JButton botEliminarCentro;
    JButton botModificarCentro;
    JPanel panelDeBotones;
    JPanel panelBotonesPacientes;
    JPanel listaPacientesPanel;
    JPanel panelListaCentros;
    JPanel panelBotonesCentros;
    JPanel panelMenu;
    JButton botEliminarFoto;
    JButton botGrafico1;
    JButton botTiposGraficas;
    JButton botEnfermedadPac;
    JList<Enfermedad> listaEnfermedades;
    DefaultListModel<Enfermedad> dlmEnfermedad;
    JButton botNuevaEnferm;
    JButton botEliminarEnferm;
    JButton botModifEnferme;
    ResourceBundle buldle;
    JTextField txtNombEnf;
    JTextField txtMedEnf;
    JTextField txtSintEnf;
    JTextField txtOrigEnf;
    JPanel panelDatosEnfermedades;
    JPanel panelBotonesEnfermedad;
    JPanel panelEnfermedadPequeno;
    JPanel panelBotonDeEnfermedad;
    JButton botInformes;
    JButton botInformeCentros;
    JButton botTodosLosInformes;
    JButton botJavadoc;
    JButton botManualUso;



    /**
     * Constructor de la clase ventana
     */
    public Ventana() {
        buldle = ResourceBundle.getBundle("idiomaResourcebundle");
        frame = new JFrame(buldle.getString("lblTituloApp"));
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("medical.png")));
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(false);
        frame.setLocationRelativeTo(null);
        crearBarraMenu();
        configurarDatePicker();
        activarControlPorTecladoAceleradoresBotonesPorDefecto();

        iniciarListasYCombos();
    }

    /**
     * Metodo que cambia la visibilidad de la ventana principal
     * @param visible el parametro visible es de tipo boolean, cuando toma el valor true la ventana es visible.
     */
    public void setVisible(boolean visible) {

        frame.setVisible(visible);
    }

    /**
     * Metodo que inicializa los diferentes DefaultListModel y DefaultComboBoxModel
     * y les setea a las listas y combos un modelo.
     */
    private void iniciarListasYCombos() {
        dlmMedico = new DefaultListModel<Medico>();
        listMedicos.setModel(dlmMedico);

        dlmPaciente = new DefaultListModel<Paciente>();
        listPacientes.setModel(dlmPaciente);

        dlmCentro = new DefaultListModel<Centro>();
        listCentros.setModel(dlmCentro);

        dcbmCentro = new DefaultComboBoxModel<Centro>();
        cbCentroMedico.setModel(dcbmCentro);
        cbCentroPaciente.setModel(dcbmCentro);

        dcbmMedico = new DefaultComboBoxModel<Medico>();
        cbMedicoDePaciente.setModel(dcbmMedico);

        dlmEnfermedad = new DefaultListModel<Enfermedad>();
        listaEnfermedades.setModel(dlmEnfermedad);

    }

    /**
     * Metodo que configura el DatePicker para ponerle un icono
     */
    private void configurarDatePicker() {
        JButton fechas = pacienteDatePicker.getComponentToggleCalendarButton();
        fechas.setText("");
        fechas.setIcon(new ImageIcon(getClass().getResource("/Calendar.png")));
    }

    /**
     * Metodo que crea una barra de menu
     */
    private void crearBarraMenu() {
        JMenuBar barra = new JMenuBar();
        frame.setJMenuBar(barra);

        //Menu File
        menuArchivo = new JMenu(buldle.getString("menu.archivo"));
        barra.add(menuArchivo);

        //ITEMS DEL MENU File
        itemCargar = new JMenuItem(buldle.getString("menu.sub.abrir"));
        itemCargar.setActionCommand("Abrir");
        itemCargar.setIcon(new ImageIcon(getClass().getResource("/file.png")));
        itemCargar.setToolTipText(buldle.getString("ToolTipText.Menu.Abrir"));
        itemCargar.setActionCommand("cargarMenu");
        menuArchivo.add(itemCargar);

        itemGuardar = new JMenuItem(buldle.getString("menu.sub.guardar"));
        itemGuardar.setActionCommand("Guardar");
        itemGuardar.setIcon(new ImageIcon(getClass().getResource("/save.png")));
        itemGuardar.setToolTipText(buldle.getString("ToolTipText.Menu.Guardar"));
        itemGuardar.setActionCommand("guardarMenu");
        menuArchivo.add(itemGuardar);

        itemSalir = new JMenuItem(buldle.getString("menu.sub.salir"));
        itemSalir.setActionCommand("Salir");
        itemSalir.setIcon(new ImageIcon(getClass().getResource("/log-out.png")));
        itemSalir.setToolTipText(buldle.getString("ToolTipText.SalirApp"));
        itemSalir.setActionCommand("SalirAplicacion");
        menuArchivo.add(itemSalir);

        //Menu Edicion
        menuEdicion = new JMenu(buldle.getString("menu.edicion"));
        barra.add(menuEdicion);

        itemConfiguracion = new JMenuItem(buldle.getString("menu.sub.configuracion"));
        itemConfiguracion.setActionCommand("Configuracion");
        itemConfiguracion.setIcon(new ImageIcon(getClass().getResource("/setting (1).png")));
        itemConfiguracion.setToolTipText(buldle.getString("ToolTipText.Menu.Ajustes"));
        itemConfiguracion.setActionCommand("ConfigApp");
        menuEdicion.add(itemConfiguracion);


    }

    /**
     * Metodo que activa el control por teclado de los diferentes botones de la clase ventana
     * activa los aceleradores de menu y asigna y boton por defecto en cada ventana del JTabbedPane
     */
    private void activarControlPorTecladoAceleradoresBotonesPorDefecto() {
        //Botones de la aplicacion
        botCargar.setMnemonic(KeyEvent.VK_O);
        botGuardar.setMnemonic(KeyEvent.VK_S);
        botAjustes.setMnemonic(KeyEvent.VK_A);
        botNuevoMedico.setMnemonic(KeyEvent.VK_N);
        botNuevoPaciente.setMnemonic(KeyEvent.VK_N);
        nuevoCentro.setMnemonic(KeyEvent.VK_N);
        botImagen.setMnemonic(KeyEvent.VK_P);
        botEliminarMedico.setMnemonic(KeyEvent.VK_X);
        botEliminarCentro.setMnemonic(KeyEvent.VK_X);
        botEliminarPaciente.setMnemonic(KeyEvent.VK_X);
        botModificarMedico.setMnemonic(KeyEvent.VK_M);
        botModificarPaciente.setMnemonic(KeyEvent.VK_M);
        botModificarCentro.setMnemonic(KeyEvent.VK_M);
        botGrafico1.setMnemonic(KeyEvent.VK_1);
        botEliminarFoto.setMnemonic(KeyEvent.VK_D);
        botTiposGraficas.setMnemonic(KeyEvent.VK_G);

        botEnfermedadPac.setMnemonic(KeyEvent.VK_E);
        botNuevaEnferm.setMnemonic(KeyEvent.VK_N);
        botEliminarEnferm.setMnemonic(KeyEvent.VK_X);
        botModifEnferme.setMnemonic(KeyEvent.VK_M);

        //INFORMES
        botInformeCentros.setMnemonic(KeyEvent.VK_C);
        botInformes.setMnemonic(KeyEvent.VK_I);
        botTodosLosInformes.setMnemonic(KeyEvent.VK_T);


        //Documentacion
        botJavadoc.setMnemonic(KeyEvent.VK_J);
        botManualUso.setMnemonic(KeyEvent.VK_U);
        //Aceleradores del Menu
        itemCargar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        itemGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));

        itemConfiguracion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        itemSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));


        //Botones por defecto
        if (JTabbedPane.getSelectedIndex() == 0) {
            JTabbedPane.getRootPane().setDefaultButton(botNuevoMedico);
        } else if (JTabbedPane.getSelectedIndex() == 1) {

            JTabbedPane.getRootPane().setDefaultButton(botNuevoPaciente);
        } else {
            JTabbedPane.getRootPane().setDefaultButton(nuevoCentro);
        }

    }
}
