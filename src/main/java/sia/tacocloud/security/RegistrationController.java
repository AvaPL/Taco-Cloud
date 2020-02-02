package sia.tacocloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloud.data.UserRepository;

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

    //TODO: Add validation.
    @PostMapping
    public String processRegistration(@ModelAttribute("registrationForm") RegistrationForm form) {
        userRepository.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }

}
