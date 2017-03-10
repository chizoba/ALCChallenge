/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.chizoba.alcchallenge.utilities;

import android.content.ContentValues;
import android.content.Context;

import com.github.chizoba.alcchallenge.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Utility functions to handle OpenWeatherMap JSON data.
 */
public final class GithubJsonUtils {

    /**
     * This method parses JSON from a web response and returns an array of Strings
     * describing the weather over various days from the forecast.
     * <p/>
     * Later on, we'll be parsing the JSON into structured data within the
     * getFullWeatherDataFromJson function, leveraging the data we have stored in the JSON. For
     * now, we just convert the JSON into human-readable strings.
     *
     * @param forecastJsonStr JSON response from server
     * @return Array of Strings describing weather data
     * @throws JSONException If JSON data cannot be properly parsed
     */
    public static ArrayList<User> getSimpleWeatherStringsFromJson(Context context, String forecastJsonStr)
            throws JSONException {

        /* Users information. Each user's info is an element of the "items" array */
        final String USER_LIST = "items";

        /* A User info - picture and username  */
        final String USER_AVATAR_URL = "avatar_url";
        final String USER_LOGIN = "login";
        final String USER_PROFILE_URL = "html_url";

        /* String array to hold each user's details String */
        ArrayList<User> parsedUserData = null;

        JSONObject forecastJson = new JSONObject(forecastJsonStr);

        JSONArray userArray = forecastJson.getJSONArray(USER_LIST);

        parsedUserData = new ArrayList<>(userArray.length());

        for (int i = 0; i < userArray.length(); i++) {

            /* Get the JSON object representing the user */
            JSONObject user = userArray.getJSONObject(i);

            /* These are the values that will be collected */
            String avatar_url = user.getString(USER_AVATAR_URL);
            String login = user.getString(USER_LOGIN);
            String profile_url = user.getString(USER_PROFILE_URL);

//            HashMap<String, String> map = new HashMap<>();

            parsedUserData.add(new User(login, avatar_url, profile_url));
//            map.put(USER_AVATAR_URL, user.getString(USER_AVATAR_URL));
//
//            map.put(USER_LOGIN, user.getString(USER_LOGIN));

//            parsedUserData.add(map);
        }

        return parsedUserData;
    }

    /**
     * Parse the JSON and convert it into ContentValues that can be inserted into our database.
     *
     * @param context         An application context, such as a service or activity context.
     * @param forecastJsonStr The JSON to parse into ContentValues.
     * @return An array of ContentValues parsed from the JSON.
     */
    public static ContentValues[] getFullWeatherDataFromJson(Context context, String forecastJsonStr) {
        /** This will be implemented in a future lesson **/
        return null;
    }
}