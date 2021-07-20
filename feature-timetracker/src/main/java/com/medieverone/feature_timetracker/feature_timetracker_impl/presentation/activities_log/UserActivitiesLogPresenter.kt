package com.medieverone.feature_timetracker.feature_timetracker_impl.presentation.activities_log

import com.medieverone.core_ui.BasePresenter
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.UserActivityEntity
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.usecases.UserActivitiesUseCase
import kotlinx.coroutines.*
import moxy.InjectViewState
import javax.inject.Inject

@DelicateCoroutinesApi
@InjectViewState
class UserActivitiesLogPresenter @Inject constructor(
    private val userActivitiesUseCase: UserActivitiesUseCase
): BasePresenter<UserActivitiesLogView>() {

    fun initActivitiesLog() {
        job = GlobalScope.launch(Dispatchers.Main) {
            val items = withContext(Dispatchers.IO) {
                userActivitiesUseCase.getAllUserActivities()
            }
            viewState.initActivitiesLog(items)
        }
    }

    fun deleteUserActivity(item: UserActivityEntity) {
        job = GlobalScope.launch(Dispatchers.Main) {
            val items = withContext(Dispatchers.IO) {
                userActivitiesUseCase.deleteUserActivity(item.tag)
                userActivitiesUseCase.getAllUserActivities()
            }
            viewState.initActivitiesLog(items)
        }
    }

}