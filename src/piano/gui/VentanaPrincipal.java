package piano.gui;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import piano.dominio.TecladoMusical;

public class VentanaPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JMenu archivoMenu;
	private JMenu operacionesMenu;
	private JMenu ayudaMenu;
	private JMenuItem abrirMenu;
	private JMenuItem guardarMenu;
	private JMenuItem salirMenu;
	private JMenuItem catalogoMenu;
	private JMenuItem consultarMenu;
	private JMenuItem acercaDeMenu;
	private JMenuBar barraMenu;
	
	private ArrayList<TecladoMusical> teclados = new ArrayList<TecladoMusical>();

	public VentanaPrincipal() {
		super("Teclados y pianos Cruz");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/piano.png")));

		archivoMenu = new JMenu("Archivo");
		archivoMenu.setIcon(new ImageIcon(getClass().getResource("/imagenes/archivo.png")));
		archivoMenu.setMnemonic(KeyEvent.VK_R);
		archivoMenu.setToolTipText("Haz click para ver las opciones del menu archivo");
		abrirMenu = new JMenuItem("Abrir");
		abrirMenu.setMnemonic(KeyEvent.VK_A);
		abrirMenu.setToolTipText("Abre un archivo");
		abrirMenu.setIcon(new ImageIcon(getClass().getResource("/imagenes/abrir.png")));
		abrirMenu.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK + InputEvent.CTRL_DOWN_MASK));
		abrirMenu.addActionListener(this);
		guardarMenu = new JMenuItem("Guardar");
		guardarMenu.setToolTipText("Guarda un archivo");
		guardarMenu.setMnemonic(KeyEvent.VK_G);
		guardarMenu.setIcon(new ImageIcon(getClass().getResource("/imagenes/Guardar.png")));
		guardarMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
		guardarMenu.addActionListener(this);
		salirMenu = new JMenuItem("Salir");
		salirMenu.setIcon(new ImageIcon(getClass().getResource("/imagenes/salir.png")));
		salirMenu.setMnemonic(KeyEvent.VK_S);
		salirMenu.setToolTipText("Cierra la aplicación");
		salirMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		salirMenu.addActionListener(this);
		archivoMenu.add(abrirMenu);
		archivoMenu.add(guardarMenu);
		archivoMenu.addSeparator();
		archivoMenu.add(salirMenu);

		operacionesMenu = new JMenu("Operaciones");
		operacionesMenu.setMnemonic(KeyEvent.VK_O);
		operacionesMenu.setToolTipText("Haz click para ver las opciones del menu operaciones");
		operacionesMenu.setIcon(new ImageIcon(getClass().getResource("/imagenes/Operaciones.png")));

		catalogoMenu = new JMenuItem("Catálogo");
		catalogoMenu.setToolTipText("Muestra el Catálogo del piano");
		catalogoMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.SHIFT_DOWN_MASK));
		catalogoMenu.setMnemonic(KeyEvent.VK_T);
		catalogoMenu.addActionListener(this);
		catalogoMenu.setIcon(new ImageIcon(getClass().getResource("/imagenes/catalogo.png")));

		consultarMenu = new JMenuItem("Consulta");
		consultarMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.SHIFT_DOWN_MASK));
		consultarMenu.setToolTipText("Meustra la sunsulta del piano");
		consultarMenu.setMnemonic(KeyEvent.VK_N);
		consultarMenu.addActionListener(this);
		consultarMenu.setIcon(new ImageIcon(getClass().getResource("/imagenes/consultar.png")));

		operacionesMenu.add(catalogoMenu);
		operacionesMenu.addSeparator();
		operacionesMenu.add(consultarMenu);

		ayudaMenu = new JMenu("Ayuda");
		ayudaMenu.setMnemonic(KeyEvent.VK_Y);
		ayudaMenu.setIcon(new ImageIcon(getClass().getResource("/imagenes/ayuda.png")));

		acercaDeMenu = new JMenuItem("Acerca de...");
		acercaDeMenu.setToolTipText("Haz click para ver las opciones del menu acerca de");
		acercaDeMenu.setMnemonic(KeyEvent.VK_E);
		acercaDeMenu.setIcon(new ImageIcon(getClass().getResource("/imagenes/acercaDe.png")));
		acercaDeMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		acercaDeMenu.setToolTipText("Muestra los créditos del sistema");
		acercaDeMenu.addActionListener(this);
		ayudaMenu.add(acercaDeMenu);

		barraMenu = new JMenuBar();
		barraMenu.add(archivoMenu);
		barraMenu.add(operacionesMenu);
		barraMenu.add(ayudaMenu);
		
		
		JLabel fondo = new JLabel();
		ImageIcon imagenFondo = new ImageIcon(getClass().getResource(
				"/imagenes/fondo.png"));
		
		
		Image imagenEscalada = imagenFondo.getImage().getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height, Image.SCALE_SMOOTH);
		fondo.setIcon(new ImageIcon(imagenEscalada));
		this.getContentPane().add(fondo);
		
		
		this.setJMenuBar(barraMenu);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		metodo1();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(abrirMenu)) {
					try {
						metodoAbrir();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
		} else if (e.getSource().equals(guardarMenu)) {
			metodoGuardar();
		} else if (e.getSource().equals(salirMenu)) {
			metodoSalir();
		} else if (e.getSource().equals(catalogoMenu)) {
			metodoCatalogo();
		} else if (e.getSource().equals(consultarMenu)) {
			metodoConsultar();
		} else if (e.getSource().equals(acercaDeMenu)) {
			metodoAcerdaDe();
		}

	}
	private void metodo1() {
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				metodoSalir();

			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

		});
	}

	private void metodoSalir() {
		// TODO Auto-generated method stub
		System.exit(0);

	}

	private void metodoAcerdaDe() {
		JOptionPane.showMessageDialog(this,
				"Teclados y pianos Cruz 1.0" + "\n\n" + "Desarrollado por:" + "\nGerman Luis Cruz Martinez" + "\n\n"
						+ "Derechos reservados UMAR " + '\u00A9' + " 2023",
				"Acerca de... Teclados y pianos Cruz", JOptionPane.INFORMATION_MESSAGE,
				new ImageIcon(getClass().getResource("/Imagenes/piano.png")));

	}

	private void metodoConsultar() {
		// TODO Auto-generated method stub

	}

	private void metodoCatalogo() {
		
		// TODO Auto-generated method stub
		new Catalogo(this,teclados);
	}

	private void metodoGuardar() {
		try (ObjectOutputStream escrito = new ObjectOutputStream(new FileOutputStream("teclados.dat"))) {
			escrito.writeObject(teclados);
			JOptionPane.showMessageDialog(null, "El archivo se ha guardado correctamente", "Guardar", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "No se pudo guardar el archivo correctamente", "Guardar", JOptionPane.ERROR_MESSAGE);
		}

	}

	@SuppressWarnings("unchecked")
	private void metodoAbrir() throws ClassNotFoundException  {
		try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream("teclados.dat"))) {
			teclados = (ArrayList<TecladoMusical>)lector.readObject();
			JOptionPane.showMessageDialog(null, "El archivo se abrio correctamente", "Abrir", JOptionPane.INFORMATION_MESSAGE);
		} catch (FileNotFoundException e1) {	
			JOptionPane.showMessageDialog(null, "El archivo no existe", "Abrir", JOptionPane.ERROR_MESSAGE);
			teclados = new ArrayList<TecladoMusical>() ;
			
		} catch (IOException e2) {
			JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo correctamente", "Abrir", JOptionPane.ERROR_MESSAGE);
			
		} 

	}

}
