package pl.oskarpolak.bookshare;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends Activity {

    @BindView(R.id.editLogin)
    EditText editLogin;

    @BindView(R.id.editPassword)
    EditText editPassword;

    @BindView(R.id.editPassword2)
    EditText editRepeatPassword;


    private SQLiteData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        data = new SQLiteData(this);

    }

    @OnClick(R.id.buttonRegister)
    public void register(){
         String login = editLogin.getText().toString();
         String password = editPassword.getText().toString();
         String passwordRepat = editRepeatPassword.getText().toString();

         if(!password.equals(passwordRepat)) {
              Utils.createSimpleDialog(this, "Błąd", "Hasła muszą się zgadzać");
              return;
         }
          if(data.isUserExist(login)) {
              Utils.createSimpleDialog(this, "Błąd", "Taki użytkownik już istnieje");
              return;
          }else{
              data.addUser(login, password);
          }

    }
}
