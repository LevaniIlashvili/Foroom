package com.example.foroom.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R

class HomePage {
    val homeContainer: ViewInteraction
        get() = onView(withId(R.id.homeContainer))
}