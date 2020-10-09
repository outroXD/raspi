package raspi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raspi.config.ApiConfig;

@RestController
public class IndexController extends AbstractRestController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    public IndexController(ApiConfig apiConfig) {
        super(apiConfig);
    }

    @RequestMapping("/hello/{apiKey}")
    public String getIndex(@PathVariable(name = "apiKey") String apiKey) {
        if (!isValidApiKey(apiKey)) {
            logger.error("apiKey error.");
            return "Failed.";
        }

        return "hello";
    }
}