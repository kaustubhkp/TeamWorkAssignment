package com.teamwork.assignment.activities;

import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

class TestBase {
    Context context;

    void baseSetup() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    void kill(Activity activity) {
        if (activity != null) {
            activity.finish();
        }
    }
}
