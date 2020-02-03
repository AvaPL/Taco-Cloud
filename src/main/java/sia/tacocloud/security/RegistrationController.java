package sia.tacocloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloud.data.UserRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepository userRepository,
                                  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("registrationForm")
    public RegistrationForm registrationForm() {
        return new RegistrationForm();
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(@Valid @ModelAttribute("registrationForm") RegistrationForm form, Errors errors) {
        validateUser(form, errors);
        validatePasswords(form, errors);
        if (errors.hasErrors()) return "registration";
        userRepository.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }

    private void validateUser(RegistrationForm form, Errors errors) {
        String username = form.getUsername();
        if (userRepository.existsByUsername(username))
            errors.rejectValue("username", "username.unique", "User already exists");
    }

    private void validatePasswords(RegistrationForm form, Errors errors) {
        String password = form.getPassword();
        String confirmPassword = form.getConfirmPassword();
        if (!password.equals(confirmPassword))
            errors.rejectValue("confirmPassword", "passwords.match", "Passwords must match");
    }

}
