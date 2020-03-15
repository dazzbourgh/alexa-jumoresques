package zhi.yest.jumoresquesrefresherservice.dao

import com.google.cloud.storage.BlobId
import com.google.cloud.storage.BlobInfo
import com.google.cloud.storage.Storage
import com.google.cloud.storage.StorageOptions
import org.springframework.stereotype.Service
import zhi.yest.jumoresquesrefresherservice.config.GcpConfig
import zhi.yest.jumoresquesrefresherservice.domain.AudioJumoresque

@Service
class GoogleCloudStorageJumoresqueDao(gcpConfig: GcpConfig) : AudioJumoresqueDao {
    private val storage: Storage = StorageOptions.newBuilder()
            .setProjectId(gcpConfig.projectId)
            .build()
            .service

    private val blobId: BlobId = BlobId.of(gcpConfig.bucketName, gcpConfig.objectName)

    private val blobInfo: BlobInfo = BlobInfo.newBuilder(blobId).build()

    override suspend fun deleteAll() {
        storage.delete(blobId)
    }

    override suspend fun save(jumoresques: List<AudioJumoresque>) {
        storage.create(blobInfo, jumoresques.reduce { _, audioJumoresque -> audioJumoresque }.audio)
    }

    override suspend fun findAll(): List<AudioJumoresque> {
        throw UnsupportedOperationException("Not supported for Google Cloud Storage.")
    }
}