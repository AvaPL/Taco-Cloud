package sia.tacocloud.web.api;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import sia.tacocloud.Taco;

public class TacoRepresentationAssembler extends RepresentationModelAssemblerSupport<Taco, TacoRepresentation> {

    public TacoRepresentationAssembler() {
        super(DesignTacoRestController.class, TacoRepresentation.class);
    }

    @Override
    protected TacoRepresentation instantiateModel(Taco taco) {
        return new TacoRepresentation(taco);
    }

    @Override
    public TacoRepresentation toModel(Taco taco) {
        return createModelWithId(taco.getId(), taco);
    }
}
