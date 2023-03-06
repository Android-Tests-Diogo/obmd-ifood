package com.omdbifood.android

import org.junit.rules.RuleChain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class LocalTestRule : TestRule {

    override fun apply(base: Statement, description: Description): Statement =
        RuleChain
            .outerRule(InstantTaskDispatcherRule())
            .apply(base, description)

}
