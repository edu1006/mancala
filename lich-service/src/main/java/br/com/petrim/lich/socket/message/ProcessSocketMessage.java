package br.com.petrim.lich.socket.message;

public class ProcessSocketMessage {

    private String message;

    public ProcessSocketMessage() {
        // Default Constructor
    }

    public ProcessSocketMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
