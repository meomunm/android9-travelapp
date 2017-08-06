package techkids.vn.travelapp.networks.JSONSeverModel;

/**
 * Created by trongphuong1011 on 8/6/2017.
 */

public class JSONResponseModel {
    private String message;

    public JSONResponseModel(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
