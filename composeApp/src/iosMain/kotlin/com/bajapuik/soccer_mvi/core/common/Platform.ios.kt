package com.bajapuik.soccer_mvi.core.common

import kotlin.experimental.ExperimentalNativeApi

actual object Platform {
    @OptIn(ExperimentalNativeApi::class)
    actual val isDebug: Boolean
        get() = kotlin.native.Platform.isDebugBinary
}