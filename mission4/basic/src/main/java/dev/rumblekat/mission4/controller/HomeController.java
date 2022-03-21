package dev.rumblekat.mission4.controller;

import dev.rumblekat.mission4.infra.AuthenticationFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("home")
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final AuthenticationFacade authFacade;

    public HomeController(
            @Autowired AuthenticationFacade authFacade
    ) {
        this.authFacade = authFacade;
    }

    @GetMapping
    public String home(){
        try {
//            logger.info("connected user: {}", principal.getName()); principal을 이용해서 전달하는 방법
//            logger.info("connected user: {}", authentication.getName()); //
            logger.info("connected user: {}",
                    authFacade.getUserName());
            logger.info(authFacade.getAuthentication().getClass().toString());
        } catch (NullPointerException e) {
            logger.info("no user logged in");
        }
        return "index";
    }
}
