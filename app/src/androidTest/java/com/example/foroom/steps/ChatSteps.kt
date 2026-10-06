package com.example.foroom.steps

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.pages.ChatPage
import com.example.foroom.pages.CreateChatPage

class ChatSteps {
    private val createChatPage = CreateChatPage()
    private val chatPage = ChatPage()

    fun fillCreateChatName(name: String): ChatSteps {
        createChatPage.chatNameInput.input(name)

        return this
    }

    fun selectAvatar(index: Int): ChatSteps {
        createChatPage.getAvatar(index).tap(10)

        return this
    }

    fun createChat(): ChatSteps {
        createChatPage.createChatBtn.tap()

        return this
    }

    fun validateChatName(name: String): ChatSteps {
        chatPage.chatName.waitUntilVisible(5).check(matches(withText(name)))

        return this
    }

    fun closeChat(): ChatSteps {
        chatPage.closeBtn.tap()

        return this
    }
}