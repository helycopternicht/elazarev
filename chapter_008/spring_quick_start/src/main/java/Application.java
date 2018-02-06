import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Eugene Lazarev mailto(helycopternicht@rambler.ru)
 * @since 05.02.18
 */
@Configuration
@ComponentScan
public class Application {

    @Bean
    public MessageService mockMessageService() {
        return new MessageService() {
            @Override
            public String getMessage() {
                return "Hello Spring";
            }
        };
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);
        MessagePrinter printer = ctx.getBean(MessagePrinter.class);
        printer.printMessage();
    }
}
