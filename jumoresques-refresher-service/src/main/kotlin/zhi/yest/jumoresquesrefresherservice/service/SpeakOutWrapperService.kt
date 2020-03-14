package zhi.yest.jumoresquesrefresherservice.service

import org.springframework.stereotype.Service
import zhi.yest.jumoresquesrefresherservice.domain.Jumoresque

@Service
class SpeakOutWrapperService {
    fun wrap(jumoresque: Jumoresque): String =
            """
        <speak>
            <voice name="Maxim">${jumoresque.text}</voice>
        </speak>
            """.trimIndent()
}