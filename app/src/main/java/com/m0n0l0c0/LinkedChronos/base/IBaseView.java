package com.m0n0l0c0.LinkedChronos.base;

import android.content.Context;

public interface IBaseView {

    void showToastMessage(String message);

    void setProgressBar(boolean show);

    Context getContext();
}
