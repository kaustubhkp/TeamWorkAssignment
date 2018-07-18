package com.teamwork.assignment.mvp;


public interface BaseView {
    void showError(Throwable throwable);
    void showInternetConnectionError(String message);
}
