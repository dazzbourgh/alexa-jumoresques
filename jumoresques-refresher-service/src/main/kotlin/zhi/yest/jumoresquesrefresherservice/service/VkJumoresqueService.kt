package zhi.yest.jumoresquesrefresherservice.service

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import zhi.yest.jumoresquesrefresherservice.domain.Jumoresque

@Service
class VkJumoresqueService(private val webClientBuilder: WebClient.Builder) {
    suspend fun fetch(): List<Jumoresque> = webClientBuilder.build().get()
            .uri("http://vk-jumoresques-scanner-service/wall/jumoreski")
            .retrieve()
            .awaitBody()
}