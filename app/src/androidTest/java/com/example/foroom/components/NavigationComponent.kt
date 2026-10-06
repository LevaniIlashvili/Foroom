package com.example.foroom.components

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R

class NavigationComponent {
    val navProfileBtn: ViewInteraction
        get() = onView(withId(R.id.homeNavigationProfile))

    val navOpenCreateChatPageBtn: ViewInteraction
        get() = onView(withId(R.id.homeNavigationCreateChat))
}