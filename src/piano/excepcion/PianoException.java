package piano.excepcion;

public class PianoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static final String 	MARCA_OBLIGATORIO="El nombre de la marca no puede estar vacio";
	public static final String 	MODELO_OBLIGATORIO="El modelo no puede estar vacio";
	public static final String 	PRECIO_OBLIGATORIO="El precio no puede estar vacio";
	public static final String 	EFECTOS_OBLIGATORIO="El efecto no puede estar vacio";
	public static final String 	NUN_TECLAS="El numero de telcas debe ser entero";
	public static final String 	NUM_RITMOS="El numero de ritmos debe ser entero";
	public static final String 	NUN_PESO="El peso debe debe ser entero";
	public static final String 	RANGO_TECLAS="El rango de telcas debe ser menor o igual que 88 y mayor a 0";
	public static final String 	RANGO_RITMOS="El rango de telcas debe ser menor o igual que 1000 y mayor a 0";
	public static final String 	PRECIO_RANGO="El rango del precio debe ser menor o igual que 500000 y mayor a 0";
	public static final String 	RANGO_PESO="El rango del peso debe ser menor o igual que 200 y mayor a 0";
	 public PianoException(String mensaje){
		super (mensaje);
	}
	
	
	

}
