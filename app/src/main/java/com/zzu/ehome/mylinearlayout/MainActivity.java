package com.zzu.ehome.mylinearlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MyLinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        linearLayout=(MyLinearLayout)findViewById(R.id.myLinearLayout);
        ItemBean mainItem=new ItemBean("小明","http://staging.topmd.cn/UploadFile/image/20160914103216400.jpg");
        linearLayout.addMainItem(mainItem);
        List<ItemBean> list=new ArrayList<>();
        list.add(new ItemBean("马新","http://staging.topmd.cn/UploadFile/image/20160923162109048.jpg"));
        list.add(new ItemBean("郭强","http://staging.topmd.cn/UploadFile/image/20161012160235822.jpg"));
        linearLayout.addItem(list, new MyLinearLayout.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Toast.makeText(getApplicationContext(), "item"+pos, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
