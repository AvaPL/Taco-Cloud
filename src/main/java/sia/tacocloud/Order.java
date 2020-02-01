package sia.tacocloud;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Taco_Order")
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    private Date placedAt;
    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Max 50 characters")
    private String name;
    @NotBlank(message = "Street is required")
    @Size(max = 50, message = "Max 50 characters")
    private String street;
    @NotBlank(message = "City is required")
    @Size(max = 50, message = "Max 50 characters")
    private String city;
    @NotBlank(message = "State is required")
    @Size(max = 2, message = "Max 2 characters")
    private String state;
    @NotBlank(message = "Zip code is required")
    @Size(max = 10, message = "Max 10 characters")
    private String zip;
    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])/[1-9][0-9]$", message = "Must be formatted MM/YY")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;
    @OneToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    public void addDesign(Taco design) {
        tacos.add(design);
    }

    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }
}
