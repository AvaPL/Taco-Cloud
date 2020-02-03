package sia.tacocloud.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import sia.tacocloud.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegistrationForm {

    @NotBlank(message = "Username is required")
    @Size(max = 50, message = "Max 50 characters")
    private String username;
    @Pattern(regexp = "^(?=\\S{8,})(?=.*[A-Z].*)(?=.*\\d.*)\\S*$", message = "Min 8 characters, 1 capital letter and 1 digit")
    private String password;
    private String confirmPassword;
    @NotBlank(message = "Full name is required")
    @Size(max = 50, message = "Max 50 characters")
    private String fullName;
    @NotBlank(message = "Street is required")
    @Size(max = 50, message = "Max 50 characters")
    private String street;
    @NotBlank(message = "City is required")
    @Size(max = 50, message = "Max 50 characters")
    private String city;
    @NotBlank(message = "State is required")
    @Size(max = 2, message = "Max 2 characters")
    private String state;
    @NotBlank(message = "Zip code is required")
    @Size(max = 10, message = "Max 10 characters")
    private String zip;
    @Pattern(regexp = "^\\d+$", message = "Invalid phone number")
    private String phoneNumber;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password), fullName, street, city, state, zip, phoneNumber);
    }

}
