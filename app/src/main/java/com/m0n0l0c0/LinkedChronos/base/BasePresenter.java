package com.m0n0l0c0.LinkedChronos.base;

public class BasePresenter<ViewT> implements IBasePresenter<ViewT> {

    protected ViewT view;

    @Override
    public void onViewActive(ViewT view) {
        this.view = view;
    }

    @Override
    public void onViewInactive() {
        view = null;
    }
}