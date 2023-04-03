package com.aiden.soccer.presentation.team

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import data.entities.Match
import data.entities.Team
import data.remote.Resource
import domain.usecases.GetMatchesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import domain.usecases.GetTeamsUseCase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * ViewModel for [TeamFragment].
 */
@ExperimentalCoroutinesApi
@HiltViewModel
class TeamViewModel @Inject constructor(
    private val getTeamsUseCase: GetTeamsUseCase,
    private val getMatchesUseCase: GetMatchesUseCase,
) :
    BaseViewModel() {
    private val _teamsLiveData = MutableLiveData<Resource<List<Team>>>()
    val teamsLiveData: LiveData<Resource<List<Team>>> = _teamsLiveData

    private val _matchesLiveData = MutableLiveData<Match>()
    val matchesLiveData: LiveData<Match> = _matchesLiveData

    fun getTeams() {
        viewModelScope.launch {
            getTeamsUseCase.invoke().onStart {  }.collect {
                Log.d("hailt", " TeamViewModel ${it.data?.size}")
                _teamsLiveData.value = it
            }
        }
    }

    fun getMatches() {
        getMatchesUseCase().onEach { res ->
            when (res) {
                is Resource.Loading -> {}
                is Resource.Success -> res.data?.let { _matchesLiveData.postValue(it) }
                is Resource.Error -> raiseErrorMessage(res.message)
            }
        }.launchIn(viewModelScope)
    }
}