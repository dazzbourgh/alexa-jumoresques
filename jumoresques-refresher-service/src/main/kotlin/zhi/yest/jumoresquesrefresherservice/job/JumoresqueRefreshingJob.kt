package zhi.yest.jumoresquesrefresherservice.job

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import zhi.yest.jumoresquesrefresherservice.dao.AudioJumoresqueDao
import zhi.yest.jumoresquesrefresherservice.service.SpeakOutWrapperService
import zhi.yest.jumoresquesrefresherservice.service.VkJumoresqueService

@Component
class JumoresqueRefreshingJob(private val audioJumoresqueDao: AudioJumoresqueDao,
                              private val vkJumoresqueService: VkJumoresqueService,
                              private val speakOutWrapperService: SpeakOutWrapperService) {
    @Scheduled(fixedRateString = "\${jumoresques.refresh.rate}")
    fun refreshJumoresques() {
        GlobalScope.launch {
            vkJumoresqueService.fetch()
                    .filter { it.text.isNotEmpty() }
                    .sortedByDescending { it.likes }
                    .take(5)
                    .map { speakOutWrapperService.wrap(it) }
                    .also {
                        audioJumoresqueDao.deleteAll()
                        audioJumoresqueDao.save(it)
                    }
        }
    }
}