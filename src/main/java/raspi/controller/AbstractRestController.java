package raspi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import raspi.config.ApiConfig;

public class AbstractRestController {
    @Autowired
    ApiConfig apiConfig;

    protected boolean isValidApiKey(final String apikey) {
        return apiConfig.getApikey().equals(apikey);
    }
}