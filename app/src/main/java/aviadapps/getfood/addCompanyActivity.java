package aviadapps.getfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class addCompanyActivity extends AppCompatActivity {
    String userName, userPassword, userPhone;
    EditText nameET, passET, phoneET, addressET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcompany);

        Intent getIntent = getIntent();
        userName = getIntent.getStringExtra("Username");
        nameET = (EditText) findViewById(R.id.settingName);
        // passET = (EditText) findViewById(R.id.settingPass);
        phoneET = (EditText) findViewById(R.id.settingPhone);
    }
}
