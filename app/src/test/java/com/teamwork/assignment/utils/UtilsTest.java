package com.teamwork.assignment.utils;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class UtilsTest {

    @Test
    public void shouldReturnFormattedDate() {
        String date = "20140405";
        assertThat(Utils.getDateForLocaleFromUtc(date, Utils.DD_MM_YYY), equalTo("05 Apr 2014"));
    }
}