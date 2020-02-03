package sia.tacocloud.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.Order;
import sia.tacocloud.User;
import sia.tacocloud.data.OrderRepository;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(@ModelAttribute("order") Order order, @AuthenticationPrincipal User user) {
        prepopulateOrderWithUserDetails(order, user);
        return "orderForm";
    }

    private void prepopulateOrderWithUserDetails(Order order, User user) {
        order.setName(user.getFullName());
        order.setStreet(user.getStreet());
        order.setCity(user.getCity());
        order.setState(user.getState());
        order.setZip(user.getZip());
    }

    @PostMapping
    public String processOrder(@Valid @ModelAttribute("order") Order order, Errors errors,
                               SessionStatus sessionStatus) {
        if (errors.hasErrors()) return "orderForm";
        orderRepository.save(order);
        log.info("Order submitted: " + order);
        sessionStatus.setComplete();
        return "redirect:/";
    }
}
