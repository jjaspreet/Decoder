package com.example.dcoderproject.data.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dcoderproject.data.model.Info;
import com.example.dcoderproject.data.model.InfoEntity;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface InfoDao {

    @Insert
    void insert(InfoEntity info);

    @Delete
    void delete(InfoEntity info);

    @Update
    void update(InfoEntity info);

    @Query("Delete FROM InfoEntity")
    void deleteAll();

    @Query("SELECT * FROM InfoEntity")
    Single<List<InfoEntity>> getAllAudits();
}
