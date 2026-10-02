package com.example.foroom.steps

import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.pages.HomePage

class HomeSteps {
    private val homePage = HomePage()

    fun validateHomeContainerVisible(): HomeSteps {
        homePage.homeContainer.waitUntilVisible(15)
        return this
    }
}