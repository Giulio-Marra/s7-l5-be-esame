package giulio_marra.s7_l5_be_esame.excepitions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
