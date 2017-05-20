package aviadapps.getfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    TextView menuTV;
    HelperDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        db = new HelperDB(this);
        menuTV = (TextView) findViewById(R.id.menuTV);

        List<Company> companyList = db.getAllCompanies();
        ArrayList arrayList = new ArrayList();
        for(int i = 0; i < companyList.size(); i++) {
            arrayList.add(companyList.get(i));
        }
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent b = new Intent(OrderActivity.this, ConfirmationActivity.class);
                b.putExtra("companyInfo", adapterView.getItemAtPosition(i).toString());
                startActivity(b);
            }
        });

    }
}
