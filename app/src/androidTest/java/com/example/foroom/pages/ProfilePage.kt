package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import org.hamcrest.Matchers.allOf

class ProfilePage {
    val openChangeLanguagePageBtn: ViewInteraction
        get() = onView(withId(R.id.changeLanguageItem))

    val openChangeLanguagePageBtnText: ViewInteraction
        get() = onView(
            allOf(
                isDescendantOfA(withId(R.id.changeLanguageItem)),
                withId(com.example.design_system.R.id.listItemTextView)
            )
        )

    val logoutBtn: ViewInteraction
        get() = onView(withId(R.id.signOutItem))

    val changePasswordBtn: ViewInteraction
        get() = onView(withId(R.id.changePasswordItem))
}