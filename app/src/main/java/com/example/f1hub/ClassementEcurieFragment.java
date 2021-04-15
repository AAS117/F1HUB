package com.example.f1hub;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

// https://www.youtube.com/watch?v=tPV8xA7m-iw&list=FLg8UcRtLyjxPkR6VDnYsNHg
public class ClassementEcurieFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_classement_ecurie, container, false);
    }
}
