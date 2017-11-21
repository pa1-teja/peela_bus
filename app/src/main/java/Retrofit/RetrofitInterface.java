package Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Pavan on 11/20/2017.
 */

public interface RetrofitInterface {

    @GET("/WebService.asmx/LiveTracking")
    Call<BusInfo> RESULT_CALL();

}
