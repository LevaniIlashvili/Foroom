package com.example.foroom.steps

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.pages.LoginPage

class LoginSteps {
    private val loginPage = LoginPage()

    fun validateLoginButtonIsDisplayed(): LoginSteps {
        loginPage.loginBtn.waitUntilVisible(5)

        return this
    }

    fun fillUsername(username: String): LoginSteps {
        loginPage.usernameInput.input(username)

        return this
    }

    fun fillPassword(password: String): LoginSteps {
        loginPage.passwordInput.input(password)

        return this
    }

    fun login(): LoginSteps {
        loginPage.loginBtn.tap()

        return this
    }

    fun validatePasswordErrorMessage(expectedMessage: String): LoginSteps {
        loginPage.passwordErrorMsg
            .waitUntilVisible(10)
            .check(matches(withText(expectedMessage)))

        return this
    }

    fun validateUsernameErrorMessage(expectedMessage: String): LoginSteps {
        loginPage.usernameErrorMsg
            .waitUntilVisible(10)
            .check(matches(withText(expectedMessage)))

        return this
    }

    fun goToRegisterPage(): LoginSteps {
        loginPage.registerBtn.tap()

        return this
    }
}