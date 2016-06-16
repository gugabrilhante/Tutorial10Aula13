package gustavo.brilhante.tutorial10aula13;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Gustavo on 15/06/2016.
 */
public class AsyncRequestUrl extends AsyncTask<String, Integer, String> {

    ProgressDialog progDailog;
    Context context;

    TextView textView;

    public AsyncRequestUrl(Context context, TextView textView) {
        this.context = context;
        this.textView = textView;
    }

    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        progDailog = new ProgressDialog(context, progDailog.THEME_TRADITIONAL);
        progDailog.setMessage("Loading...");
        progDailog.setIndeterminate(false);
        progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDailog.setCancelable(false);
        progDailog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            URL url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            InputStream is = conn.getInputStream();
            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String data = null;
            String content = " ";
            while ((data = reader.readLine())!=null){
                content+=data+"\n";
            }
            return content;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    protected void onPostExecute(String result) {
        textView.setText(result);
        progDailog.dismiss();
    }


}
