package net.vsona.orz.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import net.vsona.orz.OrzApplication;
import net.vsona.orz.R;
import net.vsona.orz.ui.joke.DaggerJokeComponent;
import net.vsona.orz.ui.joke.JokeFragment;
import net.vsona.orz.ui.joke.JokePresenter;
import net.vsona.orz.ui.joke.JokePresenterModule;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by roy on 16/6/28.
 */
public class MainActivity extends BaseToolBarActivity {
    @Inject
    JokePresenter mJokePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        JokeFragment f = new JokeFragment();
        DaggerJokeComponent.builder()
                .jokePresenterModule(new JokePresenterModule(f))
                .appComponent(((OrzApplication) getApplicationContext()).getAppComponent())
                .build()
                .inject(this);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame, f, "joke")
                .commit();
    }
}
