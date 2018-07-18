package com.teamwork.assignment.activities;

import android.support.annotation.NonNull;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.teamwork.assignment.R;
import com.teamwork.assignment.ui.activities.HomeActivity;
import com.teamwork.assignment.utils.TestUtils;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class HomeActivityTest extends TestBase {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityRule =
            new ActivityTestRule<>(HomeActivity.class,
                    false, true);

    @Before
    public void setUp() {
        baseSetup();
    }

    @Test
    public void checkProjectInfoForFirstRow() {
        onView(TestUtils.withRecyclerView(R.id.rv_project_list)
                .atPositionOnView(0, R.id.tv_project_name))
                .check(matches(withText("Project Name: Brazil")));

        onView(TestUtils.withRecyclerView(R.id.rv_project_list)
                .atPositionOnView(0, R.id.tv_project_start_date))
                .check(matches(withText("Start Date: 05 Apr 2014")));

        onView(TestUtils.withRecyclerView(R.id.rv_project_list)
                .atPositionOnView(0, R.id.tv_project_end_date))
                .check(matches(withText("End Date: 15 Jun 2018")));
    }

    @Test
    public void checkItemClickBasedOnPosition() {
        // Perform a click on first element in the RecyclerView
        onView(withId(R.id.rv_project_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void checkItemClickBasedOnText() {
        // Perform click on an element with a specific text
        onView(withId(R.id.rv_project_list)).perform(RecyclerViewActions.actionOnItem(
                hasDescendant(withText("Project Name: Time Machine R&D")), click()));
    }

    @After
    public void tearDown() throws Exception {
        kill(mActivityRule.getActivity());
    }

    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has item at position " + position + ": ");
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                // has no item on such position
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView);
            }
        };
    }
}