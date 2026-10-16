package mx.edu.utez.proyecto1E.exception.custom;

public class CustomBadRequestException extends RuntimeException {
    public CustomBadRequestException(String message){
        super(message);
    }
}