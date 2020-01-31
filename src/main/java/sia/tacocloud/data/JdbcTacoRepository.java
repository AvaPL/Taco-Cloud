package sia.tacocloud.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import sia.tacocloud.Ingredient;
import sia.tacocloud.Taco;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.*;

@Repository
public class JdbcTacoRepository implements TacoRepository {

    private final SimpleJdbcInsert tacoInserter;
    private final SimpleJdbcInsert tacoIngredientInserter;

    @Autowired
    public JdbcTacoRepository(JdbcTemplate jdbc) {
        this.tacoInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco").usingGeneratedKeyColumns("id");
        this.tacoIngredientInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco_Ingredients");
    }

    @Override
    public Taco save(Taco design) {
        saveTacoDetails(design);
        saveTacoIngredients(design);
        return design;
    }

    private void saveTacoDetails(Taco design) {
        design.setCreatedAt(new Date());
        Map<String, Object> values = new HashMap<>();
        values.put("name", design.getName());
        values.put("createdAt", design.getCreatedAt());
        long tacoId = tacoInserter.executeAndReturnKey(values).longValue();
        design.setId(tacoId);
    }

    private void saveTacoIngredients(Taco design) {
        List<Ingredient> ingredients = design.getIngredients();
        for (Ingredient ingredient : ingredients)
            insertIngredient(design, ingredient);
    }

    private void insertIngredient(Taco design, Ingredient ingredient) {
        Map<String, Object> values = new HashMap<>();
        values.put("taco", design.getId());
        values.put("ingredient", ingredient.getId());
        tacoIngredientInserter.execute(values);
    }

}
