package com.example.foroom.steps

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.pages.ChangeLanguagePage
import com.example.foroom.pages.ChangePasswordPage
import com.example.foroom.pages.ProfilePage

class ProfileSteps {
    private val profilePage = ProfilePage()
    private val changeLanguagePage = ChangeLanguagePage()
    private val changePasswordPage = ChangePasswordPage()

    fun openChangeLangugePage(): ProfileSteps {
        profilePage.openChangeLanguagePageBtn.tap()

        return this
    }

    fun chooseGeorgianLanguage(): ProfileSteps {
        changeLanguagePage.georgianBtn.tap()

        return this
    }

    fun chooseEnglishLanguage(): ProfileSteps {
        changeLanguagePage.englishBtn.tap()

        return this
    }

    fun validateChangeLanguageBtnText(text: String): ProfileSteps {
        profilePage.openChangeLanguagePageBtnText.check(matches(withText(text)))

        return this
    }

    fun logout(): ProfileSteps {
        profilePage.logoutBtn.tap()

        return this
    }

    fun openChangePasswordPage(): ProfileSteps {
        profilePage.changePasswordBtn.tap()

        return this;
    }

    fun fillNewPassword(newPassword: String): ProfileSteps {
        changePasswordPage.newPasswordInput.input(newPassword)

        return this
    }

    fun fillRepeatPassword(repeatPassword: String): ProfileSteps {
        changePasswordPage.repeatPasswordInput.input(repeatPassword)

        return this
    }

    fun changePassword(): ProfileSteps {
        changePasswordPage.submitBtn.tap()

        return this
    }
}