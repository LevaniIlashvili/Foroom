package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.design_system.R

class ChatPage {
    val chatName: ViewInteraction
        get() = onView(withId(R.id.chatNameTextView))

    val closeBtn: ViewInteraction
        get() = onView(withId(com.alternator.foroom.R.id.closeButton))
}