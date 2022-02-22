package com.example.tawktest.data.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

object PreferenceHelper {

    fun defaultPrefs(context: Context): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun customPrefs(context: Context, name: String): SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    /**Edit and apply to shared preference*/
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    /**
     * puts a key value pair in shared prefs if doesn't exists, otherwise updates value on given [key]
     * Add entry in shared preference
     */
    operator fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is String? -> edit { it.putString(key, value) }
            is Int -> edit { it.putInt(key, value) }
            is Boolean -> edit { it.putBoolean(key, value) }
            is Float -> edit { it.putFloat(key, value) }
            is Long -> edit { it.putLong(key, value) }
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    /**
     * finds value on given key.
     * [T] is the type of value
     * @param defaultValue optional default value - will take null for strings, false for bool and -1 for numeric values if [defaultValue] is not specified
     * Get entry from shared preference
     */
    inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    /**Remove/Delete/Clear entry from shared preference*/
    fun SharedPreferences.remove(name: String) = edit().remove(name).apply()

    /**Clear shared preference*/
    fun SharedPreferences.clearPreference() = edit().clear().apply()

    /**Check if key exists (will return true/false)*/
    fun SharedPreferences.isContains(name: String): Boolean = contains(name)


    const val USER_ID = "USER_ID"
    const val USER_TYPE = "USER_TYPE"
    const val USERNAME = "USERNAME"
    const val USER_NAME = "USER_NAME"
    const val USER_EMAIL = "USER_EMAIL"
    const val USER_MOBILE = "USER_MOBILE"
    const val USER_DOB = "USER_DOB"
    const val USER_ADDRESS = "USER_ADDRESS"
    const val USER_GENDER = "USER_GENDER"
    const val USER_PASSWORD = "USER_PASSWORD"

    const val STATUS = "STATUS"
    const val SUBSCRIPTION = "SUBSCRIPTION"

    const val LAST_LOGGED_IN = "LAST_LOGGED_IN"
    const val CREATED_AT = "CREATED_AT"

}