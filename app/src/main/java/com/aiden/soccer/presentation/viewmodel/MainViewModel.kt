package com.aiden.soccer.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import data.entities.Match
import data.remote.Resource
import domain.usecases.GetTeamDetailUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTeamDetailUseCase: GetTeamDetailUseCase
) : BaseViewModel() {
    var match: Match? = null

    private val _matchesLiveData = MutableLiveData<Match>()
    val matchesLiveData: LiveData<Match> = _matchesLiveData

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