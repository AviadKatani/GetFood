package aviadapps.getfood;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LoginActivity extends Activity {
    HelperDB db = new HelperDB(this);
    EditText userName, userPassword;
    private String user, pass, forget_Pass = "";
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        userName = (EditText) findViewById(R.id.etUsername);
        userPassword = (EditText) findViewById(R.id.etUserpass);
        layout = (LinearLayout)findViewById(R.id.mainLayout);
    }

    public void loginClicked(View view) {
        if(isEmpty(userName))
            userName.setError("Cannot be empty");
        else if(isEmpty(userPassword) || !isValidPassword(userPassword))
            userPassword.setError("Not a valid password");
        else {
            user = userName.getText().toString();
            pass = userPassword.getText().toString();

            String password = db.searchPass(user);
            if (pass.equals(password)) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("Username", user);
                startActivity(i);
            } else {
                Toast.makeText(this, "Username and password doesn't match!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void forgetPassword(View view) {
        showDialog();
    }

    public void moveToRegister(View view) {
        // TODO: Move to a fragment / Activity that asks user for personal details and then send a database query to register new user.
        Intent moveIntent = new Intent(this, RegisterActivity.class);
        startActivity(moveIntent);
    }

    private boolean isValidPassword(EditText pass) {
        String password = pass.getText().toString();
        return password != null && password.length() >= 6;
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    private boolean isValidEmail(EditText email) {
        CharSequence mail = email.getText().toString();
        return android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }

    public void showDialog()
    {
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        new AlertDialog.Builder(this)
                .setView(input)
                .setTitle("Forget password")
                .setMessage("Please enter your email below:")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(!isEmpty(input) && isValidEmail(input)) {
                            forget_Pass = input.getText().toString();
                            Toast.makeText(LoginActivity.this, "Thanks, password will be send to: " + forget_Pass, Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Please enter a valid email", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(LoginActivity.this, "Canceled", Toast.LENGTH_LONG).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    // TODO: Write an email send function! :)
}
