package sia.tacocloud.web.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import sia.tacocloud.Ingredient;
import sia.tacocloud.Taco;

import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class TacoRepresentation extends RepresentationModel<TacoRepresentation> {

    private final String name;
    private final Date createdAt;
    private final List<Ingredient> ingredients;

    public TacoRepresentation(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients = taco.getIngredients();
    }

}
