package ru.askael.supertodolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.askael.supertodolist.models.entities.UserEntity;

/**
 * Created by Alikin E.A. on 2020-10-18.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByLogin(String email);

}
