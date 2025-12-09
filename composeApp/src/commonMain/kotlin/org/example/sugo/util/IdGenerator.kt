import kotlin.random.Random
import kotlin.time.ExperimentalTime

object IdGenerator {

    @OptIn(ExperimentalTime::class)
    private fun timestamp(): Long =
        TimeUtil.getCurrentInstant().toEpochMilliseconds()

    private fun randomSuffix(): Int =
        Random.nextInt(1000, 10_000)

    fun generateErrandId(): String =
        "ERR_${timestamp()}_${randomSuffix()}"

    fun generateBidId(): String =
        "BID_${timestamp()}_${randomSuffix()}"
}
