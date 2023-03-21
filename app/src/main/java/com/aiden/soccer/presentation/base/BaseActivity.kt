package com.aiden.soccer.presentation.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelLazy
import androidx.viewbinding.ViewBinding
import common.BaseViewModel
import kotlin.reflect.KClass

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel>(classVM: KClass<VM>) : AppCompatActivity()  {
    protected lateinit var binding: VB
    protected val viewModelSelf by ViewModelLazy(
        classVM,
        { viewModelStore },
        { defaultViewModelProviderFactory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflateViewBinding(layoutInflater)
        setContentView(binding.root)
        initView(savedInstanceState)
        initializeAction()
        onSubscribeObserver()
    }

    open fun onSubscribeObserver() {
        bindViewModel(viewModelSelf)
    }

    abstract fun inflateViewBinding(inflater: LayoutInflater): VB

    open fun initView(savedInstanceState: Bundle?) {}

    open fun bindViewModel(viewModel: BaseViewModel) {

    }

    open fun initializeAction() {}
}