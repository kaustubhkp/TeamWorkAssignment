package com.teamwork.assignment.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Company {

    public String id;

    public String name;

    @SerializedName("is-owner")
    @Expose
    public String isOwner;
}
