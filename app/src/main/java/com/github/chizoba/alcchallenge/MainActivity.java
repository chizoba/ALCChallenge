package com.github.chizoba.alcchallenge;

import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.chizoba.alcchallenge.utilities.GithubJsonUtils;
import com.github.chizoba.alcchallenge.utilities.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mErrorMessageDisplay;

    private ProgressBar mLoadingIndicator;
    
    private FloatingActionButton mFloatingActionButton;

    ArrayList<HashMap<String, String>> simpleJsonUserData;

    ArrayList<User> usersList;

    private RecyclerViewAdapter mAdapter;

    private RecyclerView mRecyclerView;

    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();

        makeGithubSearchQuery();

    }

    private void initializeViews() {
        mLoadingIndicator = (ProgressBar) findViewById(R.id.progress_bar);
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

    }

    /**
     * This method constructs the URL (using {@link NetworkUtils}) for the java users in lagos
     * that are on github, and finally fires off an AsyncTask to perform the GET request using
     * our {@link GithubQueryTask}
     */
    private void makeGithubSearchQuery() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        URL githubSearchUrl = NetworkUtils.buildUrl();
//        mSearchResultsTextView.setText(githubSearchUrl.toString());
        new GithubQueryTask().execute(githubSearchUrl);
    }

    /**
     * This method will make the View for the JSON data visible and
     * hide the error message.
     */
    private void showJsonDataView() {
        // First, make sure the error is invisible
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        // Then, make sure the JSON data is visible
        mRecyclerView.setVisibility(View.VISIBLE);

//        mSearchResultsTextView.setVisibility(View.VISIBLE);
    }

    /**
     * This method will make the error message visible and hide the JSON
     * View.
     */
    private void showErrorMessage() {
        // First, hide the currently visible data
        mRecyclerView.setVisibility(View.INVISIBLE);
//        mSearchResultsTextView.setVisibility(View.INVISIBLE);
        // Then, show the error
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    public void refreshQuery(View view) {
        makeGithubSearchQuery();
    }

    private class GithubQueryTask extends AsyncTask<URL, Void, ArrayList<User>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected ArrayList<User> doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String githubSearchResults = null;
            try {
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);

                usersList = GithubJsonUtils
                        .getSimpleWeatherStringsFromJson(MainActivity.this, githubSearchResults);

                return usersList;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<User> githubSearchResults) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (githubSearchResults != null) {
                showJsonDataView();
                /*
                 * Iterate through the array and append the Strings to the TextView. The reason why we add
                 * the "\n\n\n" after the String is to give visual separation between each String in the
                 * TextView. Later, we'll learn about a better way to display lists of data.
                 */

//                data = Arrays.asList(githubSearchResults);
//                for (String data : githubSearchResults) {
//
//                    mSearchResultsTextView.append((data) + "\n\n\n");
//                }

                layoutManager = new LinearLayoutManager(MainActivity.this);

                mRecyclerView.setLayoutManager(layoutManager);

                mRecyclerView.setHasFixedSize(true);

                mAdapter = new RecyclerViewAdapter(MainActivity.this, githubSearchResults);

                mRecyclerView.setAdapter(mAdapter);
            } else {
                showErrorMessage();
            }
        }
        
    }

//    public List<User> fill_with_data() {
//
//        List<User> data = new ArrayList<>();
//
//        data.add(new Data("Batman vs Superman", "Following the destruction of Metropolis, Batman embarks on a personal vendetta against Superman ", R.drawable.ic_action_movie));
//        data.add(new Data("X-Men: Apocalypse", "X-Men: Apocalypse is an upcoming American superhero film based on the X-Men characters that appear in Marvel Comics ", R.drawable.ic_action_movie));
//        data.add(new Data("Captain America: Civil War", "A feud between Captain America and Iron Man leaves the Avengers in turmoil.  ", R.drawable.ic_action_movie));
//        data.add(new Data("Kung Fu Panda 3", "After reuniting with his long-lost father, Po  must train a village of pandas", R.drawable.ic_action_movie));
//        data.add(new Data("Warcraft", "Fleeing their dying home to colonize another, fearsome orc warriors invade the peaceful realm of Azeroth. ", R.drawable.ic_action_movie));
//        data.add(new Data("Alice in Wonderland", "Alice in Wonderland: Through the Looking Glass ", R.drawable.ic_action_movie));
//
//        return data;
//    }
}
