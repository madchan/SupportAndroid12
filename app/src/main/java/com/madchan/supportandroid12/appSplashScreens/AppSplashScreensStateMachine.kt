package com.madchan.supportandroid12.appSplashScreens

import com.madchan.supportandroid12.SPUtils
import com.madchan.supportandroid12.SupportAndroid12Application.Companion.appContext

const val SCHEME_SET_ICON_TRANSPARENT = 0
const val SCHEME_USE_SPLASH_SCREEN_API = 1

enum class AppStartupStateMachine {

    START {
        override fun nextState(scheme: Int?): AppStartupStateMachine {
            return when (scheme) {
                SCHEME_SET_ICON_TRANSPARENT -> PENDING_TO_SET_ICON_TRANSPARENT
                SCHEME_USE_SPLASH_SCREEN_API -> PENDING_TO_USE_SPLASH_SCREEN_API
                else -> this
            }
        }
    },
    PENDING_TO_SET_ICON_TRANSPARENT {
        override fun nextState(scheme: Int?) = END
    }
    ,
    PENDING_TO_USE_SPLASH_SCREEN_API {
        override fun nextState(scheme: Int?) = END
    },
    END {
        override fun nextState(scheme: Int?): AppStartupStateMachine {
            return when (scheme) {
                SCHEME_SET_ICON_TRANSPARENT -> PENDING_TO_SET_ICON_TRANSPARENT
                SCHEME_USE_SPLASH_SCREEN_API -> PENDING_TO_USE_SPLASH_SCREEN_API
                else -> this
            }
        }
    }
    ;

    abstract fun nextState(scheme: Int? = null): AppStartupStateMachine

    companion object {
        private const val KEY_CURRENT_STATE = "app_startup_current_state"

        fun current(): AppStartupStateMachine {
            val value = SPUtils.getInstance(appContext).getString(KEY_CURRENT_STATE)
            return if (value.isNullOrBlank()) { START } else valueOf(value)
        }

        fun save(stateMachine: AppStartupStateMachine) {
            SPUtils.getInstance(appContext).put(KEY_CURRENT_STATE, stateMachine.name, true)
        }

        fun clear() {
            SPUtils.getInstance(appContext).clear(true)
        }
    }
}