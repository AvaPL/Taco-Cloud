package sia.tacocloud;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Taco {

    private Long id;
    private Date createdAt;
    @Size(min = 5, max = 50, message = "Name must be 5 to 50 characters long")
    private String name;
    @NotNull(message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

}
