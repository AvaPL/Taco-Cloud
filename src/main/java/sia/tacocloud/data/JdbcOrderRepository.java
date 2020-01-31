package sia.tacocloud.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import sia.tacocloud.Order;
import sia.tacocloud.Taco;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private final SimpleJdbcInsert orderInserter;
    private final SimpleJdbcInsert orderTacoInserter;
    private final ObjectMapper objectMapper;

    public JdbcOrderRepository(JdbcTemplate jdbc) {
        this.orderInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco_Order").usingGeneratedKeyColumns("id");
        this.orderTacoInserter = new SimpleJdbcInsert(jdbc).withTableName("Taco_Order_Tacos");
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order order) {
        saveOrderDetails(order);
        saveOrderTacos(order);
        return order;
    }

    private void saveOrderDetails(Order order) {
        order.setPlacedAt(new Date());
        @SuppressWarnings("unchecked") Map<String, Object> values = objectMapper.convertValue(order, Map.class);
        values.put("placedAt", order.getPlacedAt()); // Overwrite because ObjectMapper maps Date to long.
        long orderId = orderInserter.executeAndReturnKey(values).longValue();
        order.setId(orderId);
    }

    private void saveOrderTacos(Order order) {
        List<Taco> tacos = order.getTacos();
        for (Taco taco : tacos)
            insertTaco(order, taco);
    }

    private void insertTaco(Order order, Taco taco) {
        Map<String, Object> values = new HashMap<>();
        values.put("tacoOrder", order.getId());
        values.put("taco", taco.getId());
        orderTacoInserter.execute(values);
    }

}
