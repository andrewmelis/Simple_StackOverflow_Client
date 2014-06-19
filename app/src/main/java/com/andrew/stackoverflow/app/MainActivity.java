package com.andrew.stackoverflow.app;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity implements LoaderManager.LoaderCallbacks<String> {

    private TextView fetchedQuestionText;
    private DataSetObserver webDataStorageObserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchedQuestionText = (TextView) findViewById(R.id.fetched_question_text);
        findViewById(R.id.fetch_question_button).setOnClickListener(new FetchQuestionButtonListener());

        webDataStorageObserver = new DataSetObserver() {
            @Override
            public void onChanged() {
                getLoaderManager().restartLoader(0, null, MainActivity.this);
            }
        };

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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
