package com.omdbifood.common.database

sealed class FlowGenericResult {

    object Successful : FlowGenericResult()
    class Failed(val reason: String = "") : FlowGenericResult()
}
