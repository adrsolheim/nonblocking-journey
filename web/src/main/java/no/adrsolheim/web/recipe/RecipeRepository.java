package no.adrsolheim.web.recipe;

import no.adrsolheim.web.recipe.domain.Recipe;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecipeRepository extends ReactiveCrudRepository<Recipe, Long> {
    Mono<Recipe> findById(Long id);
    Flux<Recipe> findByBrewfatherId(String id);
    @Query("select count(*) from recipe")
    Mono<Long> count();
    Mono<Void> deleteById(Long id);
}
