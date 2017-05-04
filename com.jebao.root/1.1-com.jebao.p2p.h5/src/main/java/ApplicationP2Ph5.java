import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by wenyq on 2017/2/17.
 */
@SpringBootApplication
@ComponentScan("com.jebao.p2p.h5")
public class ApplicationP2Ph5 {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationP2Ph5.class, args);
        
    }
}

