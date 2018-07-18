package com.teamwork.assignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("projects")
    @Expose
    public List<ProjectInfo> projects;

    @SerializedName("STATUS")
    @Expose
    public String status;
}
