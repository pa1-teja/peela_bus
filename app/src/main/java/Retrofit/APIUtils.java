package Retrofit;

/**
 * Created by Pavan on 11/20/2017.
 */

public class APIUtils {
    public static final String BASE_URL = "http://test.peelabus.com";

    public static RetrofitInterface getRetrofitService() {
        return RetrofitClient.getClient(BASE_URL).create(RetrofitInterface.class);
    }
}
