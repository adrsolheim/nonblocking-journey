package no.adrsolheim.web.recipe;

import no.adrsolheim.web.recipe.domain.Recipe;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface RecipeRepository extends ReactiveCrudRepository<Recipe, Long> {
    Mono<Recipe> findById(Long id);
}
