package zhi.yest.texttospeechservice

import com.amazonaws.regions.Regions
import com.amazonaws.services.polly.AmazonPollyAsync
import com.amazonaws.services.polly.AmazonPollyAsyncClientBuilder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class TextToSpeechServiceApplication {
    @Bean
    fun pollyClient(): AmazonPollyAsync = AmazonPollyAsyncClientBuilder
            .standard()
            .withRegion(Regions.US_WEST_2)
            .build()
}

fun main(args: Array<String>) {
    runApplication<TextToSpeechServiceApplication>(*args)
}
