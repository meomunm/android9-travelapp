package techkids.vn.travelapp.networks;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import techkids.vn.travelapp.networks.JSONSeverModel.JSONRequestModel;
import techkids.vn.travelapp.networks.JSONSeverModel.JSONResponseModel;

/**
 * Created by trongphuong1011 on 8/6/2017.
 */

public interface PutIDService {
    @POST("diphuot")
    Call<JSONResponseModel> putID(@Body JSONRequestModel jsonRequestModel);
}
