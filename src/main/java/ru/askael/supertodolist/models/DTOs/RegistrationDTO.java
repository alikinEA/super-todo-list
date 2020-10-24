package ru.askael.supertodolist.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Alikin E.A. on 2020-10-18.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO extends AuthDTO {

    private String email;
}
