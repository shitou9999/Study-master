package com.example.app.loading.loading.factory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app.loading.R;


/**
 * Material默认loading样式
 * author  dengyuhan
 * created 2017/4/15 23:04
 */
public class MaterialFactory implements LoadingFactory{

    @Override
    public View onCreateView(ViewGroup parent) {
        View loadingView = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_progressbar_vertical_material, parent,false);
        return loadingView;
    }
}
