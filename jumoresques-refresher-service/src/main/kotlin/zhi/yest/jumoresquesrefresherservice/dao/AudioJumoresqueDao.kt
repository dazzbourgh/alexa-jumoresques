package zhi.yest.jumoresquesrefresherservice.dao

import zhi.yest.jumoresquesrefresherservice.domain.AudioJumoresque

interface AudioJumoresqueDao {
    suspend fun deleteAll()
    suspend fun save(jumoresques: List<AudioJumoresque>)
    suspend fun findAll(): List<AudioJumoresque>
}