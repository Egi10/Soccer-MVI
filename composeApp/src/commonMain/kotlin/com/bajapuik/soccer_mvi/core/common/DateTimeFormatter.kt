package com.bajapuik.soccer_mvi.core.common

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.format
import kotlinx.datetime.format.*

object DateTimeFormatter {
    fun formatDateTime(
        timestamp: String,
        inputFormat: DateTimeFormat<LocalDateTime>,
        outputFormat: DateTimeFormat<LocalDateTime>
    ): String {
        try {
            val timestampParse = inputFormat.parse(timestamp)
            return timestampParse.format(outputFormat)
        } catch (e: Exception) {
            throw IllegalArgumentException(e.message)
        }
    }
}

object DateTimePattern {
    /**
     * Creates a datetime format according to the ISO 8601 standard.
     *
     * Supported patterns:
     * - "yyyy-MM-dd"                      | 2024-03-15
     * - "HH:mm:ss[.SSS]"                  | 14:30:45.123
     * - "yyyy-MM-dd'T'HH:mm:ss[.SSS]"     | 2024-03-15T14:30:45.123
     * - "yyyy-MM-dd'T'HH:mm:ss[.SSS]XXX"  | 2024-03-15T14:30:45+07:00
     * - "yyyy-MM-dd'T'HH:mm:ss[.SSS]'Z'"  | 2024-03-15T14:30:45.123Z
     * - "YYYY-'W'ww-e"                    | 2024-W11-5
     * - "yyyy-DDD"                        | 2024-074
     */
    @OptIn(FormatStringsInDatetimeFormats::class)
    fun isoDateTime(
        pattern: String
    ): DateTimeFormat<LocalDateTime> {
        return LocalDateTime.Format {
            byUnicodePattern(
                pattern = pattern
            )
        }
    }

    /**
     * Format to display date in the format: "EEE, dd MMM yy"
     * Output example: "Fri, 15 Mar 24"
     */
    fun shortDayMonthYear(): DateTimeFormat<LocalDateTime> {
        return LocalDateTime.Format {
            dayOfWeek(
                names = DayOfWeekNames(
                    names = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
                )
            )
            chars(", ")
            dayOfMonth()
            chars(" ")
            monthName(
                names = MonthNames(
                    names = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
                )
            )
            chars(" ")
            yearTwoDigits(2020)
        }
    }
}