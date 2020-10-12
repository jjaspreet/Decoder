package com.example.dcoderproject.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import io.reactivex.SingleSource;

@Entity(tableName = "InfoEntity")
public class InfoEntity  {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;

    private String username;

    private int stars;

    private int forks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }
}
