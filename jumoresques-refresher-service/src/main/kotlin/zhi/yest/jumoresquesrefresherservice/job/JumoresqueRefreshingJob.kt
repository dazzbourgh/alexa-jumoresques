package zhi.yest.jumoresquesrefresherservice.job

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import zhi.yest.jumoresquesrefresherservice.dao.AudioJumoresqueDao
import zhi.yest.jumoresquesrefresherservice.domain.AudioJumoresque
import zhi.yest.jumoresquesrefresherservice.service.TextToSpeechService
import zhi.yest.jumoresquesrefresherservice.service.VkJumoresqueService
import java.util.logging.Logger

@Component
class JumoresqueRefreshingJob(private val audioJumoresqueDao: AudioJumoresqueDao,
                              private val vkJumoresqueService: VkJumoresqueService,
                              private val textToSpeechService: TextToSpeechService) {
    @Scheduled(fixedRateString = "\${jumoresques.refresh.rate}")
    fun refreshJumoresques() {
        GlobalScope.launch {
            vkJumoresqueService.fetch()
                    .filter { it.text.isNotEmpty() }
                    .sortedByDescending { it.likes }
                    .take(5)
                    .map {
                        LOGGER.info("""
                            Sending request to text-to-speech-service:
                                ${it.text}
                        """.trimIndent())
                        AudioJumoresque(it, textToSpeechService.toSpeech(it.text))
                    }
                    .also {
                        audioJumoresqueDao.deleteAll()
                        audioJumoresqueDao.save(it)
                    }
        }
    }

    companion object {
        val LOGGER: Logger = Logger.getLogger(JumoresqueRefreshingJob::class.java.name)
    }
}