package com.andrew.stackoverflow.app.activity;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.andrew.stackoverflow.app.R;
import com.andrew.stackoverflow.app.data.WebDataStorage;
import com.andrew.stackoverflow.app.data.WebDataStorageLoader;

public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<String> {

    private TextView fetchedQuestionText;
    private DataSetObserver webDataStorageObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            getLoaderManager().restartLoader(0, null, MainActivity.this);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchedQuestionText = (TextView) findViewById(R.id.fetched_question_text);
        findViewById(R.id.fetch_question_button).setOnClickListener(new FetchQuestionButtonListener());

        getLoaderManager().restartLoader(0, null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        WebDataStorage.getInstance(this).registerObserver(webDataStorageObserver);
    }

    @Override
    protected void onPause() {
        WebDataStorage.getInstance(this).unregisterObserver(webDataStorageObserver);
        super.onPause();
    }

    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {
        return new WebDataStorageLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<String> stringLoader, String s) {
        updateFetchQuestionText(s);
    }

    private void updateFetchQuestionText(final String s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fetchedQuestionText.setText(s);
            }
        });
    }

    @Override
    public void onLoaderReset(Loader<String> stringLoader) {
    }
}
