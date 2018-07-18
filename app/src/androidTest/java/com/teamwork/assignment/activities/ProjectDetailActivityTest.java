package com.teamwork.assignment.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.teamwork.assignment.R;
import com.teamwork.assignment.model.ProjectInfo;
import com.teamwork.assignment.ui.activities.ProjectDetailActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.parceler.Parcels;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.teamwork.assignment.ui.activities.ProjectDetailActivity.PROJECT_INFO;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ProjectDetailActivityTest extends TestBase {

    @Rule
    public ActivityTestRule<ProjectDetailActivity> mActivityRule =
            new ActivityTestRule<>(ProjectDetailActivity.class,
                    false, false);

    @Before
    public void setup() {
        baseSetup();
    }

    @NonNull
    private Intent intent() {
        Intent result = new Intent(context, ProjectDetailActivity.class);
        result.putExtra(PROJECT_INFO, Parcels.wrap(getProjectInfo()));
        return result;
    }


    @Test
    public void shouldDisplayProjectDetails() {
        mActivityRule.launchActivity(intent());

        onView(withId(R.id.tv_project_name)).check(matches(withText("Project Name: Brazil")));
        onView(withId(R.id.tv_project_start_date)).check(matches(withText("Start Date: 05 Apr 2014")));
        onView(withId(R.id.tv_project_end_date)).check(matches(withText("End Date: 15 Jun 2018")));
        onView(withId(R.id.tv_project_status)).check(matches(withText("Status: active")));
        onView(withId(R.id.tv_project_description)).check(matches(withText("Description: This is going to be a normal description of an amazing country :D")));
    }


    @After
    public void teardown() throws InterruptedException {
        kill(mActivityRule.getActivity());
    }

    private ProjectInfo getProjectInfo() {
        ProjectInfo info = new ProjectInfo();
        info.name = "Brazil";
        info.logo = "https://s3.amazonaws.com/TWFiles/349705/projectLogo/tf_929A76DB-AA0C-B885-B716E9094F4683F5.animated-brazil-flag-brazilian-national-banner_h2nf-ru_F0000.png";
        info.startDate = "20140405";
        info.endDate = "20180615";
        info.status = "active";
        info.description = "This is going to be a normal description of an amazing country :D";
        return info;
    }
}