package net.vsona.orz.ui;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import net.vsona.orz.R;


/**
 * Created by roy on 16/6/3.
 */
public class BaseToolBarActivity extends BaseAppCompatActivity {

    protected void initToolbar() {
        Toolbar mToolbarView = (Toolbar) findViewById(R.id.roy_toolbar);
        setSupportActionBar(mToolbarView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
