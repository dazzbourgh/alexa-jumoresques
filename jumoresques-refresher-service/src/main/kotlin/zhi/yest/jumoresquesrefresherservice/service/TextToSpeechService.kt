package zhi.yest.jumoresquesrefresherservice.service

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Service
class TextToSpeechService(private val webClientBuilder: WebClient.Builder) {
    suspend fun toSpeech(text: String): ByteArray =
            webClientBuilder.build().post()
                    .uri("http://text-to-speech-service/text")
                    .bodyValue(text)
                    .retrieve()
                    .awaitBody()
}