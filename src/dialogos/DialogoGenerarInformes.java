package dialogos;

import base.Centro;
import base.Medico;
import mvc.giu.Controlador;
import mvc.modelo.Modelo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * La clase DialogoGenerarInformes permite visualizar dos tipos de informes, pero solamente
 * se puede visualizar uno en funcion al radio button que este releccionado en el cuadro de dialogo.
 *
 * @author Raquel Molina Diaz
 * @version 1
 * @since 16/02/2021
 */
public class DialogoGenerarInformes extends JDialog implements ActionListener {
    private JPanel contentPane;
    private JButton buttonOK;
    private JRadioButton rbInformeMedicos;
    private JRadioButton rbInformeCentros;
    private JPanel panelInforme;
    private JPanel informe1;
    private JPanel informe2;
    private JPanel panelInforme2;
    private ArrayList<Centro> listaCentros;
    private ArrayList<Medico> listaMedicos;

    /**
     * Contructor de la clase DialogoGenerarInformes
     *
     * @param listaCentros el parametro listaCentros hace referencia a la lista de centros de salud del modelo
     * @param listaMedicos el parametro listaMedicos hace referencia a la lista de medicos del modelo.
     */
    public DialogoGenerarInformes(ArrayList<Centro> listaCentros, ArrayList<Medico> listaMedicos) {
        this.listaCentros = listaCentros;
        this.listaMedicos = listaMedicos;
        mostrarInformes();

        initActionListener(this);
        initDialog();
        pack();
        setSize(new Dimension(600, 1000));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Metodo que inicializa el cuadro de dialogo
     */
    private void initDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
    }

    /**
     * Metodo que asocia un ActionListener a los elementos con los cuales el usuario interactua
     *
     * @param listener el parametro listener hace referencia a un ActionListener que se asocia los rabio button
     */
    private void initActionListener(ActionListener listener) {
        rbInformeCentros.addActionListener(listener);
        rbInformeMedicos.addActionListener(listener);
    }

    /**
     * Metodo que recoge el ActionCommand de los Radio Buttons y llama a los
     * metodos correspondientes para darles funcionalidad.
     *
     * @param e el parametro e asocia un ActionEnent a un ActionCommand
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "informeMedicos": {
                mostrarInformes();
                break;
            }
            case "informeCentros": {
                mostrarInformes();
                break;

            }
        }

    }

    /**
     * Metodo que muestra un tipo de informe u otro en funcion del radio button que este seleccionado
     */
    private void mostrarInformes() {
        if (rbInformeMedicos.isSelected()) {
            try {
                panelInforme.removeAll();
                JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/MedicosPorCentro.jasper"));
                JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(listaMedicos);
                JasperPrint jPrint = JasperFillManager.fillReport(report, null, coleccion);
                JRViewer visor = new JRViewer(jPrint);
                panelInforme.add(visor);
                //panelInforme.resize(new Dimension(500,500));
                panelInforme.setVisible(true);

                panelInforme.revalidate();
                panelInforme.repaint();


            } catch (JRException e) {
                e.printStackTrace();
            }
        } else {
            try {
                panelInforme.removeAll();
                JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/CentrosDeSalud.jasper"));
                JRBeanCollectionDataSource coleccion = new JRBeanCollectionDataSource(listaCentros);
                JasperPrint jPrint = JasperFillManager.fillReport(report, null, coleccion);
                JRViewer visor = new JRViewer(jPrint);
                panelInforme.add(visor);
                //panelInforme.resize(new Dimension(500,500));
                panelInforme.setVisible(true);

                panelInforme.revalidate();
                panelInforme.repaint();

            } catch (JRException e) {
                e.printStackTrace();
            }
        }
    }
}