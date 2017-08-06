package techkids.vn.travelapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import techkids.vn.travelapp.R;
import techkids.vn.travelapp.models.ProfileModel;

/**
 * Created by ADMIN on 8/6/2017.
 */

public class ProfileFragment extends Fragment {
    private ProfileModel profileModel;

    ProfilePictureView profilePictureView;
    TextView tvName, tvBirthday, tvMail;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        EventBus.getDefault().register(this);
        profilePictureView = view.findViewById(R.id.ppp_picture_profile);
        tvName = view.findViewById(R.id.tv_name_profile);
        tvMail= view.findViewById(R.id.tv_mail_profile);
        tvBirthday = view.findViewById(R.id.tv_birthday_profile);
        this.setupData();

        return view;
    }

    private void setupData(){
        tvBirthday.setText(profileModel.getId());
        tvName.setText(profileModel.getName());
        tvMail.setText(profileModel.getMail());
        profilePictureView.setProfileId(profileModel.getId());
    }

    @Subscribe(sticky = true)
    public void onReceivedProfileModel(ProfileModel profileModel) {
        this.profileModel = profileModel;
    }
}
