package aviadapps.getfood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
        Company company = companyList.get(1);
        String info = company.toString();
        menuTV.setText("Menu is: " + info);
    }
}
