package com.bitcode.multithreading1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DemoActivity extends AppCompatActivity {

    private Button btnDownload;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo_activity);

        btnDownload = findViewById(R.id.btnDownload);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] fileUrls = {
                        "file1",
                        "file2",
                        "file3"
                };

                new Downloader(DemoActivity.this, new MyHandler())
                        .execute(fileUrls);
            }
        });
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg == null || msg.obj == null) {
                return;
            }
            if(msg.what == 1) {
                Integer[] progress = (Integer[]) msg.obj;
                btnDownload.setText("Progress: " + progress[0] + "%");
            }
            if(msg.what == 2) {
                Float result = (Float) msg.obj;
                btnDownload.setText("Result: " + result);

            }

        }
    }
}
