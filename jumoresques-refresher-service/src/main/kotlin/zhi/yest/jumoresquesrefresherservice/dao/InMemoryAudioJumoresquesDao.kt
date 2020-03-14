package zhi.yest.jumoresquesrefresherservice.dao

import org.springframework.stereotype.Repository

@Repository
class InMemoryAudioJumoresquesDao : AudioJumoresqueDao {
    private var jumoresquesList = mutableListOf<String>()

    override suspend fun deleteAll() {
        jumoresquesList = mutableListOf()
    }

    override suspend fun save(jumoresques: List<String>) {
        jumoresquesList = jumoresques.toMutableList()
    }

    override suspend fun findAll(): List<String> = jumoresquesList
}