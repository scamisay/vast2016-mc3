package ar.edu.itba.controller;

import ar.edu.itba.domain.WSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by scamisay on 16/06/16.
 */
@Controller
public class HomeController {

    @Autowired
    private WSClient wsClient;

    @RequestMapping("/")
    public String home() {
        wsClient.startConsuming();
        return "home";
    }
}
