package com.example.foroom.pages

import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.components.image_chooser.ImageChooserItemView
import com.example.foroom.Helper.nthViewOfTypeIn
import org.hamcrest.CoreMatchers.allOf

class RegistrationPage {
    val registerBtn: ViewInteraction
        get() = onView(withId(R.id.signUpButton))

    val usernameInput: ViewInteraction
        get() = onView(
            allOf(
                isDescendantOfA(withId(R.id.userNameInput)),
                isAssignableFrom(EditText::class.java)
            )
        )

    val passwordInput: ViewInteraction
        get() = onView(
            allOf(
                isDescendantOfA(withId(R.id.passwordInput)),
                isAssignableFrom(EditText::class.java)
            )
        )

    val repeatPasswordInput: ViewInteraction
        get() = onView(
            allOf(
                isDescendantOfA(withId(R.id.repeatPasswordInput)),
                isAssignableFrom(EditText::class.java)
            )
        )

    fun getAvatar(index: Int): ViewInteraction =
        onView(nthViewOfTypeIn(R.id.listView, ImageChooserItemView::class.java, index))
}