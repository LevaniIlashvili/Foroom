package com.example.foroom.steps

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.alternator.foroom.R
import com.example.foroom.Helper.input
import com.example.foroom.Helper.isTextOnScreen
import com.example.foroom.Helper.isViewDisplayed
import com.example.foroom.Helper.swiper
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitForViewVisible
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.pages.ChatPage
import com.example.foroom.pages.CreateChatPage
import org.hamcrest.Matcher

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

    fun fillMessageInput(message: String): ChatSteps {
        chatPage.messageInput.input(message)

        return this
    }

    fun sendMessage(): ChatSteps {
        chatPage.sendMessageBtn.tap()

        return this
    }

    fun validateMessageIsDisplayed(sender: String, message: String): ChatSteps {
        chatPage.getMessageByTextAndSender(sender, message).waitUntilVisible(5)

        return this
    }

    fun scrollToOlderMessage(message: String): ChatSteps {
        var attempts = 0
        val maxAttempts = 20
        var isFound = false

        val displayMetrics = getInstrumentation().targetContext.resources.displayMetrics
        val screenHeight = displayMetrics.heightPixels

        val startY = (screenHeight * 0.3f).toInt()
        val endY = (screenHeight * 0.8f).toInt()

        while (attempts < maxAttempts) {

            if (withText(message).isViewDisplayed()) {
                isFound = true
                break
            }

            swiper(startY, endY, 500)

            Thread.sleep(250)

            attempts++
        }

        if (!isFound) {
            throw AssertionError("Message '$message' was not found after $maxAttempts swipes.")
        }

        return this
    }
}