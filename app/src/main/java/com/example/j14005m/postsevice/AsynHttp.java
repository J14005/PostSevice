package com.example.j14005m.postsevice;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by J14005M on 2017/02/10.
 */

public class AsynHttp extends AsyncTask<String,Integer,Boolean>{
    HttpURLConnection urlConnection = null;
    Boolean flg = false;

    String name;
    String value;

    public AsynHttp(String name,double lat, double lng){
        this.name = name;
        this.value = lat +"," + lng;
    }

    //�񓯊�������������
    @Override
    protected  Boolean doInBackground(String... contents){
        String urlinput = "http://10.0.2.2/upload/post.php";
        try{
            URL url = new URL(urlinput);
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            //POST�p�p�����[�^
            String postDataSample = "name=" + name + "&text=" + value;
            //POST�p�����[�^�ݒ�
            OutputStream out = urlConnection.getOutputStream();
            out.write(postDataSample.getBytes());
            out.flush();
            out.close();
          //  Log.d("test", postDataSample);
            //���X�|���X���󂯎��
            urlConnection.getInputStream();

            flg = true;
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return flg;
    }
}