package com.medieverone.progresslog.di.presenters

import com.medieverone.progresslog.ui.MainPresenter
import dagger.Subcomponent

@Subcomponent
interface PresentersComponent {

    fun provideMainPresenter(): MainPresenter
}