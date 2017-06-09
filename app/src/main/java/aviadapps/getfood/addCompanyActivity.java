package aviadapps.getfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class addCompanyActivity extends AppCompatActivity {
    String companyName, companyEmail, companyPhone, companyMenu, companyAddress;
    EditText nameET, phoneET, emailET, menuET, addressET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcompany);

        nameET = (EditText) findViewById(R.id.nameET2);
        addressET = (EditText) findViewById(R.id.addressET2);
        phoneET = (EditText) findViewById(R.id.phoneET2);
        emailET = (EditText) findViewById(R.id.emailET2);
        menuET = (EditText) findViewById(R.id.menuET2);
    }

    public void addCompany(View view) {
        if(!isValidEmail(emailET) || isEmpty(emailET))
            emailET.setError("Not a email address");
        else if(!isValidPhone(phoneET))
            phoneET.setError("Not a phone number");
        else if(isEmpty(nameET))
            nameET.setError("Cannot be empty");
        else if(isEmpty(menuET))
            menuET.setError("Cannot be empty");
        else if(isEmpty(addressET))
            addressET.setError("Cannot be empty");
        else {
            companyName = nameET.getText().toString();
            companyPhone = phoneET.getText().toString();
            companyEmail = emailET.getText().toString();
            companyMenu = menuET.getText().toString();
            companyAddress = menuET.getText().toString();
            Company company = new Company(0, companyName, companyPhone, companyEmail, companyAddress, companyMenu);
            HelperDB db = new HelperDB(this);
            db.addCompany(company);
        }
    }

    private boolean isValidEmail(EditText email) {
        CharSequence mail = email.getText().toString();
        return android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    private boolean isValidPhone(EditText phone) {
        String phoneNum = phone.getText().toString();
        return phoneNum.length() == 10;
    }
}
