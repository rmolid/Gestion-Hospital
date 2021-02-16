package dialogos;

import base.Centro;
import base.Medico;
import base.Paciente;
import mvc.modelo.Modelo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * La clase DialogoDiferentesGraficas genera diferentes gráficas en funcion del
 * Radio Button que este seleccionado
 *
 * @author Raquel Molina Diaz
 * @version 1
 * @since 16/02/2021
 */
public class DialogoDiferentesGraficas extends JDialog implements ActionListener {
    private JPanel contentPane;
    private JButton buttonOK;
    private JRadioButton rbGrafica1;
    private JRadioButton rbGrafica2;
    private JPanel pnlMostrarGraficas;
    private JPanel panelMostrar;
    private Modelo modelo;
    private ResourceBundle bulndle;

    /**
     * Constructor de la clase DialogoDiferentesGraficas
     *
     * @param modelo el parametro modelo hace referencia al modelo de la aplicacion
     */
    public DialogoDiferentesGraficas(Modelo modelo) {
        bulndle = ResourceBundle.getBundle("idiomaResourcebundle");
        this.modelo = modelo;
        iniciarActionListener(this);

        initDialog();
        generarGrafica1();
        panelMostrar.revalidate();
        pack();
        setResizable(false);
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

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
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
        dispose();
    }

    /**
     * Metodo que crea un gráfico de sectores de medicos por centro de salud
     * cuando el rbGrafica1 este seleccionado y un grafico de barrar con el total
     * de medicos y pacientes cuando rbGrafica1 no este seleccionado
     */
    private void generarGrafica1() {
        if (rbGrafica1.isSelected()) {
            pnlMostrarGraficas.removeAll();
            DefaultPieDataset dataset = new DefaultPieDataset();
            ArrayList<Medico> listaMedicos = modelo.getListaMedicos();
            int contador = 0;
            for (Medico medico : listaMedicos) {
                Centro centro = medico.getCentro();
                for (Medico medico1 : listaMedicos) {
                    if (centro == medico1.getCentro()) {
                        contador = contador + 1;
                    }
                }
                dataset.setValue(centro.getNombreCentro(), contador);
                contador = 0;
            }
            JFreeChart grafica = ChartFactory.createPieChart3D(bulndle.getString("medicos.por.centro.grafica.titulo"), dataset, true, false, false);
            ChartPanel chartPanel = new ChartPanel(grafica);
            chartPanel.setSize(new Dimension(500, 500));
            pnlMostrarGraficas.add(chartPanel);
            pnlMostrarGraficas.revalidate();

        } else {
            pnlMostrarGraficas.removeAll();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            int contadorM = 0;
            int contadorP = 0;
            for (Medico m : modelo.getListaMedicos()) {
                contadorM = contadorM + 1;
            }

            for (Paciente p : modelo.getListaPacientes()) {
                contadorP = contadorP + 1;
            }

            dataset.setValue(contadorM, bulndle.getString("medicos.string.grafica"), bulndle.getString("medicos.string.grafica.total.key"));
            dataset.setValue(contadorP, bulndle.getString("pacientes.string.grafica"), bulndle.getString("pacientes.string.grafica.total.key"));

            JFreeChart grafica = ChartFactory.createBarChart(bulndle.getString("proporcion.pacientes.titulo.grafica"), "", bulndle.getString("eje.y.total"), dataset, PlotOrientation.VERTICAL, true, true, false);
            ChartPanel chartPanel = new ChartPanel(grafica);
            chartPanel.setSize(new Dimension(500, 500));
            pnlMostrarGraficas.add(chartPanel);
            pnlMostrarGraficas.revalidate();
        }

    }

    /**
     * Metodo que recoge el ActionCommand de los Radio Buttons y llama a los
     * metodos correspondientes para darles funcionalidad.
     *
     * @param event el parametro event asocia un ActionEnent a los botones
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        String comando = event.getActionCommand();
        switch (comando) {
            case "grafica1": {
                generarGrafica1();
                break;
            }
            case "grafica2": {
                generarGrafica1();
                break;
            }
        }
    }

    /**
     * Metodo que asocia un ActionListener a los elementos con los que
     * el usuario interactua en la ventana.
     *
     * @param listener el parametro listener asocia un ActionListener a los radio buttons con los que
     *                 interactua el usuario para generar las graficas
     */
    private void iniciarActionListener(ActionListener listener) {
        rbGrafica1.addActionListener(listener);
        rbGrafica2.addActionListener(listener);
    }
}