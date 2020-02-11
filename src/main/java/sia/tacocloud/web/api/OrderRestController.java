package sia.tacocloud.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.Order;
import sia.tacocloud.data.OrderRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/orders", produces = "application/json")
@CrossOrigin(origins = "*")
public class OrderRestController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderRestController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PutMapping(path = "/{orderId}", consumes = "application/json")
    public ResponseEntity<Order> putOrder(@PathVariable("orderId") long id, @RequestBody Order order) {
        if (order.getId() != null && id != order.getId())
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(orderRepository.save(order), HttpStatus.OK);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public ResponseEntity<Order> patchOrder(@PathVariable("orderId") long id, @RequestBody Order patch) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (!orderOptional.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Order order = orderOptional.get();
        if (patch.getName() != null)
            order.setName(patch.getName());
        if (patch.getStreet() != null)
            order.setStreet(patch.getStreet());
        if (patch.getCity() != null)
            order.setCity(patch.getCity());
        if (patch.getState() != null)
            order.setState(patch.getState());
        if (patch.getZip() != null)
            order.setZip(patch.getZip());
        if (patch.getCcNumber() != null)
            order.setCcNumber(patch.getCcNumber());
        if (patch.getCcExpiration() != null)
            order.setCcExpiration(patch.getCcExpiration());
        if (patch.getCcCVV() != null)
            order.setCcCVV(patch.getCcCVV());
        return new ResponseEntity<>(orderRepository.save(order), HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") long id) {
        try {
            orderRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ignored) {
        }
    }
}
