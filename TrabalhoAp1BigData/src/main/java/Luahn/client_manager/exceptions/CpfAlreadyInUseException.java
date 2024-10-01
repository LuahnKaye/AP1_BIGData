package Luahn.client_manager.exceptions;

public class CpfAlreadyInUseException extends RuntimeException {
    public CpfAlreadyInUseException(String message) {
        super(message);
    }
}