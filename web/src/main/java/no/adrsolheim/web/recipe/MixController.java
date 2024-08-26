package no.adrsolheim.web.recipe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping
public class MixController {

    @GetMapping("/defer")
    public Mono<Void> defer() {
        return Mono.defer(() -> createString()).repeat(5).then();
    }
    private static Mono<String> createString() {
        System.out.println("Creating string...");
        return Mono.just(UUID.randomUUID().toString());
    }

    @GetMapping("/subscribe")
    public Mono<Void> subscribe() {
        return createString().repeat(5).then();
    }
}
