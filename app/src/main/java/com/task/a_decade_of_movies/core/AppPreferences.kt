package com.edu.academy.core.app
import android.content.Context
import android.content.SharedPreferences
import com.edu.academy.core.theme.AppTheme
import kotlin.properties.Delegates

object AppPreferences {


    internal var appPrefence: SharedPreferences by Delegates.notNull<SharedPreferences>()

    // notifications preference
    internal var ApplicationLocale_PREF = "ApplicationLocale"
    internal var preferenceEditor: SharedPreferences.Editor by Delegates.notNull<SharedPreferences.Editor>()
    internal var PREFS_NAME = "CONCRETEAppPref"

    fun initializePreferences(context: Context) {
        appPrefence = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }


    //endregion

    //region User
    //key
    const val CURRENT_USER = "CurrentUser"
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

















    fun setLocale(lang: String) {
        preferenceEditor = appPrefence.edit()
        preferenceEditor.putString(ApplicationLocale_PREF, lang)
        preferenceEditor.commit()
    }

    fun getLocale(): String {
        return appPrefence.getString(ApplicationLocale_PREF, "ar")!!
    }



    const val APP_THEME = "AppTheme"

    fun setTheme(theme: String) {
        preferenceEditor = appPrefence.edit()
        preferenceEditor.putString(APP_THEME, theme)
        preferenceEditor.commit()
    }

    fun getTheme(): String {
        return appPrefence.getString(APP_THEME, AppTheme.Default.name)!!
    }







}