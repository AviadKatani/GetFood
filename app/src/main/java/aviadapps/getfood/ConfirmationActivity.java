/**
 * This activity contains the full order information just before proccessing the order.
 */
package aviadapps.getfood;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

public class ConfirmationActivity extends AppCompatActivity {
    Intent orderInfoIntent;
    TextView confTV;
    FilesWorker filesWorker;
    ListView menuList;
    String fullName, emailAddress, phoneNumber, foodName, fullInfo, userName, itemChoosed;
    String[] seperatedInfo, seperatedMenu;
    HelperDB db;
    boolean isItemChoosed;
    Calendar c;
    SimpleDateFormat df;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        c = Calendar.getInstance();
        df = new SimpleDateFormat("dd/MM/yyyy");
        filesWorker = new FilesWorker(this);
        isItemChoosed = false;
        confTV = (TextView) findViewById(R.id.confTV);
        menuList = (ListView) findViewById(R.id.menuList);
        userName = getIntent().getStringExtra("userName");
        fullInfo = getIntent().getStringExtra("companyInfo");
        seperatedInfo = fullInfo.split(Pattern.quote("|"));
        v = new View(this);
        fullName = seperatedInfo[0];
        emailAddress = seperatedInfo[1];
        phoneNumber = seperatedInfo[2];
        foodName = seperatedInfo[3];
        seperatedMenu = foodName.split(",");
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, seperatedMenu);
        menuList.setAdapter(adapter);
        //textView.setText(fullName + " space " + emailAddress + " space " + phoneNumber + " space " + foodName);
        confTV.setText("Name: " + fullName + "\n" + "Email: " + emailAddress + "\n" + "Phone: " + phoneNumber + "\n" + "Please choose one of the menu items: ");
        System.out.println("Full info is: " + fullInfo);
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                v.setBackgroundResource(0);
                view.setBackgroundResource(R.color.green);
                v = view;
                itemChoosed = adapterView.getItemAtPosition(position).toString();
                isItemChoosed = true;
            }
        });
    }

    public void orderComplete(View view) {
        //db = new HelperDB(this);
        if(isItemChoosed) {
            Toast.makeText(this, "Thanks, your purchase has been made!", Toast.LENGTH_SHORT).show();
            String currentHistory = filesWorker.readFromFile();
            fullInfo = "Ordered by: " + userName + " | " + "Ordered on: " + df.format(c.getTime()) + " Ordered from: " + fullName + " | " + "Company Email: " + emailAddress + " | " + "Company Phone: " + phoneNumber + ">>";
            currentHistory += "\n" + fullInfo;
            filesWorker.writeToFile(currentHistory);
            //db.addHistory(fullInfo, userName);
        }
        else {
            Toast.makeText(this, "Please choose item from the menu!", Toast.LENGTH_LONG).show();
        }
    }

    public void cancelOrder(View view) {
        Toast.makeText(this, "Your order has been cancelled.", Toast.LENGTH_SHORT).show();
        orderInfoIntent = new Intent(ConfirmationActivity.this, OrderActivity.class);
        startActivity(orderInfoIntent);
    }


}
