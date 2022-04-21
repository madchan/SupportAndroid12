package com.madchan.supportandroid12.exactAlarmPermission

import com.madchan.supportandroid12.SPUtils
import com.madchan.supportandroid12.SupportAndroid12Application.Companion.appContext
import com.madchan.supportandroid12.hasDeclarePermission
import com.madchan.supportandroid12.hasRequirePermission

enum class ExactAlarmPermissionStateMachine {

    START {
        override fun nextState() = CRASH_FOR_NO_DECLARE_PERMISSION
    },
    CRASH_FOR_NO_DECLARE_PERMISSION {
        override fun nextState() =
            if (hasDeclarePermission(appContext)) DECLARED_PERMISSION else this
    },
    DECLARED_PERMISSION {
        override fun nextState() = READY_TO_FORBIT_PERMISSION
    },
    READY_TO_FORBIT_PERMISSION {
        override fun nextState() = if (!hasRequirePermission(appContext)) FORBITED_PERMISSION else this
    },
    FORBITED_PERMISSION {
        override fun nextState() = READY_TO_REQUIRE_PERMISSION
    },
    READY_TO_REQUIRE_PERMISSION {
        override fun nextState() =
            if (hasRequirePermission(appContext)) REQUIRED_PERMISSION else this
    },
    REQUIRED_PERMISSION {
        override fun nextState() = END
    },
    END {
        override fun nextState() = this
    }
    ;

    abstract fun nextState(): ExactAlarmPermissionStateMachine

    companion object {
        private const val KEY_CURRENT_STATE = "alarm_current_state"

        fun current(): ExactAlarmPermissionStateMachine {
            val value = SPUtils.getInstance(appContext).getString(KEY_CURRENT_STATE)
            return if (value.isNullOrBlank()) { START } else valueOf(value)
        }

        fun save(stateMachine: ExactAlarmPermissionStateMachine) {
            SPUtils.getInstance(appContext).put(KEY_CURRENT_STATE, stateMachine.name, true)
        }
    }
}