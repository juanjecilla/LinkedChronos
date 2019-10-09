package com.m0n0l0c0.linkedchronos.base

interface IBasePresenter<ViewT> {

    fun onViewActive(view: ViewT)

    fun onViewInactive()
}
