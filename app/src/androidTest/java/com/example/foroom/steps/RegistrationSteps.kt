package com.example.foroom.steps

import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.pages.RegistrationPage

class RegistrationSteps {
    private val registrationPage = RegistrationPage()

    fun validateRegistrationScreenIsDisplayed(): RegistrationSteps {
        registrationPage.repeatPasswordInput.waitUntilVisible(5)
        return this
    }

    fun fillUsername(username: String): RegistrationSteps {
        registrationPage.usernameInput.input(username)

        return this
    }

    fun fillPassword(password: String): RegistrationSteps {
        registrationPage.passwordInput.input(password)

        return this
    }

    fun fillRepeatPassword(password: String): RegistrationSteps {
        registrationPage.repeatPasswordInput.input(password)

        return this
    }

    fun register(): RegistrationSteps {
        registrationPage.registerBtn.tap()

        return this
    }

    fun selectAvatar(index: Int): RegistrationSteps {
        registrationPage.getAvatar(index).tap(10)

        return this
    }
}