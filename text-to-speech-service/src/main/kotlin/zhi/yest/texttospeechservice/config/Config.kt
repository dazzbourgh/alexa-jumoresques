package zhi.yest.texttospeechservice.config

import com.amazonaws.ClientConfiguration
import com.amazonaws.regions.Regions
import com.amazonaws.services.polly.AmazonPollyAsync
import com.amazonaws.services.polly.AmazonPollyAsyncClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config {
    @Bean
    fun pollyClient(): AmazonPollyAsync = AmazonPollyAsyncClientBuilder
            .standard()
            .withRegion(Regions.US_WEST_2)
            .withClientConfiguration(ClientConfiguration())
            .build()
}