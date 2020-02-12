package sia.tacocloud.web.api;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import sia.tacocloud.Ingredient;

public class IngredientRepresentationAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngredientRepresentation> {

    public IngredientRepresentationAssembler() {
        super(IngredientRestController.class, IngredientRepresentation.class);
    }

    @Override
    protected IngredientRepresentation instantiateModel(Ingredient ingredient) {
        return new IngredientRepresentation(ingredient);
    }

    @Override
    public IngredientRepresentation toModel(Ingredient ingredient) {
        return createModelWithId(ingredient.getId(), ingredient);
    }

}
