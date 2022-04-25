package com.es.netschool24.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.es.netschool24.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class InformationFragment extends Fragment {

    ImageView cover_img,edit_pen;
    CircleImageView profile_img;
    TextView name,email,phone,dath_of_birth,father_name,mother_name,
            address,whatsapp,nationality,guardian_name,guardian_phone,guardian_relation;



    public InformationFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_information, container, false);

        cover_img = view.findViewById(R.id.cover_img);
        edit_pen = view.findViewById(R.id.edit_pen);
        profile_img = view.findViewById(R.id.profile_img);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        phone = view.findViewById(R.id.phone);
        dath_of_birth = view.findViewById(R.id.dath_of_birth);
        father_name = view.findViewById(R.id.father_name);
        mother_name = view.findViewById(R.id.mother_name);
        address = view.findViewById(R.id.address);
        whatsapp = view.findViewById(R.id.whatsapp);
        nationality = view.findViewById(R.id.nationality);
        guardian_name = view.findViewById(R.id.guardian_name);
        guardian_phone = view.findViewById(R.id.guardian_phone);
        guardian_relation = view.findViewById(R.id.guardian_relation);

        return view;
    }
}