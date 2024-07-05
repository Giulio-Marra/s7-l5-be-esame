package giulio_marra.s7_l5_be_esame.excepitions;

public class BadRequest extends RuntimeException {
    public BadRequest(String message) {
        super(message);
    }
}
