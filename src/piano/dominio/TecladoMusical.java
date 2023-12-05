package piano.dominio;

import java.io.Serializable;
/*
Autor: German Luis Cruz Martinez
Práctica: Reto 2
Grupo: 412
Fecha: 16/05/2023
*/
import java.util.Date;

import piano.excepcion.PianoException;
import umar.lib.*;

public class TecladoMusical implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * sirve para indicar el numero de teclas que tiene el teclado
	 */
	private int numeroTeclas;
	/**
	 * sirve para indicar la cantidad de ritmos que esta integrada en el teclado
	 */
	private int cantidadRitmos;
	/**
	 * Sirve para indicar
	 */
	private Precio precio;
	/**
	 * sirve para almacenar el nombre de la marca del teclado
	 */
	private String marca;
	/**
	 * sirve para almacenar el nombre del modelo del teclado
	 */
	private String modelo;
	/**
	 * sirve para guardar la fecha del lanzamiento del teclado
	 */
	private Date fechaLanzamiento;
	/**
	 * sirve para almacenar que tipo de teclado es
	 */
	private int tipoTeclado;
	/**
	 * sirve para guardar el tipo de pantalla que trae
	 */
	private String pantalla;
	/**
	 * sirve para guardar el peso del teclado
	 */
	private double pesoTeclado;
	/**
	 * sirve para almacenar los accesorios que trae el teclado
	 */
	private String[] accesorios;
	/**
	 * sirve para almacenar los efectos digitales que contiene el teclado
	 */
	private String[] efectosDigitales;
	/**
	 * sirve para almacenar la ruta en donde esta la imagen del teclado
	 */
	private String imagenTeclado;

	public int getNumeroTeclas() {
		return numeroTeclas;
	}

	public void setNumeroTeclas(int numeroTeclas) throws PianoException {
		if (numeroTeclas <= 0 && numeroTeclas > 88) {
			throw new PianoException(PianoException.RANGO_TECLAS);
		} else {

			this.numeroTeclas = numeroTeclas;
		}

	}

	public void setNumeroTeclas(String numeroTeclas) throws PianoException {
		numeroTeclas = numeroTeclas.trim();
		try {
			setNumeroTeclas(Integer.parseInt(numeroTeclas));
		} catch (NumberFormatException e) {
			throw new PianoException(PianoException.NUN_TECLAS);
		}

	}

	// -------------------------------------------------------

	public int getCantidadRitmos() {
		return cantidadRitmos;
	}

	public void setCantidadRitmos(int cantidadRitmos) throws PianoException {

		if (cantidadRitmos <= 0 && cantidadRitmos >= 1000) {
			throw new PianoException(PianoException.RANGO_RITMOS);
		} else {

			this.cantidadRitmos = cantidadRitmos;
		}

	}

	public void setCantidadRitmos(String cantidadRitmos) throws PianoException {
		cantidadRitmos = cantidadRitmos.trim();
		try {
			setCantidadRitmos(Integer.parseInt(cantidadRitmos));
		} catch (NumberFormatException e) {
			throw new PianoException(PianoException.NUM_RITMOS);
		}
	}
	// -------------------------------------------------------------------

	public void setPrecio(String precio) throws PianoException {
		precio = precio.trim();
		try {

			if (Double.parseDouble(precio)<=0&&Double.parseDouble(precio)>=500000) {
				throw new PianoException(PianoException.PRECIO_RANGO);
			} else {
				Precio precios = new Precio();
				precios.setPrecio(precio);
				this.setPrecio(precios);
			}

		} catch (NumberFormatException e) {
			throw new PianoException(PianoException.PRECIO_OBLIGATORIO);
		}

	}

	public Precio getPrecio() {
		return precio;
	}

	public void setPrecio(Precio precio) {
		this.precio = precio;
	}

	// ---------------------------------------------------------------------------
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) throws PianoException {
		marca = marca.trim();
		if (marca.length() == 0) {
			throw new PianoException(PianoException.MARCA_OBLIGATORIO);
		} else {
			this.marca = marca;
		}

	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		modelo = modelo.trim();
		this.modelo = modelo;

	}

	public Date getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	public void setFechaLanzamiento(Date fecha) {
		this.fechaLanzamiento = fecha;
	}

	public int getTipoTeclado() {
		return tipoTeclado;
	}

	public void setTipoTeclado(int tipoTeclado) {
		this.tipoTeclado = tipoTeclado;
	}

	public String getPantalla() {
		return pantalla;
	}

	public void setPantalla(String pantalla) {
		this.pantalla = pantalla;
	}
	// -------------------------------------------

	public double getPesoTeclado() {
		return pesoTeclado;
	}

	public void setPesoTeclado(double pesoTeclado) throws PianoException {
		if (pesoTeclado <= 0 && pesoTeclado >= 200) {
			throw new PianoException(PianoException.RANGO_PESO);
		} else {
			this.pesoTeclado = pesoTeclado;

		}

	}

	public void setPesoTeclado(String pesoTeclado) throws PianoException {

		pesoTeclado = pesoTeclado.trim();
		try {
			setPesoTeclado(Double.parseDouble(pesoTeclado));
		} catch (NumberFormatException e) {
			throw new PianoException(PianoException.NUN_PESO);
		}

	}
	// -------------------------------------

	public String[] getAccesorios() {
		return accesorios;
	}

	public void setAccesorios(String[] accesorios) {
		this.accesorios = accesorios;
	}

	public String[] getEfectosDigitales() {
		return efectosDigitales;
	}

	public void setEfectosDigitales(String[] efectosDigitales) {
		this.efectosDigitales = efectosDigitales;
	}

	public String getImagenTeclado() {
		return imagenTeclado;
	}

	public void setImagenTeclado(String imagenTeclado) {
		
		this.imagenTeclado = imagenTeclado;
	}

	public TecladoMusical() {
		this.numeroTeclas = 0;//
		this.cantidadRitmos = 0;//
		this.precio = null;//
		this.marca = "";//
		this.modelo = "";//
		this.fechaLanzamiento = new Date();//
		this.tipoTeclado = 0;//
		this.pantalla = "";///
		this.pesoTeclado = 0;
		this.accesorios = null;//
		this.efectosDigitales = null;//
		this.imagenTeclado = "";
	}

	@Override
	public String toString() {
		return "Marca=" + this.marca + ", Modelo=" + this.modelo;
	}

}
