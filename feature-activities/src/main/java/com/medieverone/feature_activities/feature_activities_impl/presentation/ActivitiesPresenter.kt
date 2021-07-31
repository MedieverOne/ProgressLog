package com.medieverone.feature_activities.feature_activities_impl.presentation

import com.medieverone.core_ui.BasePresenter
import com.medieverone.feature_activities.feature_activities_impl.domain.entities.ActivityEntity
import com.medieverone.feature_activities.feature_activities_impl.domain.entities.TimeLogEntity
import com.medieverone.feature_activities.feature_activities_impl.domain.usecases.ActivitiesUseCase
import kotlinx.coroutines.*
import moxy.InjectViewState
import javax.inject.Inject

@DelicateCoroutinesApi
@InjectViewState
class ActivitiesPresenter @Inject constructor(
    private val activitiesUseCase: ActivitiesUseCase
): BasePresenter<ActivitiesView>() {

    @Volatile
    private var currentActivity: ActivityEntity? = null

    override fun attachView(view: ActivitiesView?) {
        super.attachView(view)
        initScreen()
    }

    private fun initScreen() {
        job = GlobalScope.launch(Dispatchers.Main) {
            val activities = arrayListOf<ActivityEntity>()
            val timeLog = arrayListOf<TimeLogEntity>()
            withContext(Dispatchers.IO) {
                activities.addAll(activitiesUseCase.getActivities())
                if (currentActivity == null && activities.isNotEmpty()) {
                    currentActivity = activities[0]
                }
            }

            if (currentActivity == null) {
                viewState.setErrorVisibility(true)
                return@launch
            } else {
                viewState.initActivities(activities)
            }

            withContext(Dispatchers.IO) {
                timeLog.addAll(activitiesUseCase.getActivityTimeLog(currentActivity!!.id))
            }

            if (timeLog.isNotEmpty()) {
                viewState.initTimeLog(timeLog)
            } else {
                viewState.setErrorVisibility(true)
            }
        }
    }

    fun onActivityClicked(activity: ActivityEntity) {
        job = GlobalScope.launch(Dispatchers.Main) {
            currentActivity = activity
            val items = withContext(Dispatchers.IO) {
                activitiesUseCase.getActivityTimeLog(activity.id)
            }
            viewState.initTimeLog(items)
        }
    }
}