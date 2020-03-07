package zhi.yest.jumoresquesrefresherservice.domain

data class Jumoresque(val text: String, val likes: Int)
data class AudioJumoresque(val jumoresque: Jumoresque, val audio: IntArray) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AudioJumoresque

        if (jumoresque != other.jumoresque) return false

        return true
    }

    override fun hashCode(): Int {
        return jumoresque.hashCode()
    }
}