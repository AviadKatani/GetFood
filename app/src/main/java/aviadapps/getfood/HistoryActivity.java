/**
 * This activity retrieves the History data from the database and display it to the user
 */
package aviadapps.getfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;

public class HistoryActivity extends AppCompatActivity {
    HelperDB db;
    String History;
    ListView historyList;
    String userName;
    String[] splitHistory;
    FilesWorker filesWorker;
    //Context context = getApplicationContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        historyList = (ListView) findViewById(R.id.historyView);
        filesWorker = new FilesWorker(this);
        userName = getIntent().getStringExtra("userName");
        System.out.println(userName);
        db = new HelperDB(this);
        //History = db.getHistory(userName);
        History = filesWorker.readFromFile();
        splitHistory = History.split(">>");
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, splitHistory);
        historyList.setAdapter(adapter);
    }

    // For future development -- save and read history from FILE.
}
