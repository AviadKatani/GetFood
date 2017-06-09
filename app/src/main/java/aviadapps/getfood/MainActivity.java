package aviadapps.getfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textLog;
    String userName;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textLog = (TextView) findViewById(R.id.textLog);
        userName = getIntent().getStringExtra("userName");
        textLog.setText("You're logged in, " + userName + "!");
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
            i = new Intent(this, MainActivity.class);
        }

        else if(id == R.id.action_history) {
            i = new Intent(this, HistoryActivity.class);
        }

        else if(id == R.id.action_addcompany) {
            i = new Intent(this, addCompanyActivity.class);
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
        // TODO: Move to order activity
    }


    public void historyClicked(View view) {
        i = new Intent(this, HistoryActivity.class);
        i.putExtra("userName", userName);
        startActivity(i);
        // TODO: Move to order history activity
    }


    public void helpClicked(View view) {
        // TODO: Move to help activity
    }
}
