package ru.askael.supertodolist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.askael.supertodolist.models.DTOs.RegistrationDTO;
import ru.askael.supertodolist.models.entities.UserEntity;
import ru.askael.supertodolist.repositories.UserRepository;

/**
 * Created by Alikin E.A. on 2020-10-24.
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void registration(RegistrationDTO registrationDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(registrationDTO.getEmail());
        userEntity.setLogin(registrationDTO.getLogin());
        userEntity.setPasswordHash(passwordEncoder.encode(registrationDTO.getPassword()));

        userRepository.save(userEntity);
    }
}
