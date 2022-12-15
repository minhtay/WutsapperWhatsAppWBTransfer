package com.basic.common.extension

import kotlinx.coroutines.CoroutineScope

suspend fun <T> CoroutineScope.doAfterAllAwait(vararg awaitValues: T, nextAction: (Array<out T>) -> Unit) {
    nextAction.invoke(awaitValues)
}