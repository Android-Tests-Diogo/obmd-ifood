package com.omdbifood.core.results.data.remote.exceptions

import com.omdbifood.common.exceptions.GenericExceptions

sealed class ResultsExceptions : GenericExceptions() {
    object MinCharsAccepted : ResultsExceptions()
    object NoResultsFound : ResultsExceptions()
}
