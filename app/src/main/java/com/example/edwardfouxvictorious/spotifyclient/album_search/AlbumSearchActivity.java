package com.example.edwardfouxvictorious.spotifyclient.album_search;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edwardfouxvictorious.spotifyclient.R;
import com.example.edwardfouxvictorious.spotifyclient.model.Album;
import com.example.edwardfouxvictorious.spotifyclient.model.Albums;

import java.util.List;

public class AlbumSearchActivity extends AppCompatActivity implements AlbumSearchView {
    public static final String DATA = "data";

    private RecyclerView recyclerView;
    private EditText editText;
    private AlbumSearchPresenter albumSearchPresenter;
    private AlbumSearchAdapter albumSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_search_activtiy);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        editText = (EditText) findViewById(R.id.edit_text);

        albumSearchAdapter = new AlbumSearchAdapter(new ItemCLickListener());
        albumSearchPresenter = new AlbumSearchPresenter(this);

        recyclerView.setAdapter(albumSearchAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if(!isConnectedToInternet()) {
                        Toast.makeText(AlbumSearchActivity.this, R.string.no_internet, Toast.LENGTH_SHORT).show();
                        return true;
                    }

                    albumSearchPresenter.provideSearchResults(editText.getText().toString());
                    hideKeyboardFrom(editText);
                    return true;
                }
                return false;
            }
        });

        albumSearchPresenter.onCreateCalled(savedInstanceState);
    }

    /**
     * Persists the state of the list during the screen rotation
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Albums albums = new Albums();
        albums.setItems(albumSearchAdapter.getList());
        outState.putParcelable(DATA, albums);
    }

    @Override
    public void showResult(List<Album> albumList) {
        albumSearchAdapter.setList(albumList);
        albumSearchAdapter.notifyDataSetChanged();
        hideKeyboardFrom(editText);
    }

    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private boolean isConnectedToInternet() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

    }

    private static class ItemCLickListener implements AlbumSearchAdapter.ItemClickListener {
        @Override
        public void onItemCLicked(Album album) {

        }
    }
}
