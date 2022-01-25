package com.example.gelatintalk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton imgBtn_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgBtn_setting = (ImageButton)findViewById(R.id.imgBtnSetting);
        imgBtn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popFriend = new PopupMenu(getApplicationContext(),view);
                getMenuInflater().inflate(R.menu.menu_setting,popFriend.getMenu());

                popFriend.show();//popupMenu 보이기
                popFriend.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.editFriend:
                                Toast.makeText(getApplicationContext(), "편집", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.managerFriend:
                                Toast.makeText(getApplicationContext(), "친구 관리", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.settingFriend:
                                Toast.makeText(getApplicationContext(), "전체 설정", Toast.LENGTH_SHORT).show();
                                return true;
                        }
                        return false;
                    }
                });
            }
        });
    }

}