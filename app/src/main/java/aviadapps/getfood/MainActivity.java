/**
 * This is the main activity which lets the user choose the wanted action.
 */
package aviadapps.getfood;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    TextView textLog;
    String userName;
    Intent i;
    FilesWorker filesWorker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filesWorker = new FilesWorker(this);
        textLog = (TextView) findViewById(R.id.textLog);
        userName = getIntent().getStringExtra("userName");
        textLog.setText("You're logged in, " + userName + "!");
        if(!filesWorker.fileExists("history.txt")) {
            try {
                FileOutputStream fOut = openFileOutput("history.txt", MODE_PRIVATE);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fOut);
                outputStreamWriter.write("");
                outputStreamWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_main) {
            i = new Intent(this, MainActivity.class);
        }

        else if(id == R.id.action_menu) {
            i = new Intent(this, OrderActivity.class);
        }

        else if(id == R.id.action_history) {
            i = new Intent(this, HistoryActivity.class);
        }

        else if(id == R.id.action_addcompany) {
            i = new Intent(this, addCompanyActivity.class);
        }
        else if(id == R.id.action_credits) {
            i = new Intent(this, CreditsActivity.class);
        }
        else {
            i = new Intent(this, MainActivity.class);
        }
        i.putExtra("userName", userName);
        startActivity(i);

        return true;
    }

    public void orderClicked(View view) {
        i = new Intent(this, OrderActivity.class);
        i.putExtra("userName", userName);
        startActivity(i);
    }

    public void historyClicked(View view) {
        i = new Intent(this, HistoryActivity.class);
        i.putExtra("userName", userName);
        startActivity(i);
    }

    public void helpClicked(View view) {
        sendEmail();
    }

    protected void sendEmail() {
        Log.i("Send email", "");

        String[] TO = {"someone@gmail.com"};
        String[] CC = {"xyz@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Please enter your subject here");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "How can we help you?");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email.", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
