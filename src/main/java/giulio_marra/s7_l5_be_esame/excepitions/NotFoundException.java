package giulio_marra.s7_l5_be_esame.excepitions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id) {
        super("Record con questo id non trovato");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
