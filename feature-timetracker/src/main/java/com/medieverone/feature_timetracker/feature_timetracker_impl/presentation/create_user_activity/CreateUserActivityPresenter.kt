package com.medieverone.feature_timetracker.feature_timetracker_impl.presentation.create_user_activity

import com.medieverone.core_ui.BasePresenter
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.UserActivityEntity
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.usecases.UserActivitiesUseCase
import kotlinx.coroutines.*
import moxy.InjectViewState
import javax.inject.Inject

@DelicateCoroutinesApi
@InjectViewState
class CreateUserActivityPresenter @Inject constructor(
    private val userActivitiesUseCase: UserActivitiesUseCase
) : BasePresenter<CreateUserActivityView>() {

    fun onCreateActivityClicked(tag: String) {
        if(tag.isBlank()) {
            viewState.showToast("Вы не можете создать пустую активность!")
            return
        }
        job = GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                userActivitiesUseCase.addUserActivity(
                    UserActivityEntity(
                        (0..Int.MAX_VALUE).random(),
                        tag
                    )
                )
            }
            viewState.showToast("It's been created!")
            viewState.navigateToPreviousScreen()
        }
    }
}