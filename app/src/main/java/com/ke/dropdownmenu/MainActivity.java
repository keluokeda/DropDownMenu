package com.ke.dropdownmenu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button mButton;
    @BindView(R.id.button2)
    Button mButton2;
    @BindView(R.id.button3)
    Button mButton3;
    @BindView(R.id.button4)
    Button mButton4;
    @BindView(R.id.list_view)
    ListView mListView;

    private MenuAFragment mMenuAFragment;
    private MenuBFragment mMenuBFragment;
    private MenuCFragment mMenuCFragment;
    private MenuDFragment mMenuDFragment;

    private List<Fragment> mFragmentList = new ArrayList<>(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Logger.addLogAdapter(new AndroidLogAdapter());

        initListView();

        mMenuAFragment = new MenuAFragment();
        mMenuBFragment = new MenuBFragment();
        mMenuCFragment = new MenuCFragment();
        mMenuDFragment = new MenuDFragment();

        mFragmentList.add(mMenuAFragment);
        mFragmentList.add(mMenuBFragment);
        mFragmentList.add(mMenuCFragment);
        mFragmentList.add(mMenuDFragment);

    }

    private void initListView() {
        int max = 100;
        List<String> stringList = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            stringList.add(String.valueOf(i));
        }


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringList);
        mListView.setAdapter(arrayAdapter);
    }

    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                showOrHideFragment(mMenuAFragment);
                break;
            case R.id.button2:
                showOrHideFragment(mMenuBFragment);
                break;
            case R.id.button3:
                showOrHideFragment(mMenuCFragment);
                break;
            case R.id.button4:
                showOrHideFragment(mMenuDFragment);
                break;
        }
    }

    private void showOrHideFragment(Fragment targetFragment) {
        Fragment currentFragment = getCurrentShowFragment();
        if (currentFragment == null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.fragment_container, targetFragment);
            ft.commit();
        } else if (currentFragment == targetFragment) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.remove(currentFragment);
            ft.commit();
        } else {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.remove(currentFragment);
            ft.add(R.id.fragment_container, targetFragment);
            ft.commit();
        }
    }

    @Nullable
    private Fragment getCurrentShowFragment() {
        for (Fragment fragment : mFragmentList) {
            if (fragment.isVisible()) {
                return fragment;
            }
        }
        return null;
    }

    public void setMenuAValue(@NonNull String value) {
        mButton.setText(value);
        getSupportFragmentManager().beginTransaction().remove(mMenuAFragment).commit();
    }

    public void setMenuBValue(@NonNull String value) {
        mButton2.setText(value);
        getSupportFragmentManager().beginTransaction().remove(mMenuBFragment).commit();
    }

    public void setMenuCValue(@NonNull String value) {
        mButton3.setText(value);
        getSupportFragmentManager().beginTransaction().remove(mMenuCFragment).commit();
    }

    public void setMenuDValue(@NonNull String value) {
        mButton4.setText(value);
        getSupportFragmentManager().beginTransaction().remove(mMenuDFragment).commit();
    }
}
