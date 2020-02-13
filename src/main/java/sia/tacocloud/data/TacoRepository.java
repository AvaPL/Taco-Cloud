package sia.tacocloud.data;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import sia.tacocloud.Taco;

@RepositoryRestResource(collectionResourceRel = "tacos", path = "tacos")
public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

    @RestResource(path = "recent", rel = "recent")
    public Iterable<Taco> findTop12ByOrderByCreatedAtDesc();

}
