package com.example.user.pllug_weather.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import com.example.user.pllug_weather.dao.WeatherDao;
import com.example.user.pllug_weather.entity.DbWeather;

@Database(entities = DbWeather.class, version = 1)
public abstract class WeatherDatabase extends RoomDatabase {

    private static WeatherDatabase instance;

    public abstract WeatherDao weatherDao();

    public static synchronized WeatherDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder
                    (
                            context.getApplicationContext(),
                            WeatherDatabase.class,
                            "weather_database"
                    )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static WeatherDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private WeatherDao weatherDao;

        private PopulateDbAsyncTask(WeatherDatabase db) {
            weatherDao = db.weatherDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            weatherDao.insert(new DbWeather("Lviv", 15.0d, "Sunny"));
            return null;
        }
    }
}
