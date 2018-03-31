package fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ngag.ngag.R;

import adapter.MyRecyclerViewAdapter;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

import static fm.jiecao.jcvideoplayer_lib.JCVideoPlayer.backPress;

/**
 * Created by steven on 2017/7/3.
 */

public class RecyclerViewFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView recycleView;
//    MyRecyclerViewAdapter adapterVideoList;
    MyRecyclerViewAdapter adapterVideoList;
    private View view;
    SwipeRefreshLayout swiperefreshlayout;
    private int[] a = new int[2];
    private CardView cardView; //将视频播放器放入cardView中
    private int lastVisibleItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_one_first, null);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recycleView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        cardView = (CardView) getActivity().findViewById(R.id.cardview);

        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));


       // adapterVideoList = new MyRecyclerViewAdapter(getActivity());
        adapterVideoList=new MyRecyclerViewAdapter(getActivity());

        recycleView.setAdapter(adapterVideoList);
        recycleView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
//                if (JCVideoPlayerManager.getCurrentJcvdOnFirtFloor() != null) {
//                    JCVideoPlayer videoPlayer = (JCVideoPlayer) JCVideoPlayerManager.getCurrentJcvdOnFirtFloor();
//                    if (((ViewGroup) view).indexOfChild(videoPlayer) != -1 && videoPlayer.currentState == JCVideoPlayer.CURRENT_STATE_PLAYING) {
//                        JCVideoPlayer.releaseAllVideos();
//                    }
//                }
            }
        });
        //添加Fragment对系统后退键的点击操作 用于视频全屏后返回到之前的页面
        recycleView.setFocusable(true);//没有这句没法调用
        recycleView.setOnKeyListener(backlistener);


        //下拉加载

        swiperefreshlayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.swiperefreshlayout);
        swiperefreshlayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark);
        swiperefreshlayout.setOnRefreshListener(this);
        swiperefreshlayout.post(new Runnable() {
            @Override
            public void run() {
                swiperefreshlayout.setRefreshing(true);
            }
        });

        downLoadData();
        upLoadData();
    }

    //上拉加载
    private void upLoadData() {
        recycleView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerview, int newState) {
                super.onScrollStateChanged(recyclerview, newState);


                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == adapterVideoList.getItemCount()) {


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "finish", Toast.LENGTH_SHORT).show();
                            //添加数据进adapterVideoList中

                        }

                    }, 1000);
                }
            }
            @Override
            public void onScrolled (RecyclerView recycler,int dx, int dy){
                super.onScrolled(recycler,dx,dy);
                RecyclerView.LayoutManager layoutManager = recycler.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                    //找到最后一个Item
                    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                }
            }
        });
    }

    //下拉刷新
    private void downLoadData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                    int index = adapterVideoList.getItemCount();
                    Log.e("视频的个数为", index + "");
                    for (int i = index; i < index + 2; i++) {
                        //a[i-index]=i;
                        Log.e("视频刷新", "Hhhhhhhhhhhh");
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            swiperefreshlayout.setRefreshing(false);
                            Toast.makeText(getActivity(), "已刷新完成", Toast.LENGTH_SHORT).show();
                        }
                    });
                    adapterVideoList.setA(a);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //处理视频全屏播放以后点击系统的后退键会直接退出Activity的问题
    private View.OnKeyListener backlistener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                backPress();
                return true;
            }
            return false;
        }
    };

    //这里进行网络请求最新的数据
    @Override
    public void onRefresh() {
        downLoadData();
    }
};

