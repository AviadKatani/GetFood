package aviadapps.getfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ConfirmationActivity extends AppCompatActivity {
    Intent orderInfoIntent;
    String fullName, emailAddress, phoneNumber, foodName, fullInfo;
    String[] seperated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        orderInfoIntent = getIntent();
        TextView textView = new TextView(getApplicationContext());
        fullInfo = orderInfoIntent.getStringExtra("companyInfo");
        textView.setText("Please confirm: " + fullInfo);
        seperated = fullInfo.split(" ");
        fullName = seperated[1];
        emailAddress = seperated[3];
        phoneNumber = seperated[5];
        foodName = seperated[7];
        textView.setText(fullName + " space " + emailAddress + " space " + phoneNumber + " space " + foodName);
        setContentView(textView);
        for(int i = 0; i < seperated.length; i++) {
            System.out.println("Debugging: " + i + " is " + seperated[i]);
        }
        System.out.println(fullInfo);
    }
}
