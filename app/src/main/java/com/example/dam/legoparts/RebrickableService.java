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

public class RebrickableService extends AsyncTask<String, String, String> {

    String xml;

    private Context context;

    public RebrickableService(Context context){ this.context = context;}

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
    protected String doInBackground(String... strings) {
        int count;
        try{
            String key = "JMMsrpkR1w";
            URL url = new URL("http://stucom.flx.cat/lego/get_set_parts.php?key="+key+"&set="+strings[0]);
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
            xml = new String(output.toByteArray());
        }catch (Exception e){
            Log.e("Error: ", e.getMessage());
            return null;
        }
        return xml;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        pDialog.setProgress(Integer.parseInt(values[0]));
        pDialog.show();
    }

    @Override
    protected void onPostExecute(String a) {
        pDialog.dismiss();
        super.onPostExecute(a);
    }

}
