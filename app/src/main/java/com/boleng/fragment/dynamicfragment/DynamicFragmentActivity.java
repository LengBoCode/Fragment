package com.boleng.fragment.dynamicfragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.hardware.camera2.DngCreator;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.boleng.fragment.R;
import com.boleng.fragment.event.MessageEvent;
import com.boleng.fragment.staticfragment.ContentFragment;
import com.boleng.fragment.staticfragment.TitleFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;

public class DynamicFragmentActivity extends Activity implements View.OnClickListener ,TitleFragment.TitleFragmentListener{

    private static String TAG = DynamicFragmentActivity.class.getSimpleName();
    private Button btnTitle;
    private Button btnContent;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private TitleFragment titleFragment;
    private ContentFragment contentFragment;
    public MyHandler myHandler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_fragment);
        btnTitle = (Button) findViewById(R.id.btn_title);
        btnContent = (Button) findViewById(R.id.btn_content);
        btnTitle.setOnClickListener(this);
        btnContent.setOnClickListener(this);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_title:
                transaction = fragmentManager.beginTransaction();
                if (titleFragment == null)
                    titleFragment = new TitleFragment();
                transaction.replace(R.id.fragContainer, titleFragment);
                transaction.commit();
                break;
            case R.id.btn_content:
                transaction = fragmentManager.beginTransaction();
                if (contentFragment == null)
                    contentFragment = new ContentFragment();
                transaction.replace(R.id.fragContainer, contentFragment);
                transaction.commit();
                break;
            default:
                break;
        }
    }

    private void init() {
        fragmentManager = getFragmentManager();
    }

    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        Toast.makeText(this, event.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public static class MyHandler extends Handler {
        private WeakReference<DynamicFragmentActivity> activityWeakReference;

        public MyHandler(DynamicFragmentActivity activity) {
            activityWeakReference = new WeakReference<DynamicFragmentActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            DynamicFragmentActivity activity = activityWeakReference.get();
            if (activity != null) {
                Log.i(TAG, "MSG" + msg.toString());
            }
        }
    }

    @Override
    public void sendMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }
}
