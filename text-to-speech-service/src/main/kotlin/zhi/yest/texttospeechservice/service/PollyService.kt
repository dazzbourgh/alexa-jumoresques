package zhi.yest.texttospeechservice.service

import com.amazonaws.services.polly.AmazonPollyAsync
import com.amazonaws.services.polly.model.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class PollyService(private val pollyClient: AmazonPollyAsync) {
    private lateinit var voice: Voice

    @PostConstruct
    suspend fun init() {
        val describeVoicesRequest = DescribeVoicesRequest()
        val describeVoicesResult: DescribeVoicesResult = withContext(IO) {
            pollyClient.describeVoicesAsync(describeVoicesRequest).get()
        }
        voice = describeVoicesResult.voices[0]
    }

    suspend fun toSpeech(text: String): SynthesizeSpeechResult {
        val synthReq: SynthesizeSpeechRequest = SynthesizeSpeechRequest()
                .withText(text)
                .withVoiceId(voice.id)
                .withOutputFormat(OutputFormat.Mp3)
        return withContext(IO) {
            pollyClient.synthesizeSpeechAsync(synthReq).get()
        }
    }
}