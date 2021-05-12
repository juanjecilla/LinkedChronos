package com.scallop.linkedchronos.base

interface IBasePresenter<ViewT> {

    fun onViewActive(view: ViewT)

    fun onViewInactive()
}
