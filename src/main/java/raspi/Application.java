package raspi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raspi.wol.WakeOnLan;
import raspi.wol.WakeOnLanConfig;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
        return applicationBuilder.sources(Application.class);
    }
}

@RestController
class GreetingController {
    @RequestMapping("/hello/{name}")
    String hello(@PathVariable String name) {
        return "Hello, " + name + "!";
    }
}

@RestController
class WakeOnLanController {
    @Autowired
    private WakeOnLanConfig wakeOnLanConfig;
    @Autowired
    private WakeOnLan wakeOnLan;

    @RequestMapping("/wol")
    String wol() {
        Boolean flag = wakeOnLan.sendMagickPacket(
                wakeOnLanConfig.getMacAddress(),
                wakeOnLanConfig.getIpAddress(),
                wakeOnLanConfig.getPort());

        if (flag)
            return "Success.";
        else
            return "Failed.";
    }
}