package aviadapps.getfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ConfirmationActivity extends AppCompatActivity {
    Intent orderInfoIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        orderInfoIntent = getIntent();
        TextView textView = new TextView(getApplicationContext());
        textView.setText("Please confirm: " + orderInfoIntent.getStringExtra("companyInfo"));
        setContentView(textView);
    }
}
