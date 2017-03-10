package com.github.chizoba.alcchallenge.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils {

    final static String GITHUB_BASE_URL =
            "https://api.github.com/search/users";

    final static String PARAM_QUERY = "q";
    final static String users = "";

    /*
    * The sort field. One of stars, forks, or updated.
    * Default: results are sorted by best match if no field is specified.
    */
    final static String PARAM_LANGUAGE = "language:java";
    final static String lang = "java";

    /*
    * The sort field. One of stars, forks, or updated.
    * Default: results are sorted by best match if no field is specified.
    */
    final static String PARAM_LOCATION = "location";
    final static String location = "lagos";
    /**
     * Builds the URL used to query Github.
     *
     * @return The URL to use to query the weather server.
     */
    public static URL buildUrl() {
        String link = GITHUB_BASE_URL.concat("?q=+language:java+location:lagos");
        Uri builtUri = Uri.parse(link);

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}