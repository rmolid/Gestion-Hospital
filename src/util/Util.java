package util;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * La clase Util se encarga de almacenar los diferentes JOption Pane y
 * el metodo que permite obtener el idioma y el pais de la aplicacion.
 *
 * @author Raquel Molina Diaz
 * @version 1
 * @since 16/02/2021
 */
public class Util {

    public static final int ACEPTAR = JOptionPane.OK_OPTION;
    public static final int CANCELAR = JOptionPane.CANCEL_OPTION;
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("idiomaResourcebundle");

    /**
     * Metodo que permite crear un JOptionPane mostrando un mensaje de error
     *
     * @param mensaje el parametro hace referencia al mensaje que se va a mostrar el JOptionPane
     */
    public static void mensajeError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, BUNDLE.getString("JOptionPane.Error"), JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Metodo que permite mostrar un JOptionPane con dos opciones, para confirmar algo o cancelar.
     *
     * @param mensaje el parametro hace referencia al mensaje que va a mostrar el JOptionPane
     * @return JOptionPane
     */
    public static int mostrarDialogoSiNo(String mensaje) {
        return JOptionPane.showConfirmDialog(null, mensaje, BUNDLE.getString("JoptionPane.Confirmacion"), JOptionPane.YES_NO_OPTION);
    }

    /**
     * Metodo que permite mostrar un JOptionPane de informacion.
     *
     * @param mensaje el parametro hace referencia al mensaje que va a mostrar el JOptionPane
     */
    public static void mostrarEjecucionCorrecta(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, BUNDLE.getString("JOptionPane.Correcto"), JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Metodo que crea un un directorio y un fichero para guardar la configuracion de preferecias de la aplicacion
     */
    public static void createPreferences() {
        Properties properties = new Properties();
        File d = new File("./data");
        File documento = new File(d, "preferencias.conf");

        if (!d.exists()) {
            d.mkdir();
        }

        if (!documento.exists()) {

            try {
                documento.createNewFile();
                properties.setProperty("color", String.valueOf(Color.white.getRGB()));
                properties.setProperty("idioma", "es");
                properties.setProperty("pais", "ES");
                try {
                    properties.store(new FileWriter(documento), "Documento de Preferencias");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    properties.load(new FileReader(documento));
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    /**
     * Metodo que permite obtener el un ojeto de tipo Locale con el pais
     * y el idioma de la aplicacion.
     *
     * @return locale objeto de tipo locale que se corresponde con el idioma y el pais
     */
    public static Locale obtenerLocale() {
        Locale locale = null;
        Properties properties = new Properties();

        try {
            properties.load(new FileReader("data/preferencias.conf"));
            String pais = properties.getProperty("pais");

            if (pais.equals("UK")) {
                locale = new Locale("en", "UK");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //Coger un idioma por defecto
        if (locale == null) {
            locale = new Locale("es", "ES");
        }
        return locale;
    }
}