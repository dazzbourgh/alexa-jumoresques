package zhi.yest.jumoresquesrefresherservice.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "gcp")
data class GcpConfig(val projectId: String, val bucketName: String, val objectName: String)