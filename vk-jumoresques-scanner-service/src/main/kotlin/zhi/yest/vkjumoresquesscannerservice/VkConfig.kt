package zhi.yest.vkjumoresquesscannerservice

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("vk")
class VkConfig {
    lateinit var accessToken: String
    lateinit var version: String
}