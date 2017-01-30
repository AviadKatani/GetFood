package aviadapps.getfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;

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
        user = userET.getText().toString();
        name = nameET.getText().toString();
        email = emailET.getText().toString();
        password = passwordET.getText().toString();
        phone = Integer.parseInt(phoneET.getText().toString());
        // TODO: Check the info provided by user
        HelperDB db = new HelperDB(this);
        User createUser = new User(name, phone, user, password);
        db.addUser(createUser);
    }
}
