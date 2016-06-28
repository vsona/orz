package net.vsona.orz.ui.splash;

import android.os.Handler;

import javax.inject.Inject;


/**
 * Created by roy on 16/6/24.
 */
public class SplashPrensenter implements SplashContract.Presenter {

    private final SplashContract.View mView;
    private final long minTime = 1888l;
    private long startTime;
    private long endTime;
    //执行操作数
    int surplus = 1;

    @Inject
    public SplashPrensenter(SplashContract.View view){
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        startTime = System.currentTimeMillis();
        delayEnter();
    }

    private void delayEnter() {
        surplus--;
        if (surplus > 0) {
            return;
        }
        endTime = System.currentTimeMillis();
        long sleepTime = minTime - (endTime - startTime);

        handler.postDelayed(r, sleepTime);
    }

    Handler handler = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            if (mView == null) {
                return;
            }
            enter();
        }
    };

    private void enter() {
            mView.enterMainActivity();
    }
}
