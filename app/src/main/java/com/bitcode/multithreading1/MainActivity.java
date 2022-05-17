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
            String [] fileUrls = {
                    "https://bitcode.in/files/android.pdf",
                    "https://bitcode.in/files/ios.pdf",
                    "https://bitcode.in/files/web.pdf",
            };
            //new DownloadThread().execute((String) null);
            new DownloadThread().execute(fileUrls);
        }
    }

     private class DownloadThread extends AsyncTask<String, Integer, Float> {

        private ProgressDialog progressDialog;
        //private String fileUrl = "https://bitcode.in/files/file1.pdf";

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
         protected Float doInBackground(String... fileUrls) {

             for(String fileUrl : fileUrls) {

                 progressDialog.setMessage("Downloading: " + fileUrl);

                 for (int i = 0; i <= 100; i++) {
                     //btnDownload.setText(i + "%"); //not allowed, never do this
                     Log.e("tag", fileUrl + " " + i + " % ");

                     Integer [] progress = new Integer[1];
                     progress[0] = i;
                     publishProgress(progress);

                     progressDialog.setProgress(i);
                     try {
                         Thread.sleep(50);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }
                 }
             }

             return 12.12F;
         }


         @Override
         protected void onProgressUpdate(Integer... values) {
             super.onProgressUpdate(values);
             btnDownload.setText("Progress " + values[0] + "%");
         }

         @Override
         protected void onPostExecute(Float result) {
             super.onPostExecute(result);
             progressDialog.dismiss();
             btnDownload.setText("Result: " + result);
         }
     }
}