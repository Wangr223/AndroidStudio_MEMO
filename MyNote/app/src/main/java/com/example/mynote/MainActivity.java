package com.example.mynote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.security.identity.CipherSuiteNotSupportedException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Ticker> tkers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Connector.getDatabase();
        Button CN = findViewById(R.id.create_note);
        int created;

        initNotes();
        RecyclerView recyclerView = findViewById(R.id.note_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        TickerAdapter adapter = new TickerAdapter(tkers,this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(MyItemClickListener);

        Intent intent = getIntent();
        created = intent.getIntExtra("result", 0);
            if (created == 1)
                Toast.makeText(MainActivity.this, "Mark Successfully",Toast.LENGTH_SHORT).show();
        CN.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,editor.class);
                startActivity(intent);
            }
        });
    }

    private void initNotes() {
        tkers = LitePal.findAll(Ticker.class);
    }

    private TickerAdapter.OnItemClickListener MyItemClickListener = new TickerAdapter.OnItemClickListener(){
        @Override
        public void onItemClick(View v, int position) {
            Ticker ticker = tkers.get(position);
            String check_content = ticker.getContent();
            String check_time = ticker.getTime();
            LitePal.deleteAll(Ticker.class, "content = ? and time = ?", check_content, check_time);
            Intent intent = new Intent(MainActivity.this,MainActivity.class);
            startActivity(intent);
            MainActivity.this.finish();

        }
    };
}