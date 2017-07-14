package com.xujl.datalibrary.network.port;

import com.google.gson.JsonObject;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2016/9/21.
 */
public interface RequestForNetwork {
//        @Headers({"Content-Type: application/json;charset=utf-8","Accept: application/json"})//需要添加头
    @POST("{apiName}")
    Call<JsonObject> requestPost ( @Body RequestBody body, @Path(value = "apiName", encoded = true) String apiName);

    /**
     * Get请求
     *
     * @param options
     * @return
     */
    @GET("{apiName}")
    Call<JsonObject> requestGet (@Path(value = "apiName", encoded = true) String apiName, @QueryMap Map<String, Object> options);
}
