package net.vsona.orz.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import net.vsona.orz.OrzApplication;
import net.vsona.orz.R;
import net.vsona.orz.ui.joke.DaggerJokeComponent;
import net.vsona.orz.ui.joke.JokeFragment;
import net.vsona.orz.ui.joke.JokePresenter;
import net.vsona.orz.ui.joke.JokePresenterModule;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by roy on 16/6/28.
 */
public class MainActivity extends BaseToolBarActivity {
    @Inject
    JokePresenter mJokePresenter;

    @Bind(android.R.id.tabs)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.roy_toolbar)
    Toolbar toolBar;
    private JokeFragment mJokeFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        mJokeFragment = new JokeFragment();
        DaggerJokeComponent.builder()
                .jokePresenterModule(new JokePresenterModule(mJokeFragment))
                .appComponent(((OrzApplication) getApplicationContext()).getAppComponent())
                .build()
                .inject(this);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return mJokeFragment;
                    default:
                        return mJokeFragment;
                }
            }

            @Override
            public int getCount() {
                return 1;
            }
        });


//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.frame, mJokeFragment, "joke")
//                .commit();
        tabLayout.setupWithViewPager(viewPager);
    }
}
