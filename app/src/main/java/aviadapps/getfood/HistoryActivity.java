package aviadapps.getfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class HistoryActivity extends AppCompatActivity {
    HelperDB db;
    String History;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        userName = getIntent().getStringExtra("userName");
        System.out.println(userName);
        db = new HelperDB(this);
        TextView tv = new TextView(this);
        History = db.getHistory(userName);
        tv.setText(History);
        setContentView(tv);
    }
}
