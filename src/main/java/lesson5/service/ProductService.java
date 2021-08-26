package lesson5.service;

import lesson5.dto.Product;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface ProductService {
    @GET("products")
    Call<ResponseBody> getProduct();

    @GET("products/{id}")
    Call<Product> getIdProduct(@Path("id") int id);
    @GET("products/{id}")
    Call<ResponseBody> getEmptyIdProduct(@Path("id") String id);
    @GET("products/{id}")
    Call<Product> getStrProduct(@Path("id") String id);
    @GET("products/{id}")
    Call<Product> getLongProduct(@Path("id") long id);
    @GET("products/{id}")
    Call<Product> getDoubleProduct(@Path("id") double id);
    @GET("products/{id}")
    Call<Product> getBoolProduct(@Path("id") boolean id);

    @POST("products")
    Call<Product> createProduct(@Body Product product);

    @PUT("products")
    Call<Product> putProduct(@Body Product product);

    @DELETE("products/{id}")
    Call<ResponseBody> deleteIntIdProduct(@Path("id") int id);
    @DELETE("products/{id}")
    Call<ResponseBody> deleteStringIdProduct(@Path("id") String id);
    @DELETE("products/{id}")
    Call<ResponseBody> deleteLongIdProduct(@Path("id") long id);
    @DELETE("products/{id}")
    Call<ResponseBody> deleteDoubleIdProduct(@Path("id") double id);
    @DELETE("products/{id}")
    Call<ResponseBody> deleteBoolIdProduct(@Path("id") boolean id);
}
