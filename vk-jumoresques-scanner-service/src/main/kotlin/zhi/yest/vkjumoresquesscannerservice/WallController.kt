package zhi.yest.vkjumoresquesscannerservice

import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient

@RestController
@RequestMapping("vk/wall")
class WallController(private val webClient: WebClient,
                     private val vkConfig: VkConfig) {
    @GetMapping("{domain}")
    suspend fun fetchJumoresques(@PathVariable domain: String): VkResponse = webClient.get()
            .uri {
                it.scheme("https").host("api.vk.com")
                        .path("method/wall.get")
                        .queryParam("domain", domain)
                        .queryParam("access_token", vkConfig.accessToken)
                        .queryParam("v", vkConfig.version)
                        .queryParam("count", 50)
                        .build()
            }
            .retrieve()
            .bodyToMono(VkResponse::class.java)
            .awaitFirst()
}