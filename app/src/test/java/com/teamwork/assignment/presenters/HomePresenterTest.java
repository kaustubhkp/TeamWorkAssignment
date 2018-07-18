package com.teamwork.assignment.presenters;

import android.content.Context;

import com.teamwork.assignment.model.ProjectInfo;
import com.teamwork.assignment.model.Result;
import com.teamwork.assignment.net.FetchProjectListService;
import com.teamwork.assignment.utils.Base64Coder;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Base64Coder.class})
public class HomePresenterTest {

    @Mock
    private FetchProjectListService mockFetchProjectListService;
    @Mock
    private ILoadProjects.IView mockView;
    @Mock
    private Context mockContext;

    private HomePresenter presenter;

    @BeforeClass
    public static void setUpClass() {

        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());

        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());

        RxJavaPlugins.setComputationSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @Before
    public void setUp() {
        initMocks(this);

        PowerMockito.mockStatic(Base64Coder.class);

        when(mockContext.getString(anyInt())).thenReturn("");

        PowerMockito.when(Base64Coder.encodeString("")).thenReturn("");

        presenter = new HomePresenter(mockContext, mockFetchProjectListService, mockView);
    }

    @Test
    public void testSuccessApiWithProjectList() {
        Result data = getData("OK", true);

        Observable<Result> mockResult = Observable.just(data);

        when(mockFetchProjectListService.getProjectList(anyString())).thenReturn(mockResult);

        presenter.getProjectList();

        verify(mockView, times(1)).showProjectList(data.projects);
        verify(mockView, times(1)).showProgress(true);
        verify(mockView, never()).showTryAgain("");
    }

    @Test
    public void testFailureApiWithProjectList() {
        Result data = getData("FAIL", true);

        Observable<Result> mockResult = Observable.just(data);

        when(mockFetchProjectListService.getProjectList(anyString())).thenReturn(mockResult);

        presenter.getProjectList();

        verify(mockView, never()).showProjectList(data.projects);
        verify(mockView, times(1)).showProgress(true);
        verify(mockView, times(1)).showTryAgain("");
    }

    @Test
    public void testSuccessApiWithEmptyProjectList() {
        Result data = getData("OK", false);

        Observable<Result> mockResult = Observable.just(data);

        when(mockFetchProjectListService.getProjectList(anyString())).thenReturn(mockResult);

        presenter.getProjectList();

        verify(mockView, never()).showProjectList(data.projects);
        verify(mockView, times(1)).showProgress(true);
        verify(mockView, times(1)).showTryAgain("");
    }

    @AfterClass
    public static void tearDownClass() {
        RxAndroidPlugins.reset();
        RxJavaPlugins.reset();
    }


    private Result getData(String status, boolean shouldAddProject) {
        Result result = new Result();
        result.status = status;
        if (shouldAddProject) {
            result.projects = new ArrayList<>();
            result.projects.add(new ProjectInfo());
        }
        return result;
    }
}