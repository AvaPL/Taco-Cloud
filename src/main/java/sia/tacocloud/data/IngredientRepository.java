package sia.tacocloud.data;

import sia.tacocloud.Ingredient;

import java.util.List;

public interface IngredientRepository {

    List<Ingredient> findAll();

    Ingredient findById(String id);

    Ingredient save(Ingredient ingredient);

}
