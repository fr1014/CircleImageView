package com.example.testlearning;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements OnItemClickListener {
    private androidx.recyclerview.widget.RecyclerView mRecyclerView;
    private HomeAdapter mHomeAdapter;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initView();
        initData();
        initRecyclerView();
    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mList.add("测试");
        }
        for (int i = 0; i < 4; i++) {
            mList.add("喜喜");
        }
        for (int i = 0; i < 4; i++) {
            mList.add("哈哈");
        }
    }

    private void initRecyclerView() {
        //设置布局管理器
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //设置item增加和删除的动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mHomeAdapter = new HomeAdapter(this, mList);
        mRecyclerView.setAdapter(mHomeAdapter);

        mHomeAdapter.setOnItemClickListener(this);
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recycler_view);
        //将RecyclerView设置成水平排列
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        mRecyclerView.setLayoutManager(linearLayoutManager);

        //实现GridView，只需要自定义横向的分割线
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
//        mRecyclerView.addItemDecoration(自定义分割线);
    }


    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(RecyclerViewActivity.this,"点击第"+(position+1),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, final int position) {
        new AlertDialog.Builder(RecyclerViewActivity.this)
                .setTitle("确认删除吗？")
                .setNegativeButton("取消",null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mHomeAdapter.notifyItemRemoved(position);
                        mList.remove(position);
                        //position数据开始变化的位置，itemCount变化位置的数据数量2.2
                        mHomeAdapter.notifyItemRangeChanged(position,mList.size()-position);
                        Toast.makeText(RecyclerViewActivity.this,(position+1)+"已删除",Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}
