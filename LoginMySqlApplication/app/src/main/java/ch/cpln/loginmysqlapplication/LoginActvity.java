package ch.cpln.loginmysqlapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class LoginActvity extends AppCompatActivity {
    EditText tbxEmail;
    EditText tbxPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_actvity);

        tbxEmail = (EditText) findViewById(R.id.tbxEmail);
        tbxPassword = (EditText) findViewById(R.id.tbxPassword);
    }


    public void btnLogonClick(View view) {
        String email = tbxEmail.getText().toString();
        String password = tbxPassword.getText().toString();
        String type = "login";

        SigninActivity signinActivity = new SigninActivity(this);
        signinActivity.execute(type, email, password);


        //Toast.makeText(getApplicationContext(), tbxEmail.getText() + " " + tbxPassword.getText(), Toast.LENGTH_LONG).show();
    }


}
