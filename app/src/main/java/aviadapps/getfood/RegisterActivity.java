package aviadapps.getfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    EditText userET, emailET, passwordET, phoneET, nameET;
    String user, email, password, name;
    int phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userET = (EditText)findViewById(R.id.userET);
        nameET = (EditText)findViewById(R.id.nameET);
        emailET = (EditText)findViewById(R.id.emailET);
        passwordET = (EditText)findViewById(R.id.passwordET);
        phoneET = (EditText)findViewById(R.id.phoneET);
    }

    public void registerUser(View view) {
        if(!isEmpty(userET))
            user = userET.getText().toString();
        else
            userET.setError("Cannot be empty.");
        if(!isEmpty(nameET))
            name = nameET.getText().toString();
        else
            nameET.setError("Cannot be empty.");
        if(isEmailValid(email))
            email = emailET.getText().toString();
        else
            emailET.setError("Not a email address");
        if(isValidPassword(password))
            password = passwordET.getText().toString();
        else
            passwordET.setError("Pass should be more than 6 characters");

        phone = Integer.parseInt(phoneET.getText().toString());
        // TODO: Check the info provided by user
        HelperDB db = new HelperDB(this);
        User createUser = new User(0, name, phone, user, password);
        db.addUser(createUser);
        Toast.makeText(this, "Registered", Toast.LENGTH_LONG).show();
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 6) {
            return true;
        }
        return false;
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

}
