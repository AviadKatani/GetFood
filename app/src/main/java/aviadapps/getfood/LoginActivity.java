package aviadapps.getfood;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LoginActivity extends Activity {
    HelperDB db = new HelperDB(this);
    EditText userName, userPassword;
    private String user, pass, forget_Pass = "";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        userName = (EditText) findViewById(R.id.etUsername);
        userPassword = (EditText) findViewById(R.id.etUserpass);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void loginClicked(View view) {
        if(isEmpty(userName))
            userName.setError("Cannot be empty");
        else if(isEmpty(userPassword) || !isValidPassword(userPassword))
            userPassword.setError("Not a valid password");
        else {
            user = userName.getText().toString();
            pass = userPassword.getText().toString();

            String password = db.searchPass(user);
            if (pass.equals(password)) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.putExtra("Username", user);
                startActivity(i);
            } else {
                Toast.makeText(this, "Username and password doesn't match!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void forgetPassword(View view) {
        showDialog();
    }

    public void moveToRegister(View view) {
        // TODO: Move to a fragment / Activity that asks user for personal details and then send a database query to register new user.
        Intent moveIntent = new Intent(this, RegisterActivity.class);
        startActivity(moveIntent);
    }

    private boolean isValidPassword(EditText pass) {
        String password = pass.getText().toString();
        return password != null && password.length() >= 6;
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    private boolean isValidEmail(EditText email) {
        CharSequence mail = email.getText().toString();
        return android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches();
    }

    public void showDialog()
    {
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        new AlertDialog.Builder(this)
                .setView(input)
                .setTitle("Forget password")
                .setMessage("Please enter your email below:")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(!isEmpty(input) && isValidEmail(input)) {
                            forget_Pass = input.getText().toString();
                            String password = db.getPassFromEmail(forget_Pass);
                            if(password.equals("Not found")) {
                                Toast.makeText(LoginActivity.this, "Couldn't find user with this mail: " + forget_Pass, Toast.LENGTH_LONG).show();
                            }
                            else {
                                sendEmail send = new sendEmail();
                                send.execute(forget_Pass, password);
                                Toast.makeText(LoginActivity.this, "Thanks, password will be send to: " + forget_Pass, Toast.LENGTH_LONG).show();
                            }
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Please enter a valid email", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(LoginActivity.this, "Canceled", Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public class sendEmail extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(String... params) {
            String urlAddress = "https://api.elasticemail.com/v2/email/send", apiKey = "757b3598-2532-4286-94e3-d7d42247a390", subject = "GetFood - Your password",
                    from = "aviadkatani@gmail.com", encoding = "UTF-8", to = params[0], body = "Your current password is:  " + params[1];
            HttpURLConnection urlConnection = null;
            try {
                String data = "apikey=" + URLEncoder.encode(apiKey, encoding);
                data += "&from=" + URLEncoder.encode(from, encoding);
                data += "&fromName=" + URLEncoder.encode("GetFood", encoding);
                data += "&subject=" + URLEncoder.encode(subject, encoding);
                data += "&bodyHtml=" + URLEncoder.encode(body, encoding);
                data += "&to=" + URLEncoder.encode(to, encoding);
                URL url = new URL(urlAddress);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
                wr.write(data);
                wr.flush();
                BufferedReader rd = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String result = rd.readLine();
                wr.close();
                rd.close();
                Log.e("LoginActivity", "URL Connected: " + urlAddress + "Data is: " + data);
                return result;
            } catch(IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.GONE);
        }
    }
}
