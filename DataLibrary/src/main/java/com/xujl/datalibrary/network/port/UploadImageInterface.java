package com.xujl.datalibrary.network.port;


import com.google.gson.JsonObject;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/9/30.
 * 上传图片
 */

public interface UploadImageInterface {
    @Multipart
    @POST("api/upload")
    Call<JsonObject>  uploadImage (@PartMap Map<String, RequestBody> image,
//    Call<JsonBean>  uploadImage(@Part  MultipartBody.Part image,
                                   @Query("token") String token, @Query("fileType") String fileType);
}
