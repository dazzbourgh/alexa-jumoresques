package zhi.yest.texttospeechservice.controller

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import zhi.yest.texttospeechservice.service.PollyService

@RestController
@RequestMapping("text")
class TextToSpeechController(private val pollyService: PollyService) {
    @ExperimentalCoroutinesApi
    @PostMapping(produces = ["audio/mpeg"])
    fun toSpeech(@RequestBody text: String) = flow<ByteArray> {
        withContext(Dispatchers.IO) {
            pollyService.toSpeech(text).audioStream.readAllBytes()
        }
    }
}