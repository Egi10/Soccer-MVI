package com.bajapuik.soccer_mvi.core.common

import com.bajapuik.soccer_mvi.BuildConfig

actual object Platform {
    actual val isDebug: Boolean
        get() = BuildConfig.DEBUG
}