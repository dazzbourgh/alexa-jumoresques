package zhi.yest.jumoresquesrefresherservice.dao

import org.springframework.stereotype.Repository
import zhi.yest.jumoresquesrefresherservice.domain.AudioJumoresque

@Repository
class InMemoryAudioJumoresquesDao : AudioJumoresqueDao {
    private val jumoresquesList = mutableListOf<AudioJumoresque>()

    override suspend fun deleteAll() {
        jumoresquesList.removeAll { true }
    }

    override suspend fun save(jumoresques: List<AudioJumoresque>) {
        jumoresquesList.addAll(jumoresques)
    }

    override suspend fun findAll(): List<AudioJumoresque> = jumoresquesList
}