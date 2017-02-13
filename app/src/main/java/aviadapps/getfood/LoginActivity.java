package aviadapps.getfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    HelperDB db = new HelperDB(this);
    EditText userName, userPassword;
    String user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        userName = (EditText) findViewById(R.id.etUsername);
        userPassword = (EditText) findViewById(R.id.etUserpass);
    }

    public void loginClicked(View view) {
        user = userName.getText().toString();
        pass = userPassword.getText().toString();

        String password = db.searchPass(user);
        if(pass.equals(password)) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            i.putExtra("Username", user);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "Username and password doesn't match!", Toast.LENGTH_LONG).show();
        }

        // TODO: Parse user info, check it on DB.
    }

    public void forgetPassword(View view) {
        // TODO: Move to a fragment / Activity that sends the user info to email.
    }

    public void moveToRegister(View view) {
        // TODO: Move to a fragment / Activity that asks user for personal details and then send a database query to register new user.
        Intent moveIntent = new Intent(this, RegisterActivity.class);
        startActivity(moveIntent);
    }
}
