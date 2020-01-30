package sia.tacocloud.data;

import sia.tacocloud.Ingredient;

import java.util.List;

public interface IngredientRepository {

    List<Ingredient> findAll();

    Ingredient findOne(String id);

    void save(Ingredient ingredient);

}
