package com.boleng.fragment.staticfragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.boleng.fragment.R;
import com.boleng.fragment.dynamicfragment.DynamicFragmentActivity;

/**
 * Created by boleng on 8/29/17.
 */

public class TitleFragment extends Fragment implements View.OnClickListener{

    private Button btnSendMessage;
    private TitleFragmentListener listener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.title,container,false);
        btnSendMessage= (Button) view.findViewById(R.id.btn_send_message);
        btnSendMessage.setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DynamicFragmentActivity){
            DynamicFragmentActivity.MyHandler myHandler=((DynamicFragmentActivity) context).myHandler;
            myHandler.sendEmptyMessageDelayed(0,1000);
            listener= (TitleFragmentListener) context;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_send_message:
                listener.sendMessage("send message from TitleFragment");
                break;
            default:
                break;
        }
    }

    public interface TitleFragmentListener{
        void sendMessage(String message);
    }
}
