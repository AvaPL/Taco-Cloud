package sia.tacocloud.web.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import sia.tacocloud.Ingredient;

@Relation(itemRelation = "ingredient", collectionRelation = "ingredients")
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
