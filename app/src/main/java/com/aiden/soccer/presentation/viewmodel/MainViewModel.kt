package com.aiden.soccer.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import data.entities.Match
import data.entities.Team
import data.remote.Resource
import domain.usecases.GetTeamDetailUseCase
import domain.usecases.GetTeamsUseCase
import domain.usecases.RemoveTeamsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTeamDetailUseCase: GetTeamDetailUseCase,
    private val removeTeamsUseCase: RemoveTeamsUseCase,
    private val getTeamsUseCase: GetTeamsUseCase,
) : BaseViewModel() {
    var match: Match? = null
    var localTeams: List<Team>? = null

    private val _matchesLiveData = MutableLiveData<Match>()
    val matchesLiveData: LiveData<Match> = _matchesLiveData

    private val _localTeamsLiveData = MutableLiveData<List<Team>>()
    val localTeamsLiveData: LiveData<List<Team>> = _localTeamsLiveData

    private fun getTeams() {
        viewModelScope.launch {
            removeTeamsUseCase.invoke()
                .onStart {}
                .map {}
                .collect {}
        }

        viewModelScope.launch {
            getTeamsUseCase.invoke().onStart { }.collect {
                _localTeamsLiveData.postValue(it.data ?: mutableListOf())
            }
        }
    }

    fun getTeamDetail(teamId: String) {
        getTeamDetailUseCase(teamId).onEach { res ->
            when (res) {
                is Resource.Loading -> {}
                is Resource.Success -> res.data?.let { _matchesLiveData.postValue(it) }
                is Resource.Error -> raiseErrorMessage(res.message)
            }
        }.launchIn(viewModelScope)
    }
}