package no.adrsolheim.web.recipe;

import no.adrsolheim.web.recipe.domain.Recipe;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecipeService {
    Mono<Recipe> get(Long id);
    Flux<Recipe> getByBrewfatherId(String id);
    Flux<Recipe> getAll();
    Mono<Long> count();
    Mono<Long> delete(Long id);
}
