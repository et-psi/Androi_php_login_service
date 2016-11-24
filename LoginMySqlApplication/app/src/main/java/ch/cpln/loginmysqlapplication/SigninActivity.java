package ch.cpln.loginmysqlapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by sacchettip on 21.11.2016.
 */

public class SigninActivity extends AsyncTask<String, Void, String>  {

    Context context;
    ProgressDialog progressDialog;

    SigninActivity(Context context)
    {
        this.context = context;
    }
    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context,"Vérification...", "Merci de patienter");
    }

    protected String doInBackground(String... params) {
        //String login_url = "http://lmb-106b-00/sacchetti/site_androidtest/login.php";
        String login_url = "http://157.26.174.33/sacchetti/site_androidtest/login.php";

        String type = params[0];
        if(type.equals("login")) {
            try {
                String user_name = params[1];
                String password = params[2];

                //Préparation de la connexion
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();

                //Préparation des données à envoyer pour la requête
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("pwd","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");

                //envoi de la requête
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                //récupération des données
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    protected void onPostExecute(String result) {
        // execution of result of Long time consuming operation
        progressDialog.dismiss();
        Toast.makeText(context,result,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }
}
