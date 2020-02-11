package sia.tacocloud.web.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import sia.tacocloud.Taco;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class TacoRepresentation extends RepresentationModel<TacoRepresentation> {

    private static final IngredientRepresentationAssembler ingredientAssembler =
            new IngredientRepresentationAssembler(); //TODO: Convert to bean.

    private final String name;
    private final Date createdAt;
    private final CollectionModel<IngredientRepresentation> ingredients;

    public TacoRepresentation(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients = ingredientAssembler.toCollectionModel(taco.getIngredients());
    }

}
