package com.medieverone.feature_timetracker.feature_timetracker_impl.presentation.add_time_to_log

import com.medieverone.core_ui.BasePresenter
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.TimeLogEntity
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.UserActivityEntity
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.usecases.TimeLogUseCase
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.usecases.UserActivitiesUseCase
import kotlinx.coroutines.*
import moxy.InjectViewState
import javax.inject.Inject

@DelicateCoroutinesApi
@InjectViewState
class AddTimePresenter @Inject constructor(
    private val userActivitiesUseCase: UserActivitiesUseCase,
    private val timeLogUseCase: TimeLogUseCase
) : BasePresenter<AddTimeView>() {

    @Volatile
    var time = 0L

    fun initActivitiesSpinner() {
        job = GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                userActivitiesUseCase.getAllUserActivities()
            }
            viewState.initActivitiesSpinner(result)
        }
    }

    fun initTrackedTime(timeInMillis: Long) {
        time = timeInMillis
        viewState.initTime(timeInMillis)
    }

    fun onAddActivityClicked() {
        viewState.navigateToCreateUserActivity()
    }

    fun onAddTimeClicked(timeLogEntity: TimeLogEntity) {
        if (timeLogEntity.activity == null) {
            viewState.showToast("Вы не можете добавить время, не привязав его к актинвости")
            return
        }
        job = GlobalScope.launch(Dispatchers.IO) {
            timeLogUseCase.saveTimeLog(timeLogEntity)
            withContext(Dispatchers.Main) {
                viewState.navigateToPreviousScreen()
                viewState.showToast("Время успешно записано в логи")
            }
        }
    }

    fun onActivityItemLongClicked(item: UserActivityEntity) {
        job = GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                userActivitiesUseCase.deleteUserActivity(item.tag)
            }
            viewState.refreshUserActivityAdapter()
        }
    }
}