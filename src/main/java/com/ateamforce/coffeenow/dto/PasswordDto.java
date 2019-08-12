package com.ateamforce.coffeenow.dto;

import com.ateamforce.coffeenow.annotation.PasswordMatches;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.ateamforce.coffeenow.util.PasswordService;

/**
 *
 * @author Sakel
 */
@PasswordMatches
public class PasswordDto implements PasswordService {

    @NotNull(message = "{password.notempty.restriction.message}")
    @NotEmpty(message = "{password.notempty.restriction.message}")
    @NotBlank(message = "{password.notempty.restriction.message}")
    @Size(min = 8, max = 255, message = "{password.size.restriction.message}")
    @Pattern( regexp = "(?=.*[0-9]).+", message = "{password.number.restriction.message}" )
    @Pattern( regexp = "(?=.*[a-z]).+", message = "{password.small.restriction.message}" )
    @Pattern( regexp = "(?=.*[A-Z]).+", message = "{password.capital.restriction.message}" )
    @Pattern( regexp = "(?=.*[!@#$%^&*+=?-_()/\"\\.,<>~`;:]).+", message = "{password.special.restriction.message}" )
    @Pattern( regexp = "(?=\\S+$).+", message = "{password.nospaces.restriction.message}" )
    private String password;
    
    @NotNull(message = "{passwordRepeat.notempty.restriction.message}")
    @NotEmpty(message = "{passwordRepeat.notempty.restriction.message}")
    @NotBlank(message = "{passwordRepeat.notempty.restriction.message}")
    private String passwordRepeat;

    public PasswordDto() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }
    
}
