package com.boleng.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.boleng.fragment.activity.StaticFragmentActivity;
import com.boleng.fragment.dynamicfragment.DynamicFragmentActivity;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button btnStaticFragment;
    private Button btnDynamicFragment;
    private static Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStaticFragment= (Button) findViewById(R.id.btn_static_fragment);
        btnDynamicFragment= (Button) findViewById(R.id.btn_dynamic_fragment);
        btnStaticFragment.setOnClickListener(this);
        btnDynamicFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_static_fragment:
                intent=new Intent(MainActivity.this, StaticFragmentActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_dynamic_fragment:
                intent=new Intent(MainActivity.this, DynamicFragmentActivity.class);
                startActivity(intent);
                break;
            default:

                break;
        }
    }
}
