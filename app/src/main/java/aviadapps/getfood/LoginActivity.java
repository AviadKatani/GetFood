package aviadapps.getfood;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void fragmentChange(View v) {
        Fragment fragment = null;
        if(v == findViewById(R.id.button)) {
            fragment = new userFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentPlace, fragment);
            ft.commit();

        }
        else if(v == findViewById(R.id.button2)) {
            fragment = new companyFragment();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentPlace, fragment);
            ft.commit();
        }
    }
}
