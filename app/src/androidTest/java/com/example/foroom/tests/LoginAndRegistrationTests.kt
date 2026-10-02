package com.example.foroom.tests

import android.content.Context
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.HomeSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.RegistrationSteps
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import com.example.foroom.constants.*
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginAndRegistrationTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val registrationSteps = RegistrationSteps()
    private val homeSteps = HomeSteps()

    @After
    fun tearDown() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        context.getSharedPreferences("foroom_training", Context.MODE_PRIVATE)
            .edit()
            .clear()
            .commit()
    }

    @Test
    fun validUsernameInvalidPasswordLoginTest() {
        loginSteps
            .validateLoginButtonIsDisplayed()
            .fillUsername(LOGIN_EXISTING_USERNAME)
            .fillPassword(LOGIN_WRONG_PASSWORD)
            .login()
            .validatePasswordErrorMessage(ERROR_INCORRECT_PASSWORD)
    }

    @Test
    fun invalidUsernameInvalidPasswordLoginTest() {
        loginSteps
            .validateLoginButtonIsDisplayed()
            .fillUsername(LOGIN_NON_EXISTING_USERNAME)
            .fillPassword(LOGIN_WRONG_PASSWORD)
            .login()
            .validateUsernameErrorMessage(ERROR_USERNAME_NOT_FOUND)
            .validatePasswordErrorMessage(ERROR_INCORRECT_PASSWORD)
    }

    @Test
    fun registrationTest() {
        val avatarIndex = (0 until REGISTRATION_AVATAR_COUNT).random()

        loginSteps
            .validateLoginButtonIsDisplayed()
            .goToRegisterPage()

        registrationSteps
            .validateRegistrationScreenIsDisplayed()
            .fillUsername(LocalDateTime.now().toString())
            .fillPassword(REGISTRATION_PASSWORD)
            .fillRepeatPassword(REGISTRATION_PASSWORD)
            .selectAvatar(avatarIndex)
            .register()

        homeSteps.validateHomeContainerVisible()
    }
}