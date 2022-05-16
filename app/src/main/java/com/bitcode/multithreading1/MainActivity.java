package com.bitcode.multithreading1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btnDownload = findViewById(R.id.btnDownload);

        btnDownload.setOnClickListener(new BtnDownloadClickListener());
    }

    private class BtnDownloadClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //start a thread to download
            new DownloadThread().execute((String) null);
        }
    }

     private class DownloadThread extends AsyncTask<String, Integer, Float> {

        private ProgressDialog progressDialog;

         @Override
         protected void onPreExecute() {
             super.onPreExecute();
             progressDialog = new ProgressDialog(MainActivity.this);
             progressDialog.setTitle("BitCode");
             progressDialog.setMessage("Fetching data..");
             //progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
             progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
             progressDialog.show();
         }

         @Override
         protected Float doInBackground(String... input) {

             for(int i = 0; i <= 100; i++) {
                 Log.e("tag", i + " % ");
                 progressDialog.setProgress(i);
                 try {
                     Thread.sleep(100);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }

             return null;
         }

         @Override
         protected void onPostExecute(Float aFloat) {
             super.onPostExecute(aFloat);
             progressDialog.dismiss();
         }
     }
}