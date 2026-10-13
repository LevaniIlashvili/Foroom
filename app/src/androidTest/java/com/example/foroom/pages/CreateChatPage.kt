package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.components.image_chooser.ImageChooserItemView
import com.example.foroom.Helper.nthViewOfTypeIn

class CreateChatPage {
    val chatNameInput: ViewInteraction
        get() = onView(withId(com.example.design_system.R.id.inputEditText))

    val createChatBtn: ViewInteraction
        get() = onView(withId(R.id.createChatButton))

    fun getAvatar(index: Int): ViewInteraction =
        onView(nthViewOfTypeIn(R.id.chatImageChooser, ImageChooserItemView::class.java, index))
}