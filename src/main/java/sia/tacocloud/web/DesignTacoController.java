package sia.tacocloud.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloud.Ingredient;
import sia.tacocloud.Ingredient.Type;
import sia.tacocloud.Taco;
import sia.tacocloud.data.IngredientRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    private final IngredientRepository repository;

    @Autowired
    public DesignTacoController(IngredientRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        model.addAttribute("design", new Taco());
        return "design";
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = repository.findAll();
        Type[] types = Ingredient.Type.values();
        for (Type type : types)
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream().filter(ingredient -> ingredient.getType().equals(type)).collect(
                Collectors.toList());
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Taco design, Errors errors) {
        if (errors.hasErrors()) return "design";
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }
}
