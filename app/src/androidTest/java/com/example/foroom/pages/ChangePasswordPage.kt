package com.example.foroom.pages

import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anyOf

class ChangePasswordPage {
    val newPasswordInput: ViewInteraction
        get() = onView(
            CoreMatchers.allOf(
                isDescendantOfA(withId(R.id.passwordInput)),
                withId(com.example.design_system.R.id.inputEditText)
            )
        )

    val repeatPasswordInput: ViewInteraction
        get() = onView(
            CoreMatchers.allOf(
                isDescendantOfA(withId(R.id.repeatPasswordInput)),
                withId(com.example.design_system.R.id.inputEditText)
            )
        )

    val submitBtn: ViewInteraction
        get() = onView(
            allOf(
                withId(com.example.design_system.R.id.actionButton),
                anyOf(
                    withText("დადასტურება"),
                    withText("Confirm")
                )
            )
        )
}