package com.tubes.pendaftaranpendakigunung.REST;

import com.tubes.pendaftaranpendakigunung.Model.CRUDMessage;
import com.tubes.pendaftaranpendakigunung.Model.Parsing.GetLogin;
import com.tubes.pendaftaranpendakigunung.Model.Parsing.getAllPendaki;
import com.tubes.pendaftaranpendakigunung.Model.Parsing.getPendaki;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface RetrofitInterface {
    @GET("_getLogin.php")
    Call<GetLogin> getLogin(
            @QueryMap Map<String, String> params
    );

    @GET("_getAllPendaki.php")
    Call<getAllPendaki> getAllPendaki();

    @FormUrlEncoded
    @POST("_getPendaki.php")
    Call<getPendaki> getPendaki (
            @Field("id_pendaki") String idPendaki
    );

    @FormUrlEncoded
    @POST("_insertPendaki.php")
    Call<CRUDMessage> insertPendaki (
            @Field("nama_pendaki") String namaPendaki,
            @Field("alamat_pendaki") String alamatPendaki,
            @Field("no_telp") String noTelp
    );

    @FormUrlEncoded
    @POST("_updatePendaki.php")
    Call<CRUDMessage> editPendaki (
            @Field("id_pendaki") String idPendaki,
            @Field("nama_pendaki") String namaPendaki,
            @Field("alamat_pendaki") String alamatPendaki,
            @Field("no_telp") String noTelp
    );

    @FormUrlEncoded
    @POST("_deletePendaki.php")
    Call<CRUDMessage> deletePendaki (
            @Field("id_pendaki") String idPendaki
    );

}
