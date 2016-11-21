package ch.cpln.loginmysqlapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_actvity);
    }


    public void btnLogonClick(View view) {
        EditText tbxEmail = (EditText) findViewById(R.id.tbxEmail);
        EditText tbxPassword = (EditText) findViewById(R.id.tbxPassword);

        Toast.makeText(getApplicationContext(), tbxEmail.getText() + " " + tbxPassword.getText(), Toast.LENGTH_LONG).show();
    }
}
