import SplashScreen.SplashScreen2;
import mvc.giu.Controlador;
import mvc.giu.Ventana;
import mvc.modelo.Modelo;
import util.Util;
import javax.swing.*;
import java.awt.*;
import java.util.Locale;

/**
 * Clase Principal de la aplicacion que inicializa la aplicacion
 * con lo necesario para el correcto funcionamiento de esta.
 */
public class Principal {
    public static void main(String[] args) {


        Util.createPreferences();
        Locale locale = Util.obtenerLocale();
        Locale.setDefault(locale);

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {

        }

        Ventana ventana = new Ventana();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(modelo, ventana);


        //Creamos el hilo
        Thread hilo = new Thread(new SplashScreen2());
        //Iniciamos el hilo de la splash screen
        hilo.start();

        //hacemos que el hilo principal espere a que termine el hilo splashScreen (join())
        try {
            hilo.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ventana.setVisible(true);

    }


}