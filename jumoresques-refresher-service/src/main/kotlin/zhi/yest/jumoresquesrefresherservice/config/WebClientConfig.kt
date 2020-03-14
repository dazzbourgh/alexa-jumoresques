package zhi.yest.jumoresquesrefresherservice.config

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.logging.Logger

@Configuration
class WebClientConfig {
    @LoadBalanced
    @Bean
    fun webClientBuilder(): WebClient.Builder {
        return WebClient.builder()
                .filter(loggingFunction)
    }

    private val loggingFunction = ExchangeFilterFunction.ofRequestProcessor {
        LOG.info("""Sending a ${it.method()} request to ${it.url()}:
            |   ${it.body()}
        """.trimMargin())
        Mono.just(it)
    }

    companion object {
        val LOG: Logger = Logger.getLogger("RequestLogger")
    }
}