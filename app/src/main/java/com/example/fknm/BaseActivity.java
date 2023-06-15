package com.example.fknm;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
//import com.example.tttest.R;
//import com.example.tttest.MyApplication;
//import com.example.tttest.BindView;
//import java.util.ArrayList;
//import java.util.List;
//
import com.example.fknm.util.ActivityCollector;
import com.example.fknm.util.ToastUtils;

import java.net.Socket;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by sgll on 2018/12/11.
 */
public abstract class BaseActivity extends AppCompatActivity {
    //反注销
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        unbinder = ButterKnife.bind(this);
        ActivityCollector.addActivity(this);
        setServer();
        onCreateView(savedInstanceState);
        processLogic();
        setListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        ActivityCollector.removeActivity(this);
    }

    /**
     * 设置服务
     */
    private void setServer(){
        //TODO:重启服务
        if (MyApplication.mServer == null) {
            ToastUtils.show(this, getString(R.string.error_server_socket));
            return;
        }
    }

    public Socket getSocket(){
        Socket mSocket = null;
        if (MyApplication.mServer.getSocket() != null) {
            mSocket = MyApplication.mServer.getSocket();
        }
        return mSocket;
    }

    /**
     * 加载页面 Layout
     * @return R.layout.xxx
     */
    protected abstract int getLayoutResId();

    /**
     * 初始化控件
     */
    protected abstract void onCreateView(Bundle savedInstanceState);

    /**
     * 设置各种事件的监听器
     */
    protected abstract void setListener();

    /**
     * 业务逻辑处理，主要与后端交互（网络请求）
     */
    protected abstract void processLogic();
}

