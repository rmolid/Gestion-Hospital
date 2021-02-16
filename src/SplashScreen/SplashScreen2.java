package SplashScreen;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

/**
 * Clase SplashScreen que permite crear una pantalla de carga para la aplicacion
 *
 * @author Fernando Valdeón
 * @version 1
 * @since 16/02/2021
 */
public class SplashScreen2 extends JDialog implements Runnable{

	private static final long serialVersionUID = 1L;
	private JProgressBar barraProgreso;

	/**
	 * Contuctor de la clase que crea una SplashScreen para la aplicacion
	 */
	public SplashScreen2() {
		setBounds(100, 100, 400, 300);
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		//Creo una etiqueta con la imagen en el centro
		JLabel lblImagen = new JLabel();
		//Indico la imagen que quiero mostrar en la label

        lblImagen.setIcon(new ImageIcon(SplashScreen2.class.getResource("/imagencarga.gif")));

		contentPane.add(lblImagen, BorderLayout.CENTER);
		
		//Creo un panel al sur con una barra de carga y una label para el autor
		JPanel panelInferior = new JPanel();
		panelInferior.setLayout(new GridLayout(2, 1, 0, 0));
		barraProgreso = new JProgressBar();
		//Muestra el % de carga
		barraProgreso.setStringPainted(true);
		panelInferior.add(barraProgreso);
		
		JLabel lblFersoft = new JLabel("Raquel Molina Diaz 2021");
		lblFersoft.setForeground(Color.BLUE);
		lblFersoft.setHorizontalAlignment(SwingConstants.CENTER);
		panelInferior.add(lblFersoft);
		
		//Anado el panel inferior al principal
		contentPane.add(panelInferior, BorderLayout.SOUTH);
				
		setResizable(false); //Impedir redimensionar la ventana
		setUndecorated(true); //Eliminar la barra de título y sus botones
		setLocationRelativeTo(null); //Mostrar en el centro
		setVisible(true);
	}

	/**
	 * Metodo run que contiene las acciones propias del hilo
	 */
	@Override
	public void run() {
	   try {
              //Cada 20ms avanzamos la barra de progreso 0-100
              for(int i = 0; i < 100; i++) {
                 Thread.sleep(45);
                 barraProgreso.setValue(i);
              }
           } catch (InterruptedException ie) {
            ie.printStackTrace();
           }
	   //Al terminar la espera cierro el JDialog
	   dispose();
	}
}