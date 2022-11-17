package com.mv.fp4.ui.dynamic_data;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mv.fp4.R;
import com.mv.fp4.data.model.Book;
import com.mv.fp4.data.model.SingletonBookManager;


public class DynamicFragment extends Fragment {

    private TextView titlePlaceholder;
    private TextView seriePlaceholder;
    private TextView authorPlaceholder;
    private TextView yearPlaceholder;
    private ImageView coverPlaceholder;

    public DynamicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        bindViews(view);

        Book dynamicBook = SingletonBookManager.getInstance().getBookList().get(0);
        titlePlaceholder.setText(dynamicBook.getTitle());
        seriePlaceholder.setText(dynamicBook.getSerie());
        authorPlaceholder.setText(dynamicBook.getAuthor());
        yearPlaceholder.setText(String.valueOf(dynamicBook.getYear()));
        coverPlaceholder.setImageResource(dynamicBook.getCover());

        return view;
    }


    private void bindViews(View view) {
        titlePlaceholder = view.findViewById(R.id.DynamicDataFrag_Tv_TitlePlaceholder);
        seriePlaceholder = view.findViewById(R.id.DynamicDataFrag_Tv_SeriePlaceholder);
        authorPlaceholder = view.findViewById(R.id.DynamicDataFrag_Tv_AutorPlaceholder);
        yearPlaceholder = view.findViewById(R.id.DynamicDataFrag_Tv_AnoPlaceholder);
        coverPlaceholder = view.findViewById(R.id.DynamicDataFrag_Iv_BookCoverPlaceholder);
    }
}