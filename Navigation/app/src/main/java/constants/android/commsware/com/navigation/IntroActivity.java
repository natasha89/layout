package constants.android.commsware.com.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by yong on 15. 2. 2.
 */
public class IntroActivity extends Activity{

    Button next_btn;
    boolean flag = false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Logo Activity
        reLoadLogo();
        setContentView(R.layout.activity_intro);

        next_btn = (Button) findViewById(R.id.next_btn);
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    void reLoadLogo(){

        if(!flag){
            startActivity(new Intent(this, LogoActivity.class));
            flag = true;
        }

    }


}
