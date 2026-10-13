# Android 4 Test Execution Matrix

This document records the pass/fail results of independent test scenarios across the device/AVD configuration matrix.

## Environment Overview

| Device Model / AVD Name | Type (Emulator / Physical) | Android / API Version | Screen Resolution | Scenario 1: John Week Msg (`sendAMessageInJohnWeekTest`) | Scenario 2: Own Chat Q (`sendAQuestionInYourOwnChatTest`) | Scenario 3: Multi-Account (`continueConversationUsingAnotherAccountTest`) | Full Class Run Result |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Samsung Galaxy A05 (SM-A055F)** | Physical | Android 14 (API 34) | 720 x 1600 | **PASS** | **PASS** | **PASS** | **PASS** |
| **Xiaomi Redmi 13C 5G (23124RN87C)** | Physical | Android 14 (API 34) | 720 x 1600 | **PASS** | **PASS** | **PASS** | **PASS** |
| **Pixel 6 Pro API 34 (sdk_gphone64_x86_64)** | Emulator | Android 14 (API 34) | 1080 x 2400 | **PASS** | **PASS** | **PASS** | **PASS** |

## Execution Notes
* **Scenario 1 (`sendAMessageInJohnWeekTest`):** Validates message sending, closing, and persistence upon re-opening.
* **Scenario 2 (`sendAQuestionInYourOwnChatTest`):** Validates prefix-based chat lookup and dynamic question dispatching.
* **Scenario 3 (`continueConversationUsingAnotherAccountTest`):** Validates multi-account session handoff, long-scroll pagination, reply validation, and token/state cleanup via `tearDown()`.