package mx.edu.utez.proyecto1E.exception.custom;

public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException(String message){
        super(message);
    }
}

