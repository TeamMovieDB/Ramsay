package com.example.ramsay.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.TextView
import com.example.ramsay.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SharedPreferencesConfig(private val context: Context?) {
    private var sharedPreferences: SharedPreferences = context?.getSharedPreferences(
        context.getString(R.string.shared_preferences),
        Context.MODE_PRIVATE
    )!!
    private val gson = Gson()
    private var jsonStringString = ""
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var type: Type

    fun savingTags(list: ArrayList<TextView>){
        Log.d("tags_saving_count", list.size.toString())
        editor = sharedPreferences.edit()
        jsonStringString = gson.toJson(list)
        editor.putString(context?.getString(R.string.tags), jsonStringString)
        editor.apply()
    }

    fun extractingTags(): ArrayList<TextView>{
        jsonStringString = sharedPreferences.getString(context?.getString(R.string.tags), "").toString()
        type = object : TypeToken<ArrayList<TextView>>() {}.type
        var tagList = gson.fromJson<ArrayList<TextView>>(jsonStringString, type)
        if(tagList == null){
            tagList = arrayListOf()
        }
        return tagList
    }
}