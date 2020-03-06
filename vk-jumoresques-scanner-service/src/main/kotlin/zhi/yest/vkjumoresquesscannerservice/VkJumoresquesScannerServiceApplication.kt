package zhi.yest.vkjumoresquesscannerservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient

@SpringBootApplication
class VkJumoresquesScannerServiceApplication {
    @Bean
    fun webClient() = WebClient.create()
}

fun main(args: Array<String>) {
    runApplication<VkJumoresquesScannerServiceApplication>(*args)
}
