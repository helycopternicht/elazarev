import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 05.02.18
 */
@Component
public class MessagePrinter {

    private final MessageService service;

    @Autowired
    public MessagePrinter(MessageService service) {
        this.service = service;
    }

    public void printMessage() {
        System.out.println(this.service.getMessage());
    }
}
