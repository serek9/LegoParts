package com.example.dam.legoparts;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class PartsDownloader extends AsyncTask<Void, String, Boolean> {

    private Context context;
    public PartsDownloader(Context context){ this.context = context;}

    private OnPartsLoadedListener listener = null;
    public void setOnPartsLoadedListener(OnPartsLoadedListener listener){
        this.listener = listener;
    }

    private ProgressDialog pDialog;

    @Override protected void onPreExecute(){
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Downloading file. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setMax(100);
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setCancelable(true);
        pDialog.setTitle("Please wait...");
        String msg = "Updating Parts...";
        pDialog.setMessage(msg);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        int count;
        try{
            URL url = new URL("https://rebrickable.com/api/get_set_parts?key=JMMsrpkR1w&format=json&set=60128-1");
            URLConnection connection = url.openConnection();
            connection.connect();
            int lengthOfFile = connection.getContentLength();
            pDialog.setMax(lengthOfFile);
            InputStream input = new BufferedInputStream(url.openStream(), 8192);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte data[] = new byte[1024];
            long total = 0;
            while ((count = input.read(data)) != -1) {
                total += count;
                publishProgress("" + (int) ((total * 100) / lengthOfFile));
                output.write(data, 0, count);
            }
            input.close();
            output.flush();
            String json = new String(output.toByteArray());
            System.out.println(json);
        }catch (Exception e){
            Log.e("Error: ", e.getMessage());
            return false;
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        pDialog.setProgress(Integer.parseInt(values[0]));
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        pDialog.dismiss();
        if (listener != null) listener.onPartsLoaded(aBoolean);
    }
}
