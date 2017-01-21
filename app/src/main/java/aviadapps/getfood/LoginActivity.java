package aviadapps.getfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void loginClicked(View view) {
        // TODO: Parse user info, check it on DB.
    }

    public void forgetPassword(View view) {
        // TODO: Move to a fragment / Activity that sends the user info to email.
    }

    public void registerUser(View view) {
        // TODO: Move to a fragment / Activity that asks user for personal details and then send a database query to register new user.
    }
}
