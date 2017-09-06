package com.xujl.datalibrary.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.xujl.datalibrary.network.port.RequestForNetwork;
import com.xujl.datalibrary.network.port.UploadImageInterface;
import com.xujl.utilslibrary.data.NetworkUtil;
import com.xujl.utilslibrary.port.RequestCallBack;
import com.xujl.utilslibrary.system.InternetState;
import com.xujl.utilslibrary.system.Log;
import com.xujl.utilslibrary.system.StartUpApplication;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class InternetUtil extends NetworkUtil {
    private Retrofit retrofit;

    public InternetUtil (String url) {
        final OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public InternetUtil () {
        this(ApiName.BASE_1);
    }

    public void requestForPost (Map<String, Object> params, String tag, final RequestCallBack requestCallBack, String api) {
        // 是否有可用网络
        if (!InternetState.isNetworkConnected()) {
            requestCallBack.error(RequestCallBack.NO_INTERNET, null);
            return;
        }
        RequestForNetwork rf = retrofit.create(RequestForNetwork.class);
        final RequestBody requestBody = RequestBody.
                create(MediaType.parse("application/json; charset=utf-8"), getGson().toJson(params));
        Call<JsonObject> call = rf.requestPost(requestBody, api);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse (Call<JsonObject> call, Response<JsonObject> response) {
                String resultStr = null;
                if (StartUpApplication.isDubug()) {
                    resultStr = response.body().toString();
                    requestCallBack.notice(resultStr);
                    return;
                }
                try {
                    resultStr = response.body().toString();
                    requestCallBack.notice(resultStr);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    requestCallBack.error(RequestCallBack.UNKNOW_ERROR, "返回数据为空");
                }
            }

            @Override
            public void onFailure (Call<JsonObject> call, Throwable t) {
                Log.e("InternetUtil-->", t.toString());
                if (!t.toString().equals("java.io.IOException: Canceled")) {
                    requestCallBack.error(RequestCallBack.SERVER_ERROR, null);

                }
            }
        });
    }

    public void requestForGet (Map<String, Object> params, String tag, final RequestCallBack requestCallBack, String apiName) {
        // 是否有可用网络
        if (!InternetState.isNetworkConnected()) {
            requestCallBack.error(RequestCallBack.NO_INTERNET, null);
            return;
        }
        RequestForNetwork rf = retrofit.create(RequestForNetwork.class);
//        final RequestBody requestBody = RequestBody.
//                create(MediaType.parse("application/json; charset=utf-8"), getGson().toJson(params));
        Call<JsonObject> call = rf.requestGet( apiName,params);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse (Call<JsonObject> call, Response<JsonObject> response) {
                String resultStr = null;
                if (StartUpApplication.isDubug()) {
                    resultStr = response.body().toString();
                    requestCallBack.notice(resultStr);
                    return;
                }
                try {
                    resultStr = response.body().toString();
                    requestCallBack.notice(resultStr);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    requestCallBack.error(RequestCallBack.UNKNOW_ERROR, "返回数据为空");
                }
            }

            @Override
            public void onFailure (Call<JsonObject> call, Throwable t) {
                Log.e("InternetUtil-->", t.toString());
                if (!t.toString().equals("java.io.IOException: Canceled")) {
                    requestCallBack.error(RequestCallBack.SERVER_ERROR, null);

                }
            }
        });
    }

    public void uploadImage (List<FormImage> formImage, String token, String tag, final RequestCallBack requestCallBack) {
// 是否有可用网络
        if (!InternetState.isNetworkConnected()) {
            requestCallBack.error(RequestCallBack.NO_INTERNET, null);
            return;
        }

        final Map<String, RequestBody> fileMap = new HashMap<>();
        for (FormImage image : formImage) {
            final File file = image.getCompressFile();
            RequestBody requestFile = RequestBody.
                    create(MediaType.parse("multipart/form-data"), file);
            fileMap.put("file[]\"; filename=\"" + image.getFileName() + "", requestFile);
            Log.e("图片尺寸", file.length() + "");
        }
        UploadImageInterface rf = new Retrofit.Builder()
                .baseUrl("")
//                .addConverterFactory(new ChunkingConverterFactory(fileMap.get(formImage.getFileName()),
//                        progressListener))
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(UploadImageInterface.class);
        Call<JsonObject> call = rf.uploadImage(fileMap, token, "0");
        call.enqueue(new Callback<JsonObject>() {
                         @Override
                         public void onResponse (Call<JsonObject> call, Response<JsonObject> response) {
//                final String s = response.raw().request().url().toString();
                             // 连接成功回调
                             requestCallBack.notice(response.body().toString());
                         }

                         @Override
                         public void onFailure (Call<JsonObject> call, Throwable t) {
                             if (!t.toString().equals("java.io.IOException: Canceled")) {
                                 requestCallBack.error(RequestCallBack.SERVER_ERROR, null);
                             }
                         }
                     }

        );
    }


    //取消所有请求
    public void cancelAllRequest () {
    }

    //取消指定tag的请求
    public void cancelRequestForTag (String tag) {
    }

    //添加一个请求tag
    public void addRequestTag (String tag) {
    }

}
