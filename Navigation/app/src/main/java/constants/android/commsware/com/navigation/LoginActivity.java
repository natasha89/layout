package constants.android.commsware.com.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by yong on 15. 2. 2.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    Button facebook_btn;
    Button googleplus_btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Logo Activity
        //startActivity(new Intent(this, LogoActivity.class));

        facebook_btn = (Button) findViewById(R.id.login_button);
        googleplus_btn = (Button) findViewById(R.id.login_button1);
        facebook_btn.setOnClickListener(this);
        googleplus_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent;
        switch (id) {
            case R.id.login_button:
                Toast.makeText(getApplicationContext(), "Google Plus Login", Toast.LENGTH_SHORT).show();
                intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.login_button1:
                Toast.makeText(getApplicationContext(), "FaceBook Login", Toast.LENGTH_SHORT).show();
                intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
