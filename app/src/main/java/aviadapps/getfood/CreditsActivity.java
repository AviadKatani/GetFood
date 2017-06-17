/**
 * This activity contains the information about the people who contributed to the development of this app.
 */
package aviadapps.getfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CreditsActivity extends AppCompatActivity {
    TextView creditTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        creditTV = (TextView) findViewById(R.id.creditsTV);

        creditTV.setText("Teacher: Albert Levi and Itsik Ben Shitrit" + "\n" +
                "Developed by: Aviad Katani" + "\n" +
        "Thanks to all Cyber Class for a great 3 years! :)");
    }
}
