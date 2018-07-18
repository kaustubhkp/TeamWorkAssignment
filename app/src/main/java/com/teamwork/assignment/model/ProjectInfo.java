package com.teamwork.assignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class ProjectInfo {
    public String id;
    public String isProjectAdmin;
    public Category category;
    public String starred;
    public String description;
    public Company company;
    public String name;
    public String logo;
    public String startDate;
    public String announcement;
    public String status;
    public String notifyeveryone;
    public String endDate;

    @SerializedName("show-announcement")
    @Expose
    public String showAnnouncement;

    @SerializedName("created-on")
    @Expose
    public String createdOn;

    @SerializedName("start-page")
    @Expose
    public String startPage;

    @SerializedName("last-changed-on")
    @Expose
    public String lastChangedOn;

    @SerializedName("harvest-timers-enabled")
    @Expose
    public String harvestTimersEnabled;
}
