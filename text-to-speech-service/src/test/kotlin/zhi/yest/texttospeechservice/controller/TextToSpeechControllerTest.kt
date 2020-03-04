package zhi.yest.texttospeechservice.controller

import javazoom.jl.player.FactoryRegistry
import javazoom.jl.player.advanced.AdvancedPlayer
import javazoom.jl.player.advanced.PlaybackEvent
import javazoom.jl.player.advanced.PlaybackListener
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.core.io.Resource
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import java.io.ByteArrayInputStream

@ActiveProfiles("standalone")
@ExtendWith(SpringExtension::class)
@WebFluxTest(TextToSpeechController::class)
@ComponentScan("zhi.yest.texttospeechservice.config", "zhi.yest.texttospeechservice.service")
internal class TextToSpeechControllerTest {
    @Autowired
    private lateinit var webTestClient: WebTestClient
    @Value("classpath:/jumoresque.txt")
    private lateinit var resource: Resource

    @Test
    fun shouldPlaySound() {
        val res = webTestClient.post()
                .uri("/text")
                .body(BodyInserters.fromValue(resource.file.readText()))
                .exchange()
                .expectStatus().isOk
                .returnResult(String::class.java)
                .responseBody
                .blockFirst()!!
        val input = res.substring(1 until res.length - 1)
                .split(",")
                .map { it.toInt() }
                .fold(byteArrayOf()) { acc, i -> acc + i.toByte() }
                .let { ByteArrayInputStream(it) }
        val player = AdvancedPlayer(input, FactoryRegistry.systemRegistry().createAudioDevice())
        player.playBackListener = object : PlaybackListener() {
            override fun playbackStarted(evt: PlaybackEvent) {
                println("Playback started")
            }

            override fun playbackFinished(evt: PlaybackEvent) {
                println("Playback finished")
            }
        }
        player.play()
    }
}