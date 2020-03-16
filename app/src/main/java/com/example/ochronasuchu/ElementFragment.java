package com.example.ochronasuchu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ElementFragment extends Fragment {
    public TextView mTextView1;
    public TextView mTextView2;
    public ImageView mImageView;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_element,container,false);

        mImageView = view.findViewById(R.id.imageView2);
        mTextView1 = view.findViewById(R.id.textView7);
        mTextView2 = view.findViewById(R.id.textView8);


        return view;
    }




}
