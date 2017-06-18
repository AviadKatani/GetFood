/**
 * This activity retrieves the History data from the database and display it to the user
 */
package aviadapps.getfood;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class HistoryActivity extends AppCompatActivity {
    HelperDB db;
    String History;
    String userName;
    File path, file;
    //Context context = getApplicationContext();

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
        //path = context.getFilesDir();
        //trfile = new File(path, "history.txt");
    }

    // For future development -- save and read history from FILE.
    /*
    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("history.txt", Context.MODE_PRIVATE));
            osw.write(data);
            osw.close();
        }
        catch (IOException e) {
            System.out.println("Exception on reading file: " + e.toString());
        }
    }

    private String readFromFile(Context context) {
        String ans = "";
        try {
            InputStream inputStream = context.openFileInput("history.txt");
            if(ans != null) {
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(isr);
                String receive = "";
                StringBuilder stringBuilder = new StringBuilder();
                while((receive = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receive);
                }
                inputStream.close();
                ans = stringBuilder.toString();
            }
        } catch(FileNotFoundException e) {
            System.out.println("History Activity, File not found: " + e.toString());
        } catch(IOException e) {
            System.out.println("History Activity, Cannot read file: " + e.toString());
        }
        return ans;
    }
    */
}
