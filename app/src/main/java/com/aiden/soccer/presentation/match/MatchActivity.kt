package com.aiden.soccer.presentation.match

import android.os.Bundle
import android.view.LayoutInflater
import com.aiden.soccer.databinding.ActivityMatchBinding
import com.aiden.soccer.presentation.base.BaseActivity
import com.aiden.soccer.presentation.match.adapter.ViewPagerAdapter
import com.aiden.soccer.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class MatchActivity : BaseActivity<ActivityMatchBinding, MainViewModel>(MainViewModel::class) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewModelSelf) {
            intent.getStringExtra(KEY_TEAM_ID)?.let {
                getTeamDetail(it)
            }
        }
    }

    override fun initAction() {
        binding.lnHeader.imvBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater): ActivityMatchBinding {
        return ActivityMatchBinding.inflate(inflater)
    }

    override fun onSubscribeObserver() {
        viewModelSelf.matchesLiveData.observe(this) {
            viewModelSelf.match = it
            setupViewPager()
        }
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(UpcomingMatchFragment(), "Upcoming")
        adapter.addFragment(PreviousMatchFragment(), "Previous")
        binding.viewPager.adapter = adapter
        binding.tbLayout.setupWithViewPager(binding.viewPager)
    }

    companion object {
        const val KEY_TEAM_ID = "KEY_TEAM_ID"
    }
}