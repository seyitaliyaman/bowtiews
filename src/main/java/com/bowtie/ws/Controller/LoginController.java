package com.bowtie.ws.Controller;

import com.bowtie.ws.Dto.UserAccountDto;
import com.bowtie.ws.Dto.UserDto;
import com.bowtie.ws.Entity.User;
import com.bowtie.ws.Entity.UserAccount;
import com.bowtie.ws.Security.JwtGenerator;
import com.bowtie.ws.Service.FacebookService;
import com.bowtie.ws.Service.UserAccountService;
import com.bowtie.ws.Service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("${api.context.url}")
@PreAuthorize("permitAll")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserAccountService userAccountService;
    @Autowired
    private JwtGenerator jwtGenerator;
    @Autowired
    private FacebookService facebookService;



    @PostMapping(value = "/login")
    @ApiOperation("Kullanıcı girişi için token üretip geri döndürür.")
    public ResponseEntity<String> login(@RequestBody final UserAccountDto userAccountDto){

        UserAccount user = userAccountService.getAccountByUsernameAndPassword(userAccountDto);
        Token token = new Token();
        token.setToken(jwtGenerator.generate(user));
        return new ResponseEntity<>(token.getToken(), HttpStatus.OK);
    }

    @PostMapping(value = "/register")
    @ApiOperation("Kullanıcı kaydı yapar.")
    public ResponseEntity<String> register(@RequestBody UserDto userDto){

        User user = userService.registerUser(userDto);
        Token token = new Token();
        token.setToken(jwtGenerator.generate(user.getUserAccount()));
        return new ResponseEntity<>(token.getToken(),HttpStatus.OK);
    }

    @PostMapping(value = "/facebook/auth")
    @ApiOperation("Facebook ile authentication işlemini gerçekleştirir. Kullanıcı sistemde kayıtlı değilse oluşturur.")
    public ResponseEntity<String> facebookAuth(@RequestHeader("AccessToken") String accessToken) throws ParseException {

        User user = facebookService.checkFacebookUser(accessToken);
        Token token = new Token();
        token.setToken(jwtGenerator.generate(user.getUserAccount()));
        return new ResponseEntity<>(token.getToken(),HttpStatus.OK);
    }

    @NoArgsConstructor
    class Token {
        @Getter
        @Setter
        private String token;
    }

}