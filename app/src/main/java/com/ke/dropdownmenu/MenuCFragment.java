package com.ke.dropdownmenu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuCFragment extends Fragment {


    @BindView(R.id.list_view)
    ListView mListView;
    Unbinder unbinder;
    @BindView(R.id.frame_layout)
    FrameLayout mFrameLayout;

    public MenuCFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_a, container, false);
        unbinder = ButterKnife.bind(this, view);
        initListView();

//        startAnim();
        return view;
    }

    private void startAnim() {
        mListView.post(new Runnable() {
            @Override
            public void run() {
                mListView.setScaleX(1);
                mListView.setScaleY(0);
                mListView.setPivotX(0);
                mListView.setPivotY(0);
                mListView.animate().scaleYBy(1).setDuration(300).start();
            }
        });
    }

    private void initListView() {
        int count = 5;
        int start = 20;
        final List<String> stringList = new ArrayList<>(count);
        for (int i = start; i < start + count; i++) {
            stringList.add(String.valueOf(i));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, stringList);
        mListView.setAdapter(arrayAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String value = stringList.get(i);
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.setMenuCValue(value);

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.frame_layout)
    public void onViewClicked() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }
}
