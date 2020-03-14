package zhi.yest.jumoresquesrefresherservice.dao

interface AudioJumoresqueDao {
    suspend fun deleteAll()
    suspend fun save(jumoresques: List<String>)
    suspend fun findAll(): List<String>
}