package zhi.yest.vkjumoresquesscannerservice

import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@ExtendWith(SpringExtension::class)
@WebFluxTest(WallController::class)
@ComponentScan("zhi.yest")
@Disabled
class VkJumoresquesScannerServiceApplicationTests {
    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun shouldReturnPostsFromAWall() {
        runBlocking {
            webTestClient.get()
                    .uri("/vk/wall/apiclub")
                    .exchange()
                    .returnResult(VkResponse::class.java)
                    .responseBody
                    .awaitFirst()
                    .response
                    .count
                    .also {
                        assert(it > 0)
                    }
        }
    }
}
