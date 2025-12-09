@file:OptIn(kotlin.time.ExperimentalTime::class)

import kotlinx.datetime.*
import kotlin.time.Clock

object TimeUtil {
    val currentDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
    val currentDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    val currentInstant = Clock.System.now()

    fun getCurrentTime(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }

    fun getCurrentDate(): LocalDate {
        return Clock.System.todayIn(TimeZone.currentSystemDefault())
    }
    fun getCurrentInstant(): Instant =
        Clock.System.now()
}