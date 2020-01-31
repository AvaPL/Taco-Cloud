package sia.tacocloud.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import sia.tacocloud.Ingredient;
import sia.tacocloud.Taco;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class JdbcTacoRepository implements TacoRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcTacoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Taco save(Taco design) {
        saveTacoDetails(design);
        saveTacoIngredients(design);
        return design;
    }

    private void saveTacoDetails(Taco design) {
        design.setCreatedAt(new Date());
        PreparedStatementCreator statementCreator = getStatementCreator(design);
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(statementCreator, keyHolder);
        Number tacoKey = Objects.requireNonNull(keyHolder.getKey());
        design.setId(tacoKey.longValue());
    }

    private PreparedStatementCreator getStatementCreator(Taco design) {

        PreparedStatementCreatorFactory factory =
                new PreparedStatementCreatorFactory("INSERT INTO Taco (name, createdAt) VALUES (?, ?)",
                                                    Types.VARCHAR, Types.TIMESTAMP);
        factory.setReturnGeneratedKeys(true);
        Timestamp createdAt = new Timestamp(design.getCreatedAt().getTime());
        List<? extends Serializable> values = Arrays.asList(design.getName(), createdAt);
        return factory.newPreparedStatementCreator(values);
    }

    private void saveTacoIngredients(Taco design) {
        for (Ingredient ingredient : design.getIngredients())
            jdbc.update("INSERT INTO Taco_Ingredients (taco, ingredient) VALUES (?, ?)",
                        design.getId(), ingredient.getId());
    }

}
