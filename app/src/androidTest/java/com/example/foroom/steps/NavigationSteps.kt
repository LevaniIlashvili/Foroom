package com.example.foroom.steps

import com.example.foroom.Helper.tap
import com.example.foroom.components.NavigationComponent

class NavigationSteps {
    private val navigationComponent = NavigationComponent()

    fun goToProfilePage(): NavigationSteps {
        navigationComponent.navProfileBtn.tap()

        return this
    }

    fun openCreateChatPage(): NavigationSteps {
        navigationComponent.navOpenCreateChatPageBtn.tap()

        return this
    }
}