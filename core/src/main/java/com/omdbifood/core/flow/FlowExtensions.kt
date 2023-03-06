package com.omdbifood.core.flow

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.experimental.ExperimentalTypeInference

@OptIn(ExperimentalTypeInference::class)
fun <T> flowIO(@BuilderInference block: suspend FlowCollector<T>.() -> Unit): Flow<T> =
    flow(block).flowOn(
        Dispatchers.IO
    )
