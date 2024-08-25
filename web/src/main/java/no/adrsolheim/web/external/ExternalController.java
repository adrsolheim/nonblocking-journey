package no.adrsolheim.web.external;

import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("external")
public class ExternalController {

    private final WebClient webClient;

    public ExternalController(HttpClient httpClient) {
        this.webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl("http://localhost:80")
                .build();
    }

    @GetMapping("/id")
    public Mono<String> getId() {
        return webClient
                .get()
                .uri("/uuid")
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping("/delay/{seconds}")
    public Mono<String> delayedResponse(@PathVariable Long seconds) {
        return webClient
                .get()
                .uri("/delay/{delay}", seconds)
                .accept(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .retrieve()
                .bodyToMono(String.class);
    }
}
