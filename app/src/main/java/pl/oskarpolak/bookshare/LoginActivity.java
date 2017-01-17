package pl.oskarpolak.bookshare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {



    @BindView(R.id.editTextLogin)
    EditText loginText;

    @BindView(R.id.editTextPassword)
    EditText passwordText;

    @BindView(R.id.checkBoxRemember)
    CheckBox rememberPass;


    private SharedPreferences preferences;
    private SQLiteData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        data = new SQLiteData(this);

        preferences = this.getSharedPreferences("loginData", MODE_PRIVATE);
        loadData();
    }

    @OnClick(R.id.buttonLogin)
    public void doLogin(){
        String login = loginText.getText().toString();
        String password = passwordText.getText().toString();

        // check validation
        if(!(login.trim().length() > 3 && login.trim().length() < 10)) {
            Utils.createSimpleDialog(this, "Logowanie", "Login musi mieścić się w przedziale od 3 do 10 znaków");
            return;
         }
        if(!(password.trim().length() > 5 && password.trim().length() < 10)) {
            Utils.createSimpleDialog(this, "Logowanie", "Hasło musi mieścić się w przedziale od 5 do 10 znaków");
            return;
        }

        if(data.checkPassword(login, password)) {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            saveData();
        }


    }

    private void loadData() {
        loginText.setText(preferences.getString("login", ""));
        passwordText.setText(preferences.getString("password", ""));
        rememberPass.setChecked(preferences.getBoolean("remember", false));
    }

    private void saveData() {
        if(rememberPass.isChecked()){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("login", loginText.getText().toString());
            editor.putString("password", passwordText.getText().toString());
            editor.putBoolean("remember", true);
            editor.commit();
        }
    }

    @OnClick(R.id.buttonMakeRegister)
    public void makeReigster(){
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }


}
