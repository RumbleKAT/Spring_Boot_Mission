package dev.rumblekat.mission4.controller;

import dev.rumblekat.mission4.entity.AreaEntity;
import dev.rumblekat.mission4.entity.UserEntity;
import dev.rumblekat.mission4.entity.UserRepository;
import dev.rumblekat.mission4.utils.ResidenceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ResidenceUtil residenceUtil;

    public UserController(
            @Autowired UserRepository userRepository,
            @Autowired PasswordEncoder passwordEncoder,
            @Autowired ResidenceUtil residenceUtil
            ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.residenceUtil = residenceUtil;
    }

    @GetMapping("login")
    public String login(){
        return "login-form";
    }

    @GetMapping("signup")
    public String signUp() {
        return "signup-form";
    }

    //TODO: UserController 라고 @Controller Bean을 만들고, 강의와 유사하게 로그인, 회원가입 등의 기능을 추가합시다.

    @PostMapping("signup")
    public String signUpPost(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("password_check") String passwordCheck,
            @RequestParam("is_shop_owner") Boolean isShopOwner
    ) {
        if (!password.equals(passwordCheck)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setShopOwner(isShopOwner);

        List<AreaEntity> areaLists = new ArrayList<>();
        areaLists.add(residenceUtil.getResidence().get());
        newUser.setResidence(areaLists);//TODO: AreaEntity 는 편의상 랜덤으로 지정해 줍시다.

        userRepository.save(newUser);

        return "redirect:/home";
    }
}
