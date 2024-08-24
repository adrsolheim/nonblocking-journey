package no.adrsolheim.web.recipe.impl;

import no.adrsolheim.web.recipe.RecipeRepository;
import no.adrsolheim.web.recipe.RecipeService;
import no.adrsolheim.web.recipe.domain.Recipe;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Mono<Recipe> get(Long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public Flux<Recipe> getByBrewfatherId(String id) {
        return null;
    }

    @Override
    public Flux<Recipe> getAll() {
        return null;
    }

    @Override
    public Mono<Long> count() {
        return null;
    }

    @Override
    public Mono<Long> delete(Long id) {
        return null;
    }
}
