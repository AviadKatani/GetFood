/**
 * This activity is responsible to supply all the functions to read / write to a file.
 */
package aviadapps.getfood;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by Aviad on 19-06-2017.
 */

public class FilesWorker {
    Context context;

    public FilesWorker(Context context) {
        this.context = context;
    }

    public void writeToFile(String data) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput("history.txt", context.MODE_PRIVATE));
            osw.write(data);
            osw.close();
        }
        catch (IOException e) {
            System.out.println("Exception on reading file: " + e.toString());
        }
    }

    public String readFromFile() {
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

    public boolean fileExists(String filename) {
        File file = context.getFileStreamPath(filename);
        if(file == null || !file.exists()) {
            return false;
        }
        return true;
    }
}
