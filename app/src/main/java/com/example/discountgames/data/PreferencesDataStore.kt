package com.example.discountgames.data

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore by preferencesDataStore(name = "filter_preferences")

object PreferenceKeys {
    val RATING_KEY = intPreferencesKey("selected_rating")
    val YEAR_KEY = stringPreferencesKey("selected_year")
}
