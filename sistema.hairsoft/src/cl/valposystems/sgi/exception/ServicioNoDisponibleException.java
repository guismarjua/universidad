package cl.valposystems.sgi.exception;

public class ServicioNoDisponibleException extends Exception{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6035447206351164865L;

	public ServicioNoDisponibleException() {
		
	}

	public ServicioNoDisponibleException(String message) {
		super(message);
		
	}

	public ServicioNoDisponibleException(Throwable cause) {
		super(cause);
		
	}

	public ServicioNoDisponibleException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ServicioNoDisponibleException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}
}
