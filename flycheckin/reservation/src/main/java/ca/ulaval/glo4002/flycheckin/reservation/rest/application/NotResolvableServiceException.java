package ca.ulaval.glo4002.flycheckin.reservation.rest.application;

public class NotResolvableServiceException extends RuntimeException {
	
	public NotResolvableServiceException(Class<?> contract) {
        super("Unable to resolve a service for '" + contract.getCanonicalName() + "'. Did you forget to register it?");
    }

    private static final long serialVersionUID = 1L;

}
