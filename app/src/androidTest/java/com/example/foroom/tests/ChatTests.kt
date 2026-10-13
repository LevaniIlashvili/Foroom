package com.example.foroom.tests

import android.content.Context
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.foroom.constants.CHAT_JOHN_WEEK
import com.example.foroom.constants.CHAT_NAME_PREFIX
import com.example.foroom.constants.CHAT_SOMETHING
import com.example.foroom.constants.MESSAGE_DRINK
import com.example.foroom.constants.MESSAGE_HELLO
import com.example.foroom.constants.MESSAGE_QUESTION
import com.example.foroom.constants.MESSAGE_REPLY
import com.example.foroom.constants.PREFS_NAME_TRAINING
import com.example.foroom.constants.PREFS_SESSION_PREFIX
import com.example.foroom.constants.REGISTRATION_PASSWORD
import com.example.foroom.constants.USER_A
import com.example.foroom.constants.USER_B
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.HomeSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.NavigationSteps
import com.example.foroom.steps.ProfileSteps
import com.example.foroom.steps.RegistrationSteps
import com.example.shared.util.runtime.user_token.UserTokenRuntimeHolder
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
@LargeTest
class ChatTests {
    @get:Rule
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val registrationSteps = RegistrationSteps()
    private val navigationSteps = NavigationSteps()
    private val homeSteps = HomeSteps()
    private val profileSteps = ProfileSteps()
    private val chatSteps = ChatSteps()

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
    fun sendAMessageInJohnWeekTest() {
        loginSteps
            .validateLoginButtonIsDisplayed()
            .fillUsername(USER_A)
            .fillPassword(REGISTRATION_PASSWORD)
            .login()

        homeSteps.validateHomeContainerVisible()

        homeSteps
            .searchForChat(CHAT_JOHN_WEEK)
            .openChat(CHAT_JOHN_WEEK)

        val message = MESSAGE_DRINK + LocalDateTime.now().toString()
        chatSteps
            .validateChatName(CHAT_JOHN_WEEK)
            .fillMessageInput(message)
            .sendMessage()
            .validateMessageIsDisplayed(USER_A, message)
            .closeChat()

        homeSteps.openChat(CHAT_JOHN_WEEK)

        chatSteps.validateMessageIsDisplayed(USER_A, message)
    }

    @Test
    fun sendAQuestionInYourOwnChatTest() {
        loginSteps
            .validateLoginButtonIsDisplayed()
            .fillUsername(USER_A)
            .fillPassword(REGISTRATION_PASSWORD)
            .login()

        homeSteps.validateHomeContainerVisible()

        homeSteps
            .searchForChat(CHAT_NAME_PREFIX)
            .openChat(CHAT_NAME_PREFIX)

        val message = MESSAGE_QUESTION + LocalDateTime.now().toString()
        chatSteps
            .validateChatName(CHAT_NAME_PREFIX)
            .fillMessageInput(message)
            .sendMessage()
            .validateMessageIsDisplayed(USER_A, message)
    }

    @Test
    fun continueConversationUsingAnotherAccountTest() {
        loginSteps
            .validateLoginButtonIsDisplayed()
            .fillUsername(USER_A)
            .fillPassword(REGISTRATION_PASSWORD)
            .login()

        homeSteps
            .validateHomeContainerVisible()
            .searchForChat(CHAT_SOMETHING)
            .openChat(CHAT_SOMETHING)

        val message = MESSAGE_HELLO + LocalDateTime.now().toString()
        chatSteps
            .validateChatName(CHAT_SOMETHING)
            .fillMessageInput(message)
            .sendMessage()
            .validateMessageIsDisplayed(USER_A, message)

        for (i in 1..25) {
            chatSteps
                .fillMessageInput(i.toString())
                .sendMessage()
        }

        chatSteps.closeChat()

        navigationSteps.goToProfilePage()

        profileSteps.logout()

        loginSteps
            .validateLoginButtonIsDisplayed()
            .fillUsername(USER_B)
            .fillPassword(REGISTRATION_PASSWORD)
            .login()

        homeSteps
            .validateHomeContainerVisible()
            .searchForChat(CHAT_SOMETHING)
            .openChat(CHAT_SOMETHING)

        chatSteps
            .scrollToOlderMessage(message)
            .validateMessageIsDisplayed(USER_A, message)

        chatSteps
            .validateChatName(CHAT_SOMETHING)
            .fillMessageInput(MESSAGE_REPLY)
            .sendMessage()
            .validateMessageIsDisplayed(USER_B, MESSAGE_REPLY)
            .closeChat()

        navigationSteps.goToProfilePage()

        profileSteps.logout()

        loginSteps
            .validateLoginButtonIsDisplayed()
            .fillUsername(USER_A)
            .fillPassword(REGISTRATION_PASSWORD)
            .login()

        homeSteps
            .validateHomeContainerVisible()
            .searchForChat(CHAT_SOMETHING)
            .openChat(CHAT_SOMETHING)

        chatSteps
            .validateChatName(CHAT_SOMETHING)
            .validateMessageIsDisplayed(USER_B, MESSAGE_REPLY)
    }
}