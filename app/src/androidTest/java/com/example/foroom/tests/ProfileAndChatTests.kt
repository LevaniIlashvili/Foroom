package com.example.foroom.tests

import android.content.Context
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.foroom.constants.CHAT_NAME_PREFIX
import com.example.foroom.constants.LABEL_CHANGE_LANGUAGE_ENG
import com.example.foroom.constants.LABEL_CHANGE_LANGUAGE_GEO
import com.example.foroom.constants.NEW_PASSWORD
import com.example.foroom.constants.PREFS_NAME_TRAINING
import com.example.foroom.constants.PREFS_SESSION_PREFIX
import com.example.foroom.constants.REGISTRATION_AVATAR_COUNT
import com.example.foroom.constants.REGISTRATION_PASSWORD
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.steps.HomeSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.NavigationSteps
import com.example.foroom.steps.RegistrationSteps
import com.example.shared.util.runtime.user_token.UserTokenRuntimeHolder
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
@LargeTest
class ProfileAndChatTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val registrationSteps = RegistrationSteps()
    private val navigationSteps = NavigationSteps()
    private val homeSteps = HomeSteps()
    private val profileSteps = ProfileSteps()
    private val chatSteps = ChatSteps()

    private val username = LocalDateTime.now().toString()

    @Before
    fun setUp() {
        loginSteps.goToRegisterPage()

        registrationSteps
            .validateRegistrationScreenIsDisplayed()
            .fillUsername(username)
            .fillPassword(REGISTRATION_PASSWORD)
            .fillRepeatPassword(REGISTRATION_PASSWORD)
            .register()

        navigationSteps.goToProfilePage()

        profileSteps.logout()
    }

    @After
    fun tearDown() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        val trainingPrefs = context.getSharedPreferences(PREFS_NAME_TRAINING, Context.MODE_PRIVATE)
        val sessionKeys = trainingPrefs.all.keys.filter { it.startsWith(PREFS_SESSION_PREFIX) }
        val editor = trainingPrefs.edit()
        sessionKeys.forEach { editor.remove(it) }
        editor.commit()

        val koin = GlobalContext.getOrNull()
        val userDataStore = koin?.getOrNull<ForoomUserDataStore>()
        val tokens = koin?.getOrNull<UserTokenRuntimeHolder>()

        runBlocking {
            userDataStore?.clearUserData()
            tokens?.setUserToken("")
        }
    }


    @Test
    fun changeLanguageFromGeorgianToEnglishAndBackTest() {
        loginSteps
            .validateLoginButtonIsDisplayed()
            .fillUsername(username)
            .fillPassword(REGISTRATION_PASSWORD)
            .login()

        homeSteps.validateHomeContainerVisible()

        navigationSteps.goToProfilePage()

        profileSteps
            .openChangeLangugePage()
            .chooseGeorgianLanguage()
            .validateChangeLanguageBtnText(LABEL_CHANGE_LANGUAGE_GEO)
            .openChangeLangugePage()
            .chooseEnglishLanguage()
            .validateChangeLanguageBtnText(LABEL_CHANGE_LANGUAGE_ENG)
            .openChangeLangugePage()
            .chooseGeorgianLanguage()
            .validateChangeLanguageBtnText(LABEL_CHANGE_LANGUAGE_GEO)
    }

    @Test
    fun createChatAndFindInTheListTest() {
        loginSteps
            .validateLoginButtonIsDisplayed()
            .fillUsername(username)
            .fillPassword(REGISTRATION_PASSWORD)
            .login()

        navigationSteps.openCreateChatPage()

        val chatName = CHAT_NAME_PREFIX + LocalDateTime.now().toString()
        val avatarIndex = (0 until REGISTRATION_AVATAR_COUNT).random()
        chatSteps
            .fillCreateChatName(chatName)
            .selectAvatar(avatarIndex)
            .createChat()
            .validateChatName(chatName)
            .closeChat()

        homeSteps
            .searchForChat(chatName)
            .validateChatIsDisplayed(chatName)
    }

    @Test
    fun changePasswordTest() {
        loginSteps
            .validateLoginButtonIsDisplayed()
            .fillUsername(username)
            .fillPassword(REGISTRATION_PASSWORD)
            .login()

        homeSteps.validateHomeContainerVisible()

        navigationSteps.goToProfilePage()

        profileSteps
            .openChangePasswordPage()
            .fillNewPassword(NEW_PASSWORD)
            .fillRepeatPassword(NEW_PASSWORD)
            .changePassword()

        loginSteps
            .validateLoginButtonIsDisplayed()
            .fillUsername(username)
            .fillPassword(NEW_PASSWORD)
            .login()

        homeSteps.validateHomeContainerVisible()
    }
}