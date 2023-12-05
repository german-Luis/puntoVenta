package piano.gui;

import piano.dominio.TecladoMusical;
import piano.excepcion.PianoException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DateFormat;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import utilerias.MiFocusTraversalPolicy;

public class Catalogo extends JDialog implements ActionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private JComboBox<TecladoMusical> spinnerPiano;
	private ArrayList<TecladoMusical> teclados;

	private JButton nuevoBoton;

	private JButton modificarBoton;

	private JButton guardarBoton;

	private JButton eliminarBoton;

	private JButton cancelarBoton;

	private JMenu operacionesMenu;

	private AbstractButton nuevoMenu;

	private JMenuItem guardarMenu;

	private JMenuItem modificarMenu;

	private JMenuBar barraMenu;

	private JMenuItem eliminarMenu;

	private JMenuItem cancelarMenu;

	private JTextField campoModelo;

	private JTextField campoPrecio;

	private JDateChooser campoFecha;

	private JTextField campoPeso;

	private JTextField campoEfectos;

	private JTextField campoRitmos;

	private JTextField campoTeclas;

	private JRadioButtonMenuItem lcdRadio;

	private JRadioButtonMenuItem touchRadio;

	private JRadioButtonMenuItem ningunRadio;

	private JCheckBox pedalCheck;

	private JCheckBox fundaCheck;

	private JCheckBox soporteCheck;

	private JLabel imagenLabel;

	private JButton selecionarBoton;

	private JTextField campoMarca;

	private JComboBox<String> tipoSpinner;

	private JButton agregarBoton;

	private JList<String> efectosLista;

	private JScrollPane listaScroll;

	private TecladoMusical teclado;

	private JLabel auxLabel;

	private Vector<String> efecto;

	private boolean esNuevo = true;

	private String rutaImagen;

	private JMenuItem exportarMenu;

	Catalogo(VentanaPrincipal ventana, ArrayList<TecladoMusical> teclado) {
		super(ventana, "Catálogo piano", true);
		if(teclado!=null) {
			teclados = teclado;
		}
		

		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagenes/piano.png")));
		this.setLayout(new BorderLayout());
		JPanel panelNorte = new JPanel();
		JPanel panelSur = new JPanel();
		JPanel panelAux = new JPanel();
		JPanel panelCentro = new JPanel();
		panelCentro.setLayout(new GridLayout(7, 4));
		// -----------------------Aciones-----------------------------------
		Action accionNuevo = new AbstractAction("Nuevo", new ImageIcon(getClass().getResource("/imagenes/Nuevo.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				metodoNuevo();
			}

		};
		accionNuevo.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));

		accionNuevo.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_N));

		Action accionGuardar = new AbstractAction("Guardar",
				new ImageIcon(getClass().getResource("/imagenes/Guardar.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				metodoGuardar();

			}

		};
		accionGuardar.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));

		accionGuardar.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_G));

		Action accionCancelar = new AbstractAction("Cancelar",
				new ImageIcon(getClass().getResource("/imagenes/Cancelar.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				metodoCancelar();
			}

		};
		accionCancelar.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_C));

		Action accionEliminar = new AbstractAction("Eliminar",
				new ImageIcon(getClass().getResource("/imagenes/Eliminar.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				metodoEliminar();
			}

		};

		accionEliminar.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_E));

		Action accionModificar = new AbstractAction("Modificar",
				new ImageIcon(getClass().getResource("/imagenes/Modificar.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				metodoModificar();
			}

		};
		accionModificar.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
		accionModificar.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_M));

		Action accionExportar = new AbstractAction("Exportar",
				new ImageIcon(getClass().getResource("/imagenes/exportar.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				metodoExportar();
			}

		};
		accionExportar.putValue(Action.ACCELERATOR_KEY,
				KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
		accionExportar.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_E));

		operacionesMenu = new JMenu("Operaciones");
		operacionesMenu.setIcon(new ImageIcon(getClass().getResource("/imagenes/Operaciones.png")));
		operacionesMenu.setMnemonic(KeyEvent.VK_R);
		nuevoMenu = new JMenuItem(accionNuevo);
		guardarMenu = new JMenuItem(accionGuardar);
		modificarMenu = new JMenuItem(accionModificar);
		eliminarMenu = new JMenuItem(accionEliminar);
		cancelarMenu = new JMenuItem(accionCancelar);
		exportarMenu = new JMenuItem(accionExportar);

		operacionesMenu.add(nuevoMenu);
		operacionesMenu.add(guardarMenu);
		operacionesMenu.add(modificarMenu);
		operacionesMenu.add(eliminarMenu);
		operacionesMenu.add(cancelarMenu);
		operacionesMenu.addSeparator();
		operacionesMenu.add(exportarMenu);

		barraMenu = new JMenuBar();
		barraMenu.add(operacionesMenu);
		this.setJMenuBar(barraMenu);

		JLabel etiqueta = new JLabel("Piano");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(etiqueta);

		spinnerPiano = new JComboBox<TecladoMusical>();

		spinnerPiano.setPreferredSize(new Dimension(350, 30));
		spinnerPiano.addActionListener(this);
		panelAux.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelAux.add(spinnerPiano);
		panelNorte.add(panelAux);
		this.add(panelNorte, BorderLayout.NORTH);

		JLabel etiqueta1 = new JLabel("Marca:");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelAux.add(etiqueta1);
		panelCentro.add(panelAux);

		campoMarca = new JTextField();
		campoMarca.setPreferredSize(new Dimension(150, 25));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(campoMarca);
		panelCentro.add(panelAux);

		JLabel etiqueta2 = new JLabel("Modelo:");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelAux.add(etiqueta2);
		panelCentro.add(panelAux);

		campoModelo = new JTextField();
		campoModelo.setPreferredSize(new Dimension(150, 25));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(campoModelo);
		panelCentro.add(panelAux);

		JLabel etiqueta3 = new JLabel("Tipo:");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelAux.add(etiqueta3);
		panelCentro.add(panelAux);

		tipoSpinner = new JComboBox<String>();
		tipoSpinner.setPreferredSize(new Dimension(150, 25));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(tipoSpinner);
		panelCentro.add(panelAux);

		JLabel etiqueta8 = new JLabel("Tipo de pantalla:");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelAux.add(etiqueta8);
		panelCentro.add(panelAux);

		lcdRadio = new JRadioButtonMenuItem("LCD");

		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(lcdRadio);

		touchRadio = new JRadioButtonMenuItem("Touch");

		panelAux.add(touchRadio);
		ningunRadio = new JRadioButtonMenuItem("Ninguno");

		panelAux.add(ningunRadio);

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(lcdRadio);
		grupo.add(touchRadio);
		grupo.add(ningunRadio);
		lcdRadio.setSelected(true);
		panelCentro.add(panelAux);
		JLabel etiqueta4 = new JLabel("Número de teclas:");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelAux.add(etiqueta4);
		panelCentro.add(panelAux);

		campoTeclas = new JTextField();
		campoTeclas.setPreferredSize(new Dimension(150, 25));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(campoTeclas);
		panelCentro.add(panelAux);

		JLabel etiqueta5 = new JLabel("Cantidad de ritmos:");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelAux.add(etiqueta5);
		panelCentro.add(panelAux);

		campoRitmos = new JTextField();
		campoRitmos.setPreferredSize(new Dimension(150, 25));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(campoRitmos);
		panelCentro.add(panelAux);

		JLabel etiqueta6 = new JLabel("Efectos digitales:");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelAux.add(etiqueta6);
		panelCentro.add(panelAux);

		campoEfectos = new JTextField();
		campoEfectos.setPreferredSize(new Dimension(100, 25));

		Action accionAgregar = new AbstractAction("Agregar",
				new ImageIcon(getClass().getResource("/imagenes/agregar.png"))) {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				metodoAgregar();
			}

		};
		accionAgregar.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_A));
		agregarBoton = new JButton(accionAgregar);
		agregarBoton.setPreferredSize(new Dimension(150, 30));
		efectosLista = new JList<String>();
		efectosLista.setPreferredSize(new Dimension(100, 80));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(campoEfectos);
		panelAux.add(agregarBoton);
		panelCentro.add(panelAux);

		JLabel etiqueta7 = new JLabel("Accesorios:");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelAux.add(etiqueta7);
		panelCentro.add(panelAux);

		pedalCheck = new JCheckBox("Pedal");
		fundaCheck = new JCheckBox("Funda");
		soporteCheck = new JCheckBox("Base");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(pedalCheck);
		panelAux.add(fundaCheck);
		panelAux.add(soporteCheck);
		panelCentro.add(panelAux);

		auxLabel = new JLabel();
		auxLabel.setPreferredSize(new Dimension(50, 100));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelAux.add(auxLabel);
		panelCentro.add(panelAux);

		listaScroll = new JScrollPane(efectosLista);
		listaScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		listaScroll.setPreferredSize(new Dimension(180, 60));
		listaScroll.setEnabled(false);
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(listaScroll);
		panelCentro.add(panelAux);

		JLabel etiqueta9 = new JLabel("Peso:");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelAux.add(etiqueta9);
		panelCentro.add(panelAux);

		campoPeso = new JTextField();
		campoPeso.setPreferredSize(new Dimension(150, 25));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(campoPeso);
		panelCentro.add(panelAux);

		JLabel etiqueta10 = new JLabel("Fecha de lanzamiento:");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelAux.add(etiqueta10);
		panelCentro.add(panelAux);

		campoFecha = new JDateChooser(new Date());
		campoFecha.setPreferredSize(new Dimension(150, 25));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(campoFecha);
		panelCentro.add(panelAux);

		JLabel etiqueta12 = new JLabel("Imagen:");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelAux.add(etiqueta12);
		panelCentro.add(panelAux);

		imagenLabel = new JLabel();
		imagenLabel.setPreferredSize(new Dimension(100, 100));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(imagenLabel);
		panelCentro.add(panelAux);

		JLabel etiqueta11 = new JLabel("Precio:");
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelAux.add(etiqueta11);
		panelCentro.add(panelAux);

		campoPrecio = new JTextField();
		campoPrecio.setPreferredSize(new Dimension(150, 25));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(campoPrecio);
		panelCentro.add(panelAux);

		Action accionSeleccionar = new AbstractAction("Seleccionar",
				new ImageIcon(getClass().getResource("/imagenes/selecionar.png"))) {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				metodoSeleccionar();
			}
		};
		accionSeleccionar.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_S));
		auxLabel = new JLabel();
		auxLabel.setPreferredSize(new Dimension(50, 100));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelAux.add(auxLabel);
		panelCentro.add(panelAux);

		selecionarBoton = new JButton(accionSeleccionar);
		selecionarBoton.setPreferredSize(new Dimension(150, 35));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelAux.add(selecionarBoton);
		panelCentro.add(panelAux);

		this.add(panelCentro, BorderLayout.CENTER);
		nuevoBoton = new JButton(accionNuevo);
		nuevoBoton.getActionMap().put("Nuevo", accionNuevo);
		nuevoBoton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionNuevo.getValue(Action.ACCELERATOR_KEY), "Nuevo");
		nuevoBoton.setPreferredSize(new Dimension(150, 35));
		panelAux = new JPanel();
		panelAux.setLayout(new FlowLayout(FlowLayout.CENTER));

		guardarBoton = new JButton(accionGuardar);
		guardarBoton.getActionMap().put("Guardar", accionGuardar);
		guardarBoton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionGuardar.getValue(Action.ACCELERATOR_KEY), "Guardar");
		guardarBoton.setPreferredSize(new Dimension(150, 35));

		modificarBoton = new JButton(accionModificar);
		modificarBoton.getActionMap().put("Modificar", accionModificar);
		modificarBoton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionModificar.getValue(Action.ACCELERATOR_KEY), "Modificar");
		modificarBoton.setPreferredSize(new Dimension(150, 35));
		eliminarBoton = new JButton(accionEliminar);
		eliminarBoton.getActionMap().put("Eliminar", accionEliminar);
		eliminarBoton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionEliminar.getValue(Action.ACCELERATOR_KEY), "Eliminar");
		eliminarBoton.setPreferredSize(new Dimension(150, 35));
		panelAux.add(eliminarBoton);

		cancelarBoton = new JButton(accionCancelar);
		cancelarBoton.getActionMap().put("Cancelar", accionCancelar);
		cancelarBoton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put((KeyStroke) accionCancelar.getValue(Action.ACCELERATOR_KEY), "Cancelar");
		cancelarBoton.setPreferredSize(new Dimension(150, 35));
		panelAux.add(cancelarBoton);
		panelAux.add(eliminarBoton);
		panelAux.add(modificarBoton);
		panelAux.add(guardarBoton);
		panelAux.add(nuevoBoton);
		panelSur.add(panelAux);
		inicializar();
		this.add(panelSur, BorderLayout.SOUTH);
		this.setSize(1100, 800);
		establecerPoliticaFoco();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(spinnerPiano)) {
			motrarInformacion();
		}

	}

	private void verificarLista() {

		if (spinnerPiano.getItemCount() > 0) {
			spinnerPiano.setSelectedIndex(0);
			modificarBoton.setEnabled(true);
			modificarMenu.setEnabled(true);
			eliminarBoton.setEnabled(true);
			eliminarMenu.setEnabled(true);
			exportarMenu.setEnabled(true);
			spinnerPiano.setEnabled(true);
		} else {
			modificarBoton.setEnabled(false);
			modificarMenu.setEnabled(false);
			eliminarBoton.setEnabled(false);
			eliminarMenu.setEnabled(false);
			exportarMenu.setEnabled(false);
			spinnerPiano.setEnabled(false);
		}
		spinnerPiano.setEditable(false);
	}

	private void motrarInformacion() {
		limpiarCampos();

		if (spinnerPiano.getSelectedIndex() >= 0) {
			teclado = teclados.get(spinnerPiano.getSelectedIndex());
			String peso = "" + teclado.getPesoTeclado();
			String[] accesorios = teclado.getAccesorios();
			String teclas = "" + teclado.getNumeroTeclas();
			String ritmos = "" + teclado.getCantidadRitmos();
			campoTeclas.setText(teclas);
			campoRitmos.setText(ritmos);
			campoPrecio.setText(teclado.getPrecio().getPrecio());
			campoMarca.setText(teclado.getMarca());
			campoModelo.setText(teclado.getModelo());
			tipoSpinner.setSelectedIndex(teclado.getTipoTeclado());
			campoFecha.setDate(teclado.getFechaLanzamiento());
			rutaImagen = teclado.getImagenTeclado();
			mostrarImagen();
			if (teclado.getPantalla().equals("LCD")) {
				lcdRadio.setSelected(true);
			} else if (teclado.getPantalla().equals("Touch")) {
				touchRadio.setSelected(true);
			} else {
				ningunRadio.setSelected(true);
			}

			for (int i = 0; i < accesorios.length; i++) {
				if (accesorios[i].compareToIgnoreCase("Pedal") == 0) {
					pedalCheck.setSelected(true);
				}
				if (accesorios[i].compareToIgnoreCase("Funda") == 0) {
					fundaCheck.setSelected(true);
				}
				if (accesorios[i].compareToIgnoreCase("Base") == 0) {
					soporteCheck.setSelected(true);
				}

			}
			campoEfectos.setText("");
			campoPeso.setText(peso);
			for (int i = 0; i < teclado.getEfectosDigitales().length; i++) {
				for (int j = 0; j < efecto.size(); j++) {
					if (efecto.get(j).compareTo(teclado.getEfectosDigitales()[i]) == 0) {
						efectosLista.setSelectedIndex(j);
					}

				}

			}

		}

	}

	private void establecerPoliticaFoco() {

		Vector<Component> componentes = new Vector<Component>();
		componentes.add(nuevoBoton);
		componentes.add(modificarBoton);
		componentes.add(guardarBoton);
		componentes.add(eliminarBoton);
		componentes.add(cancelarBoton);
		componentes.add(spinnerPiano);
		componentes.add(campoMarca);
		componentes.add(tipoSpinner);
		componentes.add(campoTeclas);
		componentes.add(campoEfectos);
		componentes.add(agregarBoton);
		componentes.add(campoFecha);
		componentes.add(campoPrecio);
		componentes.add(lcdRadio);
		componentes.add(touchRadio);
		componentes.add(campoRitmos);
		componentes.add(campoPeso);
		MiFocusTraversalPolicy politica = new MiFocusTraversalPolicy(componentes);
		this.setFocusTraversalPolicy(politica);
	}

	private void metodoNuevo() {
		limpiarCampos();
		esNuevo = true;
		spinnerPiano.setEnabled(false);
		habilitarCampos();
		nuevoBoton.setEnabled(false);
		nuevoMenu.setEnabled(false);
		modificarBoton.setEnabled(false);
		modificarMenu.setEnabled(false);
		eliminarBoton.setEnabled(false);
		eliminarMenu.setEnabled(false);
		exportarMenu.setEnabled(false);
		guardarBoton.setEnabled(true);
		guardarMenu.setEnabled(true);
		cancelarBoton.setEnabled(true);
		cancelarMenu.setEnabled(true);
		spinnerPiano.setEnabled(false);
	}

	private void metodoGuardar() {
		String rutaModificar = "";
		if (esNuevo) {
			teclado = new TecladoMusical();
		} else {
			teclado = teclados.get(spinnerPiano.getSelectedIndex());
			rutaModificar = teclado.getImagenTeclado();
		}
		try {

			String pantalla;
			List<String> milista = new ArrayList<String>();
			String[] lista;
			teclado.setNumeroTeclas(campoTeclas.getText());
			assert Integer.parseInt(campoTeclas.getText()) > 0 : "El número de teclas debe ser positivo";
			teclado.setCantidadRitmos(campoRitmos.getText());
			teclado.setMarca(campoMarca.getText());

			teclado.setModelo(campoModelo.getText());
			teclado.setTipoTeclado(tipoSpinner.getSelectedIndex());
			if (lcdRadio.isSelected()) {
				pantalla = "LCD";
			} else if (touchRadio.isSelected()) {
				pantalla = "Touch";
			} else {
				pantalla = "Ninguno";
			}
			teclado.setPantalla(pantalla);
			teclado.setPesoTeclado(campoPeso.getText());
			assert Double.parseDouble(campoPeso.getText()) > 0 : "El peso del piano debe ser positivo";
			teclado.setImagenTeclado("Imagen");

			if (pedalCheck.isSelected()) {
				milista.add("Pedal");
			}
			if (fundaCheck.isSelected()) {
				milista.add("funda");
			}
			if (soporteCheck.isSelected()) {
				milista.add("base");
			}
			String[] accesorios = new String[milista.size()];
			accesorios = (String[]) milista.toArray(accesorios);
			teclado.setAccesorios(accesorios);
			lista = new String[efectosLista.getSelectedValuesList().size()];
			lista = efectosLista.getSelectedValuesList().toArray(lista);
			teclado.setEfectosDigitales(lista);
			teclado.setPrecio(campoPrecio.getText());
			teclado.setFechaLanzamiento(campoFecha.getDate());
			File directorio = new File("imagenes");
			if (!directorio.exists()) {
				directorio.mkdir();
			}
			String extension = rutaImagen.substring(rutaImagen.length() - 4, rutaImagen.length());

			if (rutaModificar.length() > 1 && !rutaImagen.equals(rutaModificar)) {
				new File(rutaModificar).delete();
			}
			if (!rutaImagen.equals("/imagenes/pianoPorDefecto.png") && !rutaImagen.equals(rutaModificar)) {
				try (BufferedInputStream lector = new BufferedInputStream(new FileInputStream(rutaImagen));
						BufferedOutputStream escritura = new BufferedOutputStream(new FileOutputStream(
								"imagenes/" + campoMarca.getText() + campoModelo.getText() + extension))) {
					byte[] bufer = new byte[1024];
					int bytesleidos = lector.read(bufer);
					while (bytesleidos > 0) {
						escritura.write(bufer, 0, bytesleidos);
						bytesleidos = lector.read(bufer);
					}
					lector.close();
					escritura.close();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "No se pudo guardar la imagen", "Guardar imagen",
							JOptionPane.ERROR_MESSAGE);
				}
				teclado.setImagenTeclado("imagenes/" + campoMarca.getText() + campoModelo.getText() + extension);
			} else if (rutaImagen.equals("/imagenes/pianoPorDefecto.png")) {
				teclado.setImagenTeclado("/imagenes/pianoPorDefecto.png");
			} else {
				teclado.setImagenTeclado(rutaModificar);
			}			
			rutaImagen = null;
			if (esNuevo) {
				teclados.add(teclado);
				spinnerPiano.addItem(teclado);
				JOptionPane.showMessageDialog(this, "Los datos se han guardado corectamente", "Guardar",
						JOptionPane.INFORMATION_MESSAGE);

			} else {
				JOptionPane.showMessageDialog(this, "Los datos se han modificado corectamente", "Modificar",
						JOptionPane.INFORMATION_MESSAGE);
			}

			metodoCancelar();
		} catch (PianoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void metodoEliminar() {

		int opcion = JOptionPane.showConfirmDialog(this, "¿Desea eliminar los datos del teclado?", "Eliminar",
				JOptionPane.YES_NO_OPTION);
		if (opcion == JOptionPane.YES_OPTION) {

			teclados.remove(spinnerPiano.getSelectedIndex());
			spinnerPiano.remove(spinnerPiano.getSelectedIndex());
			JOptionPane.showMessageDialog(this, "Se ha eliminado " + campoMarca.getText() + " correctamente.",
					"Eliminar", JOptionPane.INFORMATION_MESSAGE);
			metodoCancelar();
		}

	}

	private void metodoCancelar() {

		limpiarCampos();
		desabilitarCampos();
		verificarLista();
		desabilitarBotones();
	}

	private void metodoModificar() {

		esNuevo = false;
		habilitarCampos();
		guardarBoton.setEnabled(true);
		guardarMenu.setEnabled(true);
		cancelarBoton.setEnabled(true);
		cancelarMenu.setEnabled(true);
		nuevoBoton.setEnabled(false);
		nuevoMenu.setEnabled(false);
		modificarBoton.setEnabled(false);
		modificarMenu.setEnabled(false);
		cancelarBoton.setEnabled(false);
		cancelarMenu.setEnabled(false);
		spinnerPiano.setEnabled(false);

	}

	private void metodoAgregar() {
		boolean existe = false;
		for (int i = 0; i < efecto.size(); i++) {
			if (campoEfectos.getText().compareToIgnoreCase(efecto.get(i)) == 0) {
				existe = true;
			}
		}
		if (!existe) {
			efecto.add(campoEfectos.getText().trim());
			efectosLista.setListData(efecto);
			try(
					PrintWriter escritura=new PrintWriter(new FileWriter(new File("efectosDigitales.txt")))){
					String linea=efecto.get(0);
					escritura.println(linea);
					for(int i=1;i<efecto.size();i++) {
						escritura.write(efecto.get(i)+"\n");
					}						
				}catch(EOFException e) {
					JOptionPane.showMessageDialog(null, "No se pudo abrir el archivo de los efectos digitales", "Efectos digitales",
							JOptionPane.ERROR_MESSAGE);
				}catch(IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Efectos digitales",
							JOptionPane.ERROR_MESSAGE);
				}

		}
	}

	private void metodoSeleccionar() {
		JFileChooser dialogo = new JFileChooser();
		dialogo.setDialogTitle("“Seleccione una imagen para piano");
		FileFilter filtro1 = new FileNameExtensionFilter("Archivos de imagenes", "GIF", "JPG", "PNG", "gif", "jpg",
				"png");
		dialogo.setFileFilter(filtro1);
		dialogo.setApproveButtonText("Aceptar");
		dialogo.setApproveButtonMnemonic(KeyEvent.VK_A);
		dialogo.setApproveButtonToolTipText("Abrir imagen selecionada");
		;
		dialogo.setAcceptAllFileFilterUsed(false);
		dialogo.setCurrentDirectory(new File(System.getProperty("user.dir")));
		dialogo.setSelectedFile(null);
		dialogo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		dialogo.setMultiSelectionEnabled(false);
		int valor = dialogo.showOpenDialog(this);
		if (valor == JFileChooser.APPROVE_OPTION) {
			File archivo = dialogo.getSelectedFile();
			if (archivo.exists()) {
				rutaImagen = archivo.getAbsolutePath();
			} else {
				JOptionPane.showMessageDialog(null, "El archivo no existe", "Error al abrir la imagen",
						JOptionPane.WARNING_MESSAGE);
			}
		}
		mostrarImagen();
	}

	private void mostrarImagen() {
		ImageIcon imagenFondo;
		if (rutaImagen.equals("/imagenes/pianoPorDefecto.png")) {
			imagenFondo = new ImageIcon(getClass().getResource(rutaImagen));
		} else {
			imagenFondo = new ImageIcon(rutaImagen);
		}
		Image imagenEscalada;
		if (imagenFondo.getIconHeight() > imagenFondo.getIconWidth()) {
			imagenEscalada = imagenFondo.getImage().getScaledInstance(-1, imagenLabel.getWidth(), Image.SCALE_SMOOTH);
		} else {
			imagenEscalada = imagenFondo.getImage().getScaledInstance(imagenLabel.getHeight(), -1, Image.SCALE_SMOOTH);
		}
		imagenLabel.setIcon(new ImageIcon(imagenEscalada));

	}

	private void desabilitarBotones() {
		nuevoBoton.setEnabled(true);
		nuevoMenu.setEnabled(true);
		guardarBoton.setEnabled(false);
		cancelarBoton.setEnabled(false);
		guardarMenu.setEnabled(false);
		cancelarMenu.setEnabled(false);

	}

	private void limpiarCampos() {
		campoMarca.setText("");
		campoTeclas.setText("");
		campoEfectos.setText("");
		campoFecha.setDate(null);
		campoRitmos.setText("");
		campoPrecio.setText("");
		campoPeso.setText("");
		rutaImagen = "/imagenes/pianoPorDefecto.png";
		mostrarImagen();
		campoModelo.setText("");
		campoFecha.setDate(new Date());
		tipoSpinner.setSelectedIndex(0);
		lcdRadio.setSelected(true);
		touchRadio.setSelected(false);
		ningunRadio.setSelected(false);
		pedalCheck.setSelected(false);
		fundaCheck.setSelected(false);
		soporteCheck.setSelected(false);

	}

	private void metodoExportar() {
		teclado = teclados.get(spinnerPiano.getSelectedIndex());
		Object[] opciones = { "Sí", "No" };
		JFileChooser dialogo = new JFileChooser();
		File archivo = null;
		dialogo.setDialogTitle("Guardar como");
		FileFilter filtro1 = new FileNameExtensionFilter("Archivo JSON", "json", "JSON");
		dialogo.setFileFilter(filtro1);
		dialogo.setAcceptAllFileFilterUsed(false);
		dialogo.setCurrentDirectory(new File(System.getProperty("user.dir")));
		dialogo.setSelectedFile(null);
		dialogo.setMultiSelectionEnabled(false);
		int valor = dialogo.showSaveDialog(null);
		if (valor == JFileChooser.APPROVE_OPTION) {
			archivo = dialogo.getSelectedFile();
			if (!archivo.equals(null) && !archivo.getName().toUpperCase().endsWith(".json")) {
				archivo = new File(archivo.getAbsolutePath() + ".JSON");
			}
			if (archivo.exists()) {
				int respuesta1 = JOptionPane.showOptionDialog(null,
						archivo.getName() + " ya existe.\n ¿Desea reemplazarlo?", "Confirmación",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[1]);
				if (respuesta1 != 0) {
					return;
				}
			}
			try {
				DateFormat f = DateFormat.getDateInstance(DateFormat.FULL);
				JsonObjectBuilder piano = Json.createObjectBuilder();
				JsonArrayBuilder accesoriosPiano = Json.createArrayBuilder();
				JsonArrayBuilder efectosPiano = Json.createArrayBuilder();

				for (String accesorios : teclado.getAccesorios()) {
					accesoriosPiano.add(accesorios);
				}
				for (String efectos : teclado.getEfectosDigitales()) {
					efectosPiano.add(efectos);
				}

				piano.add("numeroTeclas", teclado.getNumeroTeclas());
				piano.add("cantidadRitmos", teclado.getCantidadRitmos());
				piano.add("precio", teclado.getPrecio().getPrecio());
				piano.add("marca", teclado.getMarca());
				piano.add("modelo", teclado.getModelo());
				piano.add("fechaLanzamiento", f.format(teclado.getFechaLanzamiento()));
				piano.add("tipoTeclado", teclado.getTipoTeclado());
				piano.add("pantalla", teclado.getPantalla());
				piano.add("pesoTeclado", teclado.getPesoTeclado());
				piano.add("accesorios", accesoriosPiano);
				piano.add("efectosDijitales", efectosPiano);
				// -----------------------------
				JsonObject workerJsonObject = piano.build();
				Writer writer = new FileWriter(archivo.getAbsolutePath());
				Map<String, Boolean> config = new HashMap<>();
				config.put(JsonGenerator.PRETTY_PRINTING, true);
				JsonWriterFactory writerFactory = Json.createWriterFactory(config);
				JsonWriter jsonWriter = writerFactory.createWriter(writer);
				jsonWriter.write(workerJsonObject);
				jsonWriter.close();
				writer.close();

				JOptionPane.showMessageDialog(null, "El archivo se ha guardado exitosamente.", "Exportar JSON ",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No se pudo guardar el archivo JSON", "Exportar JSON",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	private void inicializar() {
		for (TecladoMusical objeto : teclados) {
			spinnerPiano.addItem(objeto);
		}
		guardarBoton.setEnabled(false);
		cancelarBoton.setEnabled(false);
		guardarMenu.setEnabled(false);
		cancelarMenu.setEnabled(false);
		nuevoBoton.setEnabled(true);
		nuevoMenu.setEnabled(true);
		desabilitarCampos();
		tipoSpinner.addItem("Arreglistas");
		tipoSpinner.addItem("Sintetizadores");
		tipoSpinner.addItem("Workstation");
		tipoSpinner.addItem("Digitales o eléctricos");
		tipoSpinner.addItem("Controladores");
		efecto = new Vector<String>();
		try (BufferedReader lector = new BufferedReader(new FileReader(new File("efectosDigitales.txt")))) {
			String linea = lector.readLine();
			while (linea != null) {
				efecto.add(linea);
				linea = lector.readLine();		
			}
		}catch (EOFException e) {
			JOptionPane.showMessageDialog(null, "No se pudo abrir la lista de efectos", "Abrir efectos",
					JOptionPane.ERROR_MESSAGE);
		} catch (IOException e2) {
			
		}
		efectosLista = new JList<String>(efecto);
		listaScroll.setViewportView(efectosLista);
		verificarLista();
	}

	private void habilitarCampos() {
		campoMarca.setEditable(true);
		campoTeclas.setEditable(true);
		campoEfectos.setEditable(true);
		campoFecha.setEnabled(true);
		campoRitmos.setEditable(true);
		campoPrecio.setEditable(true);
		campoPeso.setEditable(true);
		campoModelo.setEditable(true);
		listaScroll.setEnabled(true);
		efectosLista.setEnabled(true);
		tipoSpinner.setEnabled(true);
		pedalCheck.setEnabled(true);
		fundaCheck.setEnabled(true);
		soporteCheck.setEnabled(true);
		lcdRadio.setEnabled(true);
		touchRadio.setEnabled(true);
		ningunRadio.setEnabled(true);
		agregarBoton.setEnabled(true);
		selecionarBoton.setEnabled(true);
	}

	private void desabilitarCampos() {
		campoMarca.setEditable(false);
		campoTeclas.setEditable(false);
		campoEfectos.setEditable(false);
		campoFecha.setEnabled(false);
		campoRitmos.setEditable(false);
		campoPrecio.setEditable(false);
		campoPeso.setEditable(false);
		campoModelo.setEditable(false);
		efectosLista.setEnabled(false);
		listaScroll.setEnabled(false);
		efectosLista.setEnabled(false);
		tipoSpinner.setEnabled(false);
		pedalCheck.setEnabled(false);
		fundaCheck.setEnabled(false);
		soporteCheck.setEnabled(false);
		lcdRadio.setEnabled(false);
		touchRadio.setEnabled(false);
		ningunRadio.setEnabled(false);
		agregarBoton.setEnabled(false);
		selecionarBoton.setEnabled(false);
	}

}
