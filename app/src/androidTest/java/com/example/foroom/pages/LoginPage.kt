package com.example.foroom.pages

import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.CoreMatchers.allOf

class LoginPage {
    val loginBtn: ViewInteraction
        get() = onView(withId(R.id.logInButton))

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

    val passwordErrorMsg: ViewInteraction
        get() = onView(
            allOf(
                isDescendantOfA(withId(R.id.passwordInput)),
                withId(com.example.design_system.R.id.descriptionTextView)
            )
        )

    val usernameErrorMsg: ViewInteraction
        get() = onView(
            allOf(
                isDescendantOfA(withId(R.id.userNameInput)),
                withId(com.example.design_system.R.id.descriptionTextView)
            )
        )

    val registerBtn: ViewInteraction
        get() = onView(withId(R.id.signUpButton))
}