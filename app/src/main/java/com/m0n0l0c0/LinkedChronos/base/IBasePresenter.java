package com.m0n0l0c0.LinkedChronos.base;

public interface IBasePresenter<ViewT> {

    void onViewActive(ViewT view);

    void onViewInactive();
}
