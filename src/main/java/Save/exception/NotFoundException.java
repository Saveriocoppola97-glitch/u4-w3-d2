package Save.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(long id) {
        super ("il record con id " + id + " non è stato trovato");
    }
}
