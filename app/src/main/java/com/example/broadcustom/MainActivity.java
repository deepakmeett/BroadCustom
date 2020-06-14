package com.example.broadcustom;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        textView = findViewById( R.id.text );
        button = findViewById( R.id.buttonPanel );
        
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( "com.example.EXAMPLE_ACTION" );
                intent.putExtra( "com.example.EXTRA_TEXT", "Broadcast Received" );
                sendBroadcast( intent );
            }
        } );
    }
    
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String receivedText = intent.getStringExtra( "com.example.EXTRA_TEXT" );
            textView.setText( receivedText );
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter( "com.example.EXAMPLE_ACTION" );
        registerReceiver( broadcastReceiver, filter );
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver( broadcastReceiver );
    }
}
