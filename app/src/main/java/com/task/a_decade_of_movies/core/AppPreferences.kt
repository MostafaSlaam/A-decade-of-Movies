package com.task.a_decade_of_movies.core
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.task.a_decade_of_movies.features.home.domain.model.MovieModel

import kotlin.properties.Delegates

object AppPreferences {


    internal var appPrefence: SharedPreferences by Delegates.notNull<SharedPreferences>()

    // notifications preference
    internal var ApplicationLocale_PREF = "ApplicationLocale"
    internal var preferenceEditor: SharedPreferences.Editor by Delegates.notNull<SharedPreferences.Editor>()
    internal var PREFS_NAME = "MOVIESAppPref"

    fun initializePreferences(context: Context) {
        appPrefence = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }


    //endregion

    //region User
    //key
    const val CURRENT_MOVIE = "current_movie"
    const val CURRENT_PASSWORD = "CurrentPassword"
    const val CURRENT_EMAIL = "CurrentEmail"
    const val ACCESS_TOKEN = "AccessToken"
    const val REMEMBER_ME = "RememberMe"
    const val FCM_TOKEN = "FCM_Token"
    const val USER_EMAIL = "user_email"




    fun storeCurrentFCMToken(fCMToken: String) {
        preferenceEditor = appPrefence.edit()
        preferenceEditor.putString(FCM_TOKEN, fCMToken)
        preferenceEditor.commit()
    }

    fun getCurrentFCMToken(): String? {
        return appPrefence.getString(FCM_TOKEN, "")!!
    }


    fun storeCurrentMovie(movie: MovieModel) {
        try {
            var bodyjson = ""
            val gson = Gson()
            bodyjson = gson.toJson(movie)
            preferenceEditor = appPrefence.edit()
            preferenceEditor.putString(CURRENT_MOVIE, bodyjson)
            preferenceEditor.commit()
        } catch (e1: Exception) {
            e1.printStackTrace()

        }

    }

    fun getCurrentMovie(): MovieModel? {
        val json= appPrefence.getString(CURRENT_MOVIE, "")!!
        try {
            val gson = Gson()
            val type = object : TypeToken<MovieModel>() {}.type
            return gson.fromJson(json, type)
        } catch (e1: Exception) {
            e1.printStackTrace()
            return null!!
        }
    }
















    fun setLocale(lang: String) {
        preferenceEditor = appPrefence.edit()
        preferenceEditor.putString(ApplicationLocale_PREF, lang)
        preferenceEditor.commit()
    }

    fun getLocale(): String {
        return appPrefence.getString(ApplicationLocale_PREF, "en")!!
    }



    const val APP_THEME = "AppTheme"









}