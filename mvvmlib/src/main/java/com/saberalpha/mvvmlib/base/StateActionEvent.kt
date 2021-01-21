package com.saberalpha.mvvmlib.base

import android.app.Activity

//定义状态(密封类扩展性更好)
sealed class StateActionEvent

object LoadState : StateActionEvent()

object DismissState : StateActionEvent()

object SuccessState : StateActionEvent()

object FinishEventState : StateActionEvent()

object OnBackPressedEventState : StateActionEvent()

class ErrorState(val message: String?) : StateActionEvent()

class ToastEventState(val message: String?) : StateActionEvent()

class StartActivityState(val activity:Class<out Activity>,val params: Array<out Pair<String, Any?>>) : StateActionEvent()

