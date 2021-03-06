package zhi.yest.jumoresquesrefresherservice.job

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import zhi.yest.jumoresquesrefresherservice.dao.AudioJumoresqueDao
import zhi.yest.jumoresquesrefresherservice.domain.AudioJumoresque
import zhi.yest.jumoresquesrefresherservice.service.TextToSpeechService
import zhi.yest.jumoresquesrefresherservice.service.VkJumoresqueService

@Component
class JumoresqueRefreshingJob(@Qualifier("googleCloudStorageJumoresqueDao")
                              private val audioJumoresqueDao: AudioJumoresqueDao,
                              private val vkJumoresqueService: VkJumoresqueService,
                              private val textToSpeechService: TextToSpeechService) {
    @Scheduled(fixedRateString = "\${jumoresques.refresh.rate}")
    fun refreshJumoresques() {
        GlobalScope.launch {
            vkJumoresqueService.fetch()
                    .filter { it.text.isNotEmpty() }
                    .sortedByDescending { it.likes }
                    .take(5)
                    .joinToString(separator = "\n", prefix = "Прослушайте свежие юморески.\n", postfix = "Очень смешно.")
                    .let { AudioJumoresque(it, textToSpeechService.toSpeech(it)) }
                    .also {
                        audioJumoresqueDao.deleteAll()
                        audioJumoresqueDao.save(listOf(it))
                    }
        }
    }
}