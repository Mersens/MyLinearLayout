package com.zzu.ehome.mylinearlayout;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mersens on 2016/10/21.
 */

public class MyLinearLayout extends LinearLayout {
    private LinearLayout.LayoutParams params = null;
    private Context context;
    private List<View> list = null;
    private static final int ANIMATION_TIME = 20;
    private boolean isMenuOpened;
    private int i = 0;
    private int j = 0;
    private ShowRunnable showRunnable = new ShowRunnable();
    private HideRunnable hideRunnable = new HideRunnable();
    private LayoutTransition layoutTransition;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public MyLinearLayout(Context context) {
        this(context, null);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    private void init() {
        layoutTransition = new LayoutTransition();
        setLayoutTransition(layoutTransition);
        list = new ArrayList<>();
        inflater = LayoutInflater.from(context);
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        params.rightMargin = 10;
        setGravity(Gravity.RIGHT);
        setBackgroundColor(getResources().getColor(android.R.color.transparent));
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(null, View.TRANSLATION_X, 0F, 0F).
                setDuration(layoutTransition.getDuration(LayoutTransition.APPEARING));
        layoutTransition.setAnimator(LayoutTransition.APPEARING, animator1);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(null, View.TRANSLATION_X, 0F, 0F).
                setDuration(layoutTransition.getDuration(LayoutTransition.DISAPPEARING));
        layoutTransition.setAnimator(LayoutTransition.DISAPPEARING, animator2);
    }

    public void addMainItem(final ItemBean item) {
        View mainView = inflater.inflate(R.layout.list_item, null);
        ImageView imageView = (ImageView) mainView.findViewById(R.id.ItemImage);
        TextView tv_name = (TextView) mainView.findViewById(R.id.tvname);
        Glide.with(context).load(item.getImg()).into(imageView);
        tv_name.setText(item.getName());
        mainView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMenuOpened) {
                    hidePromotedActions();
                    isMenuOpened = false;
                } else {
                    isMenuOpened = true;
                    showPromotedActions();
                }
            }
        });
        addView(mainView, params);
    }

    public void addItem(List<ItemBean> items, OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        for (int i = 0; i < items.size(); i++) {
            View item = inflater.inflate(R.layout.list_item, null);
            ImageView imageView = (ImageView) item.findViewById(R.id.ItemImage);
            Glide.with(context).load(items.get(i).getImg()).into(imageView);
            TextView tv_name = (TextView) item.findViewById(R.id.tvname);
            tv_name.setText(items.get(i).getName());
            item.setOnClickListener(new MyOnClicKListener(i));
            item.setVisibility(View.GONE);
            list.add(item);
            addView(item, params);
        }
    }

    private void hidePromotedActions() {
        invalidate();
        handler.postDelayed(hideRunnable, ANIMATION_TIME);
    }

    private void showPromotedActions() {
        invalidate();
        handler.postDelayed(showRunnable, ANIMATION_TIME);
    }


    class HideRunnable implements Runnable {
        @Override
        public void run() {
            j++;
            if (j <= list.size()) {
                list.get(j - 1).setVisibility(View.GONE);
                handler.postDelayed(hideRunnable, ANIMATION_TIME);
            } else {
                handler.removeCallbacks(hideRunnable);
                j = 0;
            }
        }
    }

    class ShowRunnable implements Runnable {
        @Override
        public void run() {
            i++;
            if (i <= list.size()) {
                list.get(i - 1).setVisibility(View.VISIBLE);
                handler.postDelayed(showRunnable, ANIMATION_TIME);
            } else {
                handler.removeCallbacks(showRunnable);
                i = 0;
            }
        }
    }

    public class MyOnClicKListener implements OnClickListener {
        private int pos;

        public MyOnClicKListener(int pos) {
            this.pos = pos;
        }

        @Override
        public void onClick(View view) {
            onItemClickListener.onItemClick(pos);

        }
    }

    public interface OnItemClickListener {
         void onItemClick(int pos);
    }


}
