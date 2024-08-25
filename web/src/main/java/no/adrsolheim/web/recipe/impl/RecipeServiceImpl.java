package no.adrsolheim.web.recipe.impl;

import lombok.extern.slf4j.Slf4j;
import no.adrsolheim.web.recipe.RecipeRepository;
import no.adrsolheim.web.recipe.RecipeService;
import no.adrsolheim.web.recipe.domain.Recipe;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.NoSuchElementException;

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
                .switchIfEmpty(Mono.error(new NoSuchElementException("Recipe "+id+" could not be found")))
                .delayUntil(this::logAfterFetch)
                .delayUntil(r -> publishEvent(r).onErrorResume(ex -> {
                        log.error("Failed to publish event. Ignoring error: ", ex);
                        return Mono.empty();
                }));

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
                .map(rcp -> {
                    if (rcp.getId() == 100002) {
                        throw new RuntimeException("This recipe throws random exceptions");
                    }
                    return rcp;
                })
                .delayElement(Duration.ofSeconds(2))
                .doOnNext(e -> log.info("published event {}", e))
                .then();
    }
}

