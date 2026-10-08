package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.design_system.R
import org.hamcrest.Matchers.allOf

class ChatPage {
    val chatName: ViewInteraction
        get() = onView(withId(R.id.chatNameTextView))

    val closeBtn: ViewInteraction
        get() = onView(withId(com.alternator.foroom.R.id.closeButton))

    val messageInput: ViewInteraction
        get() = onView(
            allOf(
                isDescendantOfA(withId(com.alternator.foroom.R.id.messageInput)),
                withId(R.id.inputEditText)
            )
        )

    val sendMessageBtn: ViewInteraction
        get() = onView(withId(R.id.sendMessageButton))


    fun getMessageByTextAndSender(sender:String, message: String): ViewInteraction {
        return onView(
            allOf(
                withId(com.alternator.foroom.R.id.messageView),
                hasDescendant(
                    allOf(
                        withId(R.id.userNameTextView),
                        withText(sender)
                    )
                ),
                hasDescendant(
                    allOf(
                        withId(R.id.messageTextView),
                        withText(message)
                    )
                )
            )
        )
    }
}