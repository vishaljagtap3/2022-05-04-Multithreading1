package com.bitcode.multithreading1;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

public class Downloader extends AsyncTask<String, Integer, Float> {

    private ProgressDialog progressDialog;
    private Context context;
    private Handler handler;

    public Downloader(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
    }

    @Override
    protected Float doInBackground(String... fileUrls) {

        for(String fileUrl : fileUrls) {
            for(int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Integer [] progress = new Integer[1];
                progress[0] = i;
                publishProgress(progress);
            }
        }

        return 12.12F;
    }


    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);

        Message message = new Message();
        message.what = 1;
        message.obj = progress;

        handler.sendMessage(message);
    }

    @Override
    protected void onPostExecute(Float result) {
        super.onPostExecute(result);
        progressDialog.dismiss();

        Message message = new Message();
        message.what = 2;
        message.obj = result;

        handler.sendMessage(message);
    }
}
