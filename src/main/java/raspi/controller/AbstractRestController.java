package raspi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import raspi.config.ApiConfig;
import java.util.Optional;

public class AbstractRestController {
    private final ApiConfig apiConfig;

    @Autowired
    public AbstractRestController(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    protected boolean isValidApiKey(Optional<String> apikey) {
        return true;
    }
}
