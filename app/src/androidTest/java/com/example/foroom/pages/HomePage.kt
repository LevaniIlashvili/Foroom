package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import org.hamcrest.Matchers.allOf

class HomePage {
    val homeContainer: ViewInteraction
        get() = onView(withId(R.id.homeContainer))

    val chatSearchInput: ViewInteraction
        get() = onView(
            allOf(
                isDescendantOfA(withId(R.id.searchChatInput)),
                withId(com.example.design_system.R.id.inputEditText)
            )
        )

    fun getChatTitle(chatName: String): ViewInteraction {
        return onView(
            allOf(
                withId(com.example.design_system.R.id.chatTitleTextView),
                withText(chatName)
            )
        )
    }

    fun getOpenChatBtn(chatName: String): ViewInteraction {
        return onView(
            allOf(
                withId(R.id.sendMessageButton),
                hasSibling(
                    allOf(
                        withId(com.example.design_system.R.id.chatTitleTextView),
                        withText(chatName)
                    )
                )
            )
        )
    }
}