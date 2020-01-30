package sia.tacocloud.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sia.tacocloud.Ingredient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Ingredient> findAll() {
        return jdbc.query("SELECT id, name, type FROM Ingredient", this::mapRowToIngredient);
    }

    private Ingredient mapRowToIngredient(ResultSet resultSet, int rowNumber) throws SQLException {
        return new Ingredient(resultSet.getString("id"),
                              resultSet.getString("name"),
                              Ingredient.Type.valueOf(resultSet.getString("type")));
    }

    @Override
    public Ingredient findOne(String id) {
        return jdbc.queryForObject("SELECT id, name, type FROM Ingredient WHERE id=?", this::mapRowToIngredient, id);
    }

    @Override
    public void save(Ingredient ingredient) {
        jdbc.update("INSERT INTO Ingredient (id, name, type) VALUES (?, ?, ?)",
                    ingredient.getId(), ingredient.getName(), ingredient.getType());
    }
}
