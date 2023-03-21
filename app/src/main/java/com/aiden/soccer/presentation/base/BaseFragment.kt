package com.aiden.soccer.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.viewbinding.ViewBinding
import common.BaseViewModel
import kotlin.reflect.KClass

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel>(classVM: KClass<VM>) :
    Fragment() {

    private var _binding: VB? = null

    protected val binding get() = _binding!!
    protected val baseActivity by lazy { requireActivity() as BaseActivity<*, *> }

    protected abstract val layoutId: Int
    protected val viewModelSelf by createViewModelLazy(classVM, { viewModelStore })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = inflateViewBinding(inflater)

        initView(savedInstanceState)
        initAction()
        onSubscribeObserver()
        return binding.root
    }

    abstract fun inflateViewBinding(inflater: LayoutInflater): VB

    open fun onSubscribeObserver() {
        bindViewModel(viewModelSelf)
    }

    protected open fun initView(savedInstanceState: Bundle?) {}

    protected open fun initAction() {}

    protected open fun bindViewModel(viewModel: BaseViewModel) {

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
    }

    protected fun hideActionBar() {
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    protected fun setTitle(title: String) {
        (activity as AppCompatActivity).supportActionBar?.run {
            this.title = title
            show()
        }
    }
}