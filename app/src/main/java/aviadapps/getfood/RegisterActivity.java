package aviadapps.getfood;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends Activity {
    EditText userET, emailET, passwordET, phoneET, nameET;
    String user, email, password, name, phone;

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
        if(isEmpty(userET))
            userET.setError("Cannot be empty.");
        else if(isEmpty(nameET))
            nameET.setError("Cannot be empty.");
        else if(isEmpty(emailET) || !isValidEmail(emailET))
            emailET.setError("Not a email address");
        else if(isEmpty(passwordET) || !isValidPassword(passwordET))
            passwordET.setError("Pass should be more than 6 characters");
        else if(isEmpty(phoneET) || !isValidPhone(phoneET))
            phoneET.setError("Phone should be 10 digits");
        else {
            user = userET.getText().toString();
            name = nameET.getText().toString();
            email = emailET.getText().toString();
            password = passwordET.getText().toString();
            phone = phoneET.getText().toString();
            HelperDB db = new HelperDB(this);
            User createUser = new User(0, name, phone, user, password);
            db.addUser(createUser);
            Toast.makeText(this, "Registered", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isValidEmail(EditText email) {
        CharSequence mail = email.getText().toString();
        return android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }

    private boolean isValidPassword(EditText pass) {
        String password = pass.getText().toString();
        return password != null && password.length() >= 6;
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    private boolean isValidPhone(EditText phone) {
        String phoneNum = phone.getText().toString();
        return phoneNum.length() == 10;
    }

}
