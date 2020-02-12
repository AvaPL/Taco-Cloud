package sia.tacocloud.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.Taco;
import sia.tacocloud.data.TacoRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoRestController {

    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoRestController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping("/recent")
    public CollectionModel<TacoRepresentation> recentTacos() {
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Taco> recentTacos = tacoRepository.findAll(pageRequest).getContent();
        CollectionModel<TacoRepresentation> recentTacosCollection =
                new TacoRepresentationAssembler().toCollectionModel(recentTacos);
        Link link = getRecentsLink();
        recentTacosCollection.add(link);
        return recentTacosCollection;
    }

    private Link getRecentsLink() {
        CollectionModel<TacoRepresentation> recents =
                WebMvcLinkBuilder.methodOn(DesignTacoRestController.class).recentTacos();
        return WebMvcLinkBuilder.linkTo(recents).withRel("recents");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") long id) {
        Optional<Taco> tacoOptional = tacoRepository.findById(id);
        if (tacoOptional.isPresent())
            return new ResponseEntity<>(tacoOptional.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
    }

}
