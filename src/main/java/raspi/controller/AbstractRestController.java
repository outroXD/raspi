package raspi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import raspi.config.ApiConfig;

public class AbstractRestController {
    private final ApiConfig apiConfig;

    @Autowired
    public AbstractRestController(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    protected boolean isValidApiKey(String apikey) {
        return apiConfig.getApikey().equals(apikey);
    }
}
