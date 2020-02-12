package sia.tacocloud.web.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
@CrossOrigin("*")
public class IngredientRestController {
    // TODO: Implement.
}
