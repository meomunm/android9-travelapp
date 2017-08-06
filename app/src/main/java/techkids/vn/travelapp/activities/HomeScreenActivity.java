package techkids.vn.travelapp.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import techkids.vn.travelapp.R;
import techkids.vn.travelapp.fragments.ProfileFragment;
import techkids.vn.travelapp.managers.ScreenManager;
import techkids.vn.travelapp.models.ProfileModel;

public class HomeScreenActivity extends AppCompatActivity {
    private static final String TAG = HomeScreenActivity.class.toString();
    ImageView ivTrangCaNhan;
    private ProfileModel profileModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        EventBus.getDefault().register(this);


        ivTrangCaNhan = (ImageView) findViewById(R.id.iv_trang_ca_nhan);

        ivTrangCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScreenManager.openFragment(getSupportFragmentManager(), new ProfileFragment(), R.id.ll_container);
                Log.d(TAG, String.format("show model: %s, %s, %s", profileModel.getId(), profileModel.getName(), profileModel.getMail()));
            }
        });
    }

    @Subscribe(sticky = true)
    public void onReceivedProfileModel(ProfileModel profileModel) {
        this.profileModel = profileModel;
    }
}
