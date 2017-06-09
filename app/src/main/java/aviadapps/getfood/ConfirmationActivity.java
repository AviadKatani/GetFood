package aviadapps.getfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ConfirmationActivity extends AppCompatActivity {
    Intent orderInfoIntent;
    TextView confTV;
    String fullName, emailAddress, phoneNumber, foodName, fullInfo, userName;
    String[] seperated;
    HelperDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        confTV = (TextView) findViewById(R.id.confTV);
        userName = getIntent().getStringExtra("userName");
        fullInfo = getIntent().getStringExtra("companyInfo");
        seperated = fullInfo.split(Pattern.quote("|"));
        fullName = seperated[0];
        emailAddress = seperated[1];
        phoneNumber = seperated[2];
        foodName = seperated[3];
        //textView.setText(fullName + " space " + emailAddress + " space " + phoneNumber + " space " + foodName);
        confTV.setText("Name: " + fullName + "\n" + "Email: " + emailAddress + "\n" + "Phone: " + phoneNumber + "\n" + "Food Ordered: " + foodName);
        System.out.println("Full info is: " + fullInfo);
    }

    public void orderComplete(View view) {
        db = new HelperDB(this);
        Toast.makeText(this, "Thanks, your purchase has been made!", Toast.LENGTH_SHORT).show();
        System.out.println("Second before calling userName" + userName);
        db.addHistory(fullInfo, userName);
    }

    public void cancelOrder(View view) {
        Toast.makeText(this, "Your order has been cancelled.", Toast.LENGTH_SHORT).show();
        orderInfoIntent = new Intent(ConfirmationActivity.this, OrderActivity.class);
        startActivity(orderInfoIntent);
    }
}
