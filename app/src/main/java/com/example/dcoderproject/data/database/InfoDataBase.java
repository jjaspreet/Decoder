package com.example.dcoderproject.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.dcoderproject.data.database.dao.InfoDao;
import com.example.dcoderproject.data.model.Info;
import com.example.dcoderproject.data.model.InfoEntity;

@Database(entities = {InfoEntity.class}, version = 5)
public abstract class InfoDataBase extends RoomDatabase {

   public abstract InfoDao infoDao();
}
