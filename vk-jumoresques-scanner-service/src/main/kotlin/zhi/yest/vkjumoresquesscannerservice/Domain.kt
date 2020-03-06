package zhi.yest.vkjumoresquesscannerservice

import com.fasterxml.jackson.annotation.JsonProperty

data class VkResponse(val response: VkResponseBody)

data class VkResponseBody(val count: Int, val items: List<Post>)

data class Post(val id: Int,
                val date: Int,
                @JsonProperty("is_pinned") val isPinned: Int,
                val text: String,
                val likes: Likes)

data class Likes(val count: Int)