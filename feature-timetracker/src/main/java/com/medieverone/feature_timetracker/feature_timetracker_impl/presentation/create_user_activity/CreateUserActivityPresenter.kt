package com.medieverone.feature_timetracker.feature_timetracker_impl.presentation.create_user_activity

import com.medieverone.core_ui.BasePresenter
import com.medieverone.feature_activities.featue_activities_api.ActivitiesApi
import com.medieverone.feature_activities.featue_activities_api.FeatureActivitiesApi
import com.medieverone.feature_activities.feature_activities_impl.domain.entities.ActivityEntity
import kotlinx.coroutines.*
import moxy.InjectViewState
import javax.inject.Inject

@DelicateCoroutinesApi
@InjectViewState
class CreateUserActivityPresenter @Inject constructor(

) : BasePresenter<CreateUserActivityView>() {

    fun onCreateActivityClicked(tag: String) {
        if (tag.isBlank()) {
            viewState.showToast("Вы не можете создать пустую активность!")
            return
        }
        job = GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                featureActivitiesApi.addActivity(
                    ActivityEntity(
                        id = (0..Int.MAX_VALUE).random(),
                        name = tag,
                        comment = "",
                        logsIds = null
                    )
                )
            }
            viewState.showToast("It's been created!")
            viewState.navigateToPreviousScreen()
        }
    }
}