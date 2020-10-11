package raspi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raspi.crypt.Aes;

import java.util.Optional;

@RestController
public class CryptRestController {
    private static final Logger logger = LoggerFactory.getLogger(CryptRestController.class);

    @Autowired
    private Aes aes;

    @RequestMapping({"/crypt/aes/encrypt", "/crypt/aes/encrypt/{text}"})
    public String encrypt(@PathVariable("text") String text) {
        Optional<String> res = aes.encrypt(text);
        return res.orElse("encrypt failed.");
    }

    @RequestMapping({"/crypt/aes/decrypt", "/crypt/aes/decrypt/{text}"})
    public String decrypt(@PathVariable("text") String text) {
        Optional<String> res = aes.decrypt(text);
        return res.orElse("decrypt failed.");
    }
}
