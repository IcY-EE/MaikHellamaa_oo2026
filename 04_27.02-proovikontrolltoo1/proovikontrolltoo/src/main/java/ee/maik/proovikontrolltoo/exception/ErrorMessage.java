package ee.maik.proovikontrolltoo.exception;

import lombok.Data;
import java.util.Date;

@Data
public class ErrorMessage {
    private String teade;
    private Date kellaaeg;
    private int staatus;
}