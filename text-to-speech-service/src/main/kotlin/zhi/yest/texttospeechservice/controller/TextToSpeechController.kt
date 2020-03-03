package zhi.yest.texttospeechservice.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import zhi.yest.texttospeechservice.service.PollyService

@RestController
@RequestMapping("text")
class TextToSpeechController(private val pollyService: PollyService) {
    @PostMapping
    suspend fun toSpeech(@RequestBody text: String) = pollyService.toSpeech(text).audioStream
}