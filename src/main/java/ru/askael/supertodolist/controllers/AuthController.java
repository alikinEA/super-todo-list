package ru.askael.supertodolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.askael.supertodolist.models.AuthDTO;
import ru.askael.supertodolist.models.JwtTokenDTO;
import ru.askael.supertodolist.services.DefaultUserDetailService;
import ru.askael.supertodolist.services.JwtService;

/**
 * Created by Alikin E.A. on 2020-09-28.
 */
@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DefaultUserDetailService userDetailService;

    @Autowired
    private JwtService jwtService;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public JwtTokenDTO auth(@RequestBody AuthDTO authDTO) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getLogin(), authDTO.getPassword()));
        } catch (AuthenticationException e) {
            throw new RuntimeException("Incorrect login or password");
        }

        UserDetails user = userDetailService.loadUserByUsername(authDTO.getLogin());

        return new JwtTokenDTO(jwtService.generateToken(user));
    }

}
