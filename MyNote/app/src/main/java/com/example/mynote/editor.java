package com.example.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.tablemanager.Connector;

import java.text.SimpleDateFormat;
import java.util.Date;

public class editor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        Button FR = findViewById(R.id.finish_edit);
        final EditText EC = findViewById(R.id.ed_edit);
        FR.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String content = EC.getText().toString();
                //int id = EC.getId();
                EC.setText("");
                int mark = 0;
                if( !content.equals("")) {
                    Ticker tker = new Ticker();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                    Date date = new Date(System.currentTimeMillis());
                    String time =  simpleDateFormat.format(date);
                    mark = 1;
                    //Log.d("Editor", content);
                    //Log.d("Editor", time);
                    tker.setContent(content);
                    tker.setTime(time);
                    //tker.setId(id);
                    tker.save();
                }
                Intent intent = new Intent(editor.this,MainActivity.class);
                intent.putExtra("result", mark);
                startActivity(intent);
                editor.this.finish();
            }
        });
    }
}