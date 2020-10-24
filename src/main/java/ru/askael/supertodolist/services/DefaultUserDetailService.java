package ru.askael.supertodolist.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.askael.supertodolist.models.entities.UserEntity;
import ru.askael.supertodolist.repositories.UserRepository;

import java.util.ArrayList;

/**
 * Created by Alikin E.A. on 2020-09-28.
 */
@Service
public class DefaultUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByLogin(login);
        return new User(userEntity.getLogin(), userEntity.getPasswordHash(), new ArrayList<>());
    }
}
