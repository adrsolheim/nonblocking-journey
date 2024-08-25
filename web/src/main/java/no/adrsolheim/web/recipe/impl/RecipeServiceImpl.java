package no.adrsolheim.web.recipe.impl;

import lombok.extern.slf4j.Slf4j;
import no.adrsolheim.web.recipe.RecipeRepository;
import no.adrsolheim.web.recipe.RecipeService;
import no.adrsolheim.web.recipe.domain.Recipe;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Mono<Recipe> get(Long id) {
        return recipeRepository.findById(id)
                .delayUntil(this::logAfterFetch)
                .delayUntil(this::publishEvent);

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

    private Mono<Void> logAfterFetch(Recipe r) {
        log.info("Fetched entity from database {}", r);
        return Mono.empty();
    }

    private Mono<Void> publishEvent(Recipe r) {
        return Mono.just(r)
                .delayElement(Duration.ofSeconds(2))
                .doOnNext(e -> log.info("published event {}", e))
                .then();
    }
}

