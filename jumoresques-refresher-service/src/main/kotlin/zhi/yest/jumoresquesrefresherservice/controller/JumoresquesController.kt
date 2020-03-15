package zhi.yest.jumoresquesrefresherservice.controller

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import zhi.yest.jumoresquesrefresherservice.dao.AudioJumoresqueDao

@RestController
@RequestMapping("/jumoresques")
class JumoresquesController(@Qualifier("googleCloudStorageJumoresqueDao")
                            private val audioJumoresqueDao: AudioJumoresqueDao) {
    @GetMapping
    suspend fun getJumoresques() = audioJumoresqueDao.findAll()
            .reduce { _, audioJumoresque -> audioJumoresque }
            .audio
}