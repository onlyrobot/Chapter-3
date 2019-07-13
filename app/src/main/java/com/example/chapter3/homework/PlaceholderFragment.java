package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class PlaceholderFragment extends Fragment {


    private LottieAnimationView animationView;
    private  RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View view = inflater.inflate(R.layout.fragment_placeholder, container, false);
        animationView = view.findViewById(R.id.animation_view);
        recyclerView = view.findViewById(R.id.recycler_view);
        //先将RecyclerView设为不可见
        recyclerView.setVisibility(View.INVISIBLE);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(container.getContext());
        LinearLayoutManager managerVertical = new LinearLayoutManager(container.getContext());
        managerVertical.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(managerVertical);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 7; ++i){
            data.add("Item " + (i + 1));
        }
        adapter.setVerticalDataList(data);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                recyclerView.setVisibility(View.VISIBLE);
                ObjectAnimator a1 = ObjectAnimator.ofFloat(animationView, "alpha", 1.0f, 0.0f);
                a1.setDuration(1000);
                ObjectAnimator a2 = ObjectAnimator.ofFloat(recyclerView, "alpha", 0.0f, 1.0f);
                a2.setDuration(1000);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(a1, a2);
                animatorSet.start();
            }
        }, 5000);
    }
}
