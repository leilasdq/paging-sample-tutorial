package com.example.pagingsample.local.convertor

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class ListToStringConvertor: BaseConvertor<String>()