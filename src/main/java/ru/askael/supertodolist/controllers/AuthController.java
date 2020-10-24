package ru.askael.supertodolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.askael.supertodolist.models.DTOs.AuthDTO;
import ru.askael.supertodolist.models.DTOs.JwtTokenDTO;
import ru.askael.supertodolist.models.DTOs.RegistrationDTO;
import ru.askael.supertodolist.services.DefaultUserDetailService;
import ru.askael.supertodolist.services.JwtService;
import ru.askael.supertodolist.services.UserService;

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

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<JwtTokenDTO> auth(@RequestBody AuthDTO authDTO) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authDTO.getLogin(), authDTO.getPassword()));
        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        UserDetails user = userDetailService.loadUserByUsername(authDTO.getLogin());

        return ResponseEntity.ok(new JwtTokenDTO(jwtService.generateToken(user)));
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public void registration(@RequestBody RegistrationDTO registrationDTO) {
        userService.registration(registrationDTO);
    }

}
