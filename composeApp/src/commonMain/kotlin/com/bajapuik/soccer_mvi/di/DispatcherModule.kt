package com.bajapuik.soccer_mvi.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val DISPATCHER_MAIN = "dispatcher-main"
const val DISPATCHER_IO = "dispatcher-io"
const val DISPATCHER_DEFAULT = "dispatcher-default"
const val DISPATCHER_UNCONFINED = "dispatcher-unconfined"

val dispatcherModule = module {
    single<CoroutineDispatcher>(named(DISPATCHER_MAIN)) {
        Dispatchers.Main
    }
    single<CoroutineDispatcher>(named(DISPATCHER_IO)) {
        Dispatchers.IO
    }
    single<CoroutineDispatcher>(named(DISPATCHER_DEFAULT)) {
        Dispatchers.Default
    }
    single<CoroutineDispatcher>(named(DISPATCHER_UNCONFINED)) {
        Dispatchers.Unconfined
    }
}