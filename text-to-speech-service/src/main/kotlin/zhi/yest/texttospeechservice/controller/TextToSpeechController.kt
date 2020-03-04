package zhi.yest.texttospeechservice.controller

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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
    @PostMapping
    suspend fun toSpeech(@RequestBody text: String): Flow<Int> {
        val stream = pollyService.toSpeech(text).audioStream
        return flow {
            withContext(Dispatchers.IO) {
                var value = stream.read()
                while (value != -1) {
                    emit(value)
                    value = stream.read()
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}