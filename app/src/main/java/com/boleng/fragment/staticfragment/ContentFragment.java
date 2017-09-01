package com.boleng.fragment.staticfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.boleng.fragment.R;
import com.boleng.fragment.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.sql.BatchUpdateException;

/**
 * Created by boleng on 8/29/17.
 */

public class ContentFragment extends Fragment implements View.OnClickListener{

    private Button btnSendMessage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.content,container,false);
        btnSendMessage= (Button) view.findViewById(R.id.btn_send_message);
        btnSendMessage.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send_message:
                EventBus.getDefault().post(new MessageEvent("send message from ContentFragment"));
                break;
            default:
                break;
        }
    }
}
