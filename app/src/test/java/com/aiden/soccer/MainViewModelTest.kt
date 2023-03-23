package com.aiden.soccer

import android.app.Activity
import com.aiden.soccer.presentation.viewmodel.MainViewModel
import domain.usecases.GetTeamDetailUseCase
import domain.usecases.GetTeamsUseCase
import domain.usecases.RemoveTeamsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest : Assert() {

    private var viewModel: MainViewModel? = null
    private var activity: Activity? = null

    @Mock
    private lateinit var getTeamDetailUseCase: GetTeamDetailUseCase

    @Mock
    private lateinit var getTeamsUseCase: GetTeamsUseCase

    @Mock
    private lateinit var removeTeamsUseCase: RemoveTeamsUseCase

    @Before
    fun setup() {
        activity = mock(Activity::class.java)
        viewModel = MainViewModel(getTeamDetailUseCase, removeTeamsUseCase, getTeamsUseCase)
    }

    @Test
    fun getTeams() {
        viewModel?.getTeamDetail("")
    }
}