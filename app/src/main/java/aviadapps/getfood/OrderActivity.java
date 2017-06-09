package aviadapps.getfood;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class OrderActivity extends AppCompatActivity {
    TextView menuTV;
    HelperDB db = new HelperDB(this);
    String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        userName = getIntent().getStringExtra("userName");
        db = new HelperDB(this);
        ListView list = (ListView) findViewById(R.id.listView);
        menuTV = (TextView) findViewById(R.id.tvGetFood);
        List<Company> companyList = db.getAllCompanies();

        companyAdapter adapter = new companyAdapter(getApplicationContext(), R.layout.row, companyList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent b = new Intent(OrderActivity.this, ConfirmationActivity.class);
                b.putExtra("companyInfo", adapterView.getItemAtPosition(i).toString());
                b.putExtra("userName", userName);
                startActivity(b);
            }
        });
    }

    public class companyAdapter extends ArrayAdapter {
        private List<Company> companyList;
        private int resource;
        private LayoutInflater inflater;
        public companyAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List objects) {
            super(context, resource, objects);
            companyList = objects;
            this.resource = resource;
            inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);


        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) convertView = inflater.inflate(R.layout.row, null);
            ImageView ivCompany;
            String companyName = companyList.get(position).getName();
            TextView tvCompany, tvTagline, tvAddress, tvPhone;
            RatingBar rbCompany = (RatingBar) convertView.findViewById(R.id.rbCompany);
            rbCompany.setRating(3);
            tvCompany = (TextView) convertView.findViewById(R.id.tvCompany);
            ivCompany = (ImageView) convertView.findViewById(R.id.companyLogo);
            tvAddress = (TextView) convertView.findViewById(R.id.tvAddress);
            tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);
            tvTagline = (TextView) convertView.findViewById(R.id.tvTagline);

            tvCompany.setText(companyName);
            tvTagline.setText("Kosher");
            tvAddress.setText(companyList.get(position).getAddress());
            tvPhone.setText(companyList.get(position).getPhone());
            switch(companyName) {
                case "Pizza Hut":
                    ivCompany.setImageDrawable(getDrawable(R.drawable.pizzahut));
                    break;
                case "Aroma":
                    ivCompany.setImageDrawable(getDrawable(R.drawable.aroma));
                    break;
                case "McDonalds":
                    ivCompany.setImageDrawable(getDrawable(R.drawable.mcdonalds));
                    break;
                case "Dominos":
                    ivCompany.setImageDrawable(getDrawable(R.drawable.dominos));
                    break;
            }
            return convertView;
    }
}
}
