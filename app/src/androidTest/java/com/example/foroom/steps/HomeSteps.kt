package com.example.foroom.steps

import com.example.foroom.Helper.input
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.pages.CreateChatPage
import com.example.foroom.pages.HomePage

class HomeSteps {
    private val homePage = HomePage()
    private val createChatPage = CreateChatPage()

    fun validateHomeContainerVisible(): HomeSteps {
        homePage.homeContainer.waitUntilVisible(15)
        return this
    }

    fun searchForChat(chatName: String): HomeSteps {
        homePage.chatSearchInput.input(chatName)

        return this
    }

    fun validateChatIsDisplayed(chatName: String): HomeSteps {
        homePage.getChatTitle(chatName).waitUntilVisible(5)

        return this
    }
}