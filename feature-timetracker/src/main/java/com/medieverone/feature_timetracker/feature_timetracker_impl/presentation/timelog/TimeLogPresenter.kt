package com.medieverone.feature_timetracker.feature_timetracker_impl.presentation.timelog

import com.medieverone.core_ui.BasePresenter
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.entities.TimeLogEntity
import com.medieverone.feature_timetracker.feature_timetracker_impl.domain.usecases.TimeLogUseCase
import kotlinx.coroutines.*
import moxy.InjectViewState
import javax.inject.Inject

@InjectViewState
class TimeLogPresenter @Inject constructor(
    private val localUseCase: TimeLogUseCase
): BasePresenter<TimeLogView>() {

    val timeLogItems: ArrayList<TimeLogEntity> = arrayListOf()

    @DelicateCoroutinesApi
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        job = GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                timeLogItems.addAll(localUseCase.getAllTimeLogItems())
            }
            viewState.initItems(timeLogItems)
        }
    }
}