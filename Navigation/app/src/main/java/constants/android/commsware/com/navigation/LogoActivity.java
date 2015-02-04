package constants.android.commsware.com.navigation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by yong on 15. 2. 2.
 */
public class LogoActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, 1000);

    }
}
