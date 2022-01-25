package com.example.gelatintalk;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    Button login_btn;
    EditText login_id, login_password;
    TextView text_error, login_findID, login_findPassword, login_join;
    ImageButton image_idX, image_passX;

    Boolean id = false;
    String loginID, loginPass;
    private TextWatcher idTw, passwordTw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_id = (EditText)findViewById(R.id.loginID);
        login_password = (EditText)findViewById(R.id.loginPassword);
        text_error = (TextView)findViewById(R.id.textError);
        login_findID = (TextView)findViewById(R.id.findID);
        login_findPassword = (TextView)findViewById(R.id.findPassword);
        login_join = (TextView)findViewById(R.id.loginJoin);
        login_btn = (Button)findViewById(R.id.btnLogin);
        image_idX = (ImageButton)findViewById(R.id.imageIdX);
        image_passX = (ImageButton)findViewById(R.id.imagePassX);

        idTw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                loginID = login_id.getText().toString();
                login_id.removeTextChangedListener(idTw);
                if(loginID.length()>0){
                    id = true;
                    image_idX.setVisibility(View.VISIBLE);
                }else{
                    id = false;
                }
                login_id.addTextChangedListener(idTw);
                image_idX.setVisibility(View.INVISIBLE);
            }
        };

        passwordTw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                loginPass = login_password.getText().toString();
                login_password.removeTextChangedListener(passwordTw);

                //x이미지 버튼 사용
                if(loginPass.length()>0){
                    image_passX.setVisibility(View.VISIBLE);
                }else{
                    image_passX.setVisibility(View.INVISIBLE);
                }

                if(id&loginPass.length()>4){
                    login_btn.setEnabled(true);
                }else{
                    login_btn.setEnabled(false);
                }
                login_password.addTextChangedListener(passwordTw);

            }
        };

        login_id.addTextChangedListener(idTw);
        login_password.addTextChangedListener(passwordTw);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loginID.equals("wlan12")|loginPass.equals("a1234")){
                    SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);

                    //아이디가 'wlan12'이고 비밀번호가 'a1234'일 경우 SharedPreferences.Editor를 통해
                    //auto의 loginID와 loginPass애 값을 저장해 줌.
                    SharedPreferences.Editor autoLogin = auto.edit();
                    autoLogin.putString("inputID", loginID);
                    autoLogin.putString("inputPass", loginPass);

                    //commit()으로 값 저장
                    autoLogin.commit();

                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    text_error.setText("계정 또는 비밀번호를 다시 확인해주세요.");
                }
            }
        });
    }

    public void removeID(View v){
        login_id.setText("");
    }
    public void removePass(View v){
        login_password.setText("");
    }
}