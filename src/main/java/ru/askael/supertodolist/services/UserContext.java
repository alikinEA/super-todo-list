package ru.askael.supertodolist.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

/**
 * Created by Alikin E.A. on 2020-10-01.
 */
@Component
@RequestScope
@Getter
@Setter
public class UserContext {

    private String userName;

}
