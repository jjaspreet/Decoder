package com.example.dcoderproject.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.List;


public class Info {

    private String sucess;

    private String message;

    private List<Data> data;

    public String getSucess() {
        return sucess;
    }

    public void setSucess(String sucess) {
        this.sucess = sucess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public static class Data{

        private String _id;

        private boolean is_project;

        private int language_id;

        private String file;

        private String title;

        private String description;

        private List<String> tags;

        private String createdAt;

        private String updatedAt;

        private Stars stars;

        private Forks forks;

        private String username;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public boolean isIs_project() {
            return is_project;
        }

        public void setIs_project(boolean is_project) {
            this.is_project = is_project;
        }

        public int getLanguage_id() {
            return language_id;
        }

        public void setLanguage_id(int language_id) {
            this.language_id = language_id;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
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

        public List<String> getTags() {
            return tags;
        }

        public void setTags(List<String> tags) {
            this.tags = tags;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Stars getStars() {
            return stars;
        }

        public void setStars(Stars stars) {
            this.stars = stars;
        }

        public Forks getForks() {
            return forks;
        }

        public void setForks(Forks forks) {
            this.forks = forks;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
