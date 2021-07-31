package com.medieverone.feature_timetracker.feature_timetracker_impl.presentation.add_time_to_log

import com.medieverone.core_ui.BasePresenter
import com.medieverone.feature_activities.featue_activities_api.ActivitiesApi
import com.medieverone.feature_activities.featue_activities_api.FeatureActivitiesApi
import com.medieverone.feature_activities.feature_activities_impl.domain.entities.ActivityEntity
import com.medieverone.feature_activities.feature_activities_impl.domain.entities.TimeLogEntity
import kotlinx.coroutines.*
import moxy.InjectViewState
import javax.inject.Inject

@DelicateCoroutinesApi
@InjectViewState
class AddTimePresenter @Inject constructor(
    private val featureActivitiesApi: ActivitiesApi
) : BasePresenter<AddTimeView>() {

    @Volatile
    var time = 0L
    @Volatile
    var currentActivity: ActivityEntity? = null

    fun initActivitiesSpinner() {
        job = GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                featureActivitiesApi.getActivities()
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
        if (currentActivity == null) {
            viewState.showToast("Установите активность!")
            return
        }
        job = GlobalScope.launch(Dispatchers.IO) {
            featureActivitiesApi.addTimeToActivity(currentActivity!!, timeLogEntity)
            withContext(Dispatchers.Main) {
                viewState.navigateToPreviousScreen()
                viewState.showToast("Время успешно записано в логи")
            }
        }
    }

    fun onActivityClicked(activityEntity: ActivityEntity) {
        currentActivity = activityEntity
    }

//    fun onActivityItemLongClicked(item: ActivityEntity) {
//        job = GlobalScope.launch(Dispatchers.Main) {
//            withContext(Dispatchers.IO) {
//                userActivitiesUseCase.deleteUserActivity(item.tag)
//            }
//            viewState.refreshUserActivityAdapter()
//        }
//    }
}