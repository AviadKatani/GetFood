package aviadapps.getfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

public class SettingsActivity extends AppCompatActivity {
    String userName, userPassword, userPhone;
    EditText nameET, passET, phoneET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent getIntent = getIntent();
        userName = getIntent.getStringExtra("Username");
        nameET = (EditText) findViewById(R.id.settingName);
        passET = (EditText) findViewById(R.id.settingPass);
        phoneET = (EditText) findViewById(R.id.settingPhone);
    }
}
