package com.example.fknm;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//import com.example.tttest.util.ToastUtils;
import java.net.Socket;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.OnClick;

/**
 * Created by sgll on 2019/1/14.
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.bt_submit)
    public Button btSubmit;
    @BindView(R.id.et_content)
    public EditText etContent;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
    }


    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic() {

    }

    @OnClick(R.id.bt_submit)
    void onSubmit(View view) {
        Socket mSocket = getSocket();
        if (mSocket == null) {
            ToastUtils.show(this, getString(R.string.error_server_socket));
            return;
        }

        String content = etContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            ToastUtils.show(this, "内容不能为空");
            return;
        }
        new SendThread(content, mSocket).start();
    }

}

