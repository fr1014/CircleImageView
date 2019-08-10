package com.example.testlearning.httpclient;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpUtils {

    private HttpClient createHttpClient(){
        HttpParams mDefaultHttpParams = new BasicHttpParams();
        //设置连接超时
        HttpConnectionParams.setConnectionTimeout(mDefaultHttpParams,15000);
        //设置请求超时
        HttpConnectionParams.setSoTimeout(mDefaultHttpParams,15000);
        HttpConnectionParams.setTcpNoDelay(mDefaultHttpParams,true);
        HttpProtocolParams.setVersion(mDefaultHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(mDefaultHttpParams, HTTP.UTF_8);
        //持续握手
        HttpProtocolParams.setUseExpectContinue(mDefaultHttpParams,true);
        HttpClient mHttpClient = new DefaultHttpClient(mDefaultHttpParams);
        return mHttpClient;
    }

    private static final String TAG = "HttpUtils";
    public void useHttpClientGet(String url){
        HttpGet mHttpGet = new HttpGet(url);
        mHttpGet.addHeader("Connection","Keep-Alive");
        HttpClient mHttpClient = createHttpClient();
        try {
            HttpResponse mHttpResponse = mHttpClient.execute(mHttpGet);
            HttpEntity mHttpEntity = mHttpResponse.getEntity();
            int code = mHttpResponse.getStatusLine().getStatusCode();
            if (mHttpEntity != null){
                InputStream mInputStream = mHttpEntity.getContent();
                String response = converStreamToString(mInputStream);
                Log.d(TAG, "请求状态码："+code+"\n请求结果\n"+response);
                mInputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String converStreamToString(InputStream inputStream) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = reader.readLine())!=null){
            sb.append(line+"\n");
        }
        String response = sb.toString();
        return response;
    }

}
