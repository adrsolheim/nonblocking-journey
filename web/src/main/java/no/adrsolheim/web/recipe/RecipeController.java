package no.adrsolheim.web.recipe;

import lombok.extern.slf4j.Slf4j;
import no.adrsolheim.web.recipe.domain.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/{id}")
    public Mono<Recipe> recipe(@PathVariable Long id) {
        return recipeService.get(id);
    }

    @GetMapping("/brewfather/{id}")
    public Flux<Recipe> recipe(@PathVariable String id) {
        return recipeService.getByBrewfatherId(id);
    }

    @GetMapping
    public Flux<Recipe> recipes() {
        return recipeService.getAll();
    }

    @GetMapping("/count")
    public Mono<Long> count() {
        return recipeService.count();
    }

    @DeleteMapping("/{id}")
    public Mono<Long> delete(@PathVariable Long id) {
        log.info("Request for deleting recipe id {}", id);
        return recipeService.delete(id);
    }
}
