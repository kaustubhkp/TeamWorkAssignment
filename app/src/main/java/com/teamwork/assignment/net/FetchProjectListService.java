package com.teamwork.assignment.net;

import com.teamwork.assignment.model.Result;
import com.teamwork.assignment.utils.Constants;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface FetchProjectListService {

    @GET(Constants.GET_PROJECT_LIST)
    Observable<Result> getProjectList(@Header("Authorization") String contentRange);

}
