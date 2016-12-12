package ca.ulaval.glo4002.flycheckin.reservation.rest.application;

public class RepeatableContratException extends RuntimeException{
	
	public RepeatableContratException(Class<?> contract) {
        super("That's a second time that your registering this : '" + contract.getCanonicalName() + "'");
    }

    private static final long serialVersionUID = 1L;

}
