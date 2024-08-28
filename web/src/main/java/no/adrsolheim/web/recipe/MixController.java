package no.adrsolheim.web.recipe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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

    @GetMapping("/subscribe/timer")
    public Flux<String> subscribeTimer() {
        // repeats the same datetime
        return monoTime().repeat(3);
    }

    @GetMapping("/defer/timer")
    public Flux<String> deferTimer() {
        // repeats three different datetimes
        return Mono.defer(() -> monoTime()).repeat(3);
    }

    @GetMapping("/callable/timer")
    public Flux<String> callableTimer() {
        // repeats three different datetimes
        return Mono.fromCallable(() -> time()).repeat(3);
    }

    private String time() throws InterruptedException {
        Thread.sleep(1000);
        return ZonedDateTime.now().format(DateTimeFormatter.ISO_TIME)+"\n";
    }
    private Mono<String> monoTime() {
        try {
            return Mono.just(time());
        } catch (InterruptedException e) {
            return Mono.error(e);
        }
    }
}
