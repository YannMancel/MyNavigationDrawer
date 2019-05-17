package com.mancel.yann.mynavigationdrawer.controllers.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mancel.yann.mynavigationdrawer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilePageFragment extends Fragment {

    // CONSTRUCTORS --------------------------------------------------------------------------------

    public ProfilePageFragment() {}

    // METHODS -------------------------------------------------------------------------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_page, container, false);
    }

    /**
     * Creates a new instance of the ProfilePageFragment class
     *
     * @return a ProfilePageFragment object
     */
    public static ProfilePageFragment newInstance() {
        return new ProfilePageFragment();
    }
}
