package com.teamwork.assignment.presenters;

import android.content.Context;

import com.teamwork.assignment.R;
import com.teamwork.assignment.model.Result;
import com.teamwork.assignment.net.FetchProjectListService;
import com.teamwork.assignment.utils.Base64Coder;
import com.teamwork.assignment.utils.Utils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.teamwork.assignment.utils.Constants.STATUS_OK;


public class HomePresenter implements ILoadProjects.IPresenter {
    private final FetchProjectListService fetchProjectService;
    private final CompositeDisposable compositeDisposable;
    private final ILoadProjects.IView view;
    private final Context mContext;

    public HomePresenter(Context context, FetchProjectListService fetchProjectService, ILoadProjects.IView view) {
        this.fetchProjectService = fetchProjectService;
        this.view = view;
        this.mContext = context;
        compositeDisposable = new CompositeDisposable();
    }


    @Override
    public void getProjectList() {
        compositeDisposable.add(fetchProjectListFromAPI()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> view.showProgress(true))
                .doOnTerminate(() -> view.showProgress(false))
                .subscribe(this::handleResult, view::showError));
        }

    private Observable<Result> fetchProjectListFromAPI() {
        return fetchProjectService.getProjectList("Basic "
                + Base64Coder.encodeString(mContext.getString(R.string.api_key)));
    }

    private void handleResult(Result result) {
        if (result.status.equals(STATUS_OK) && result.projects != null) {
            if (result.projects.isEmpty()) {
                view.showTryAgain(mContext.getString(R.string.project_list_empty));
            } else {
                view.showProjectList(result.projects);
            }
        } else {
            view.showTryAgain(mContext.getString(R.string.something_went_wrong));
        }
    }


    @Override
    public void start(Context context) {
        if (Utils. isOnline(context)) {
            getProjectList();
        } else {
            view.showInternetConnectionError(mContext.getString(R.string.connection_error));
        }
    }


    @Override
    public void stop() {
        compositeDisposable.clear();
    }

}
