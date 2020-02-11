package sia.tacocloud.web.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import sia.tacocloud.Ingredient;

@Data
@EqualsAndHashCode(callSuper = true)
public class IngredientRepresentation extends RepresentationModel<IngredientRepresentation> {

    private final String name;
    private final Ingredient.Type type;

    public IngredientRepresentation(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }

}
