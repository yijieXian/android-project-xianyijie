package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ngag.ngag.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import utils.VideoConstant;

/**
 * Created by steven on 2017/7/10.
 */


public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;  //普通Item View
    private static final int TYPE_FOOTER = 1;  //底部FootView
//    int[] videoIndexs = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    int []videoIndexs=new int [VideoConstant.videoUrls[0].length];

    int[] a = {};

    private int load_more_status = 0;//初始化加载时的状态
    public static final int PULL_LOAD_MORE = 0;
    public static final int LOADING_MORE = 1;
    private Context context;

    public MyRecyclerViewAdapter(Context context) {
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_videoview, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(context).inflate(R.layout.foot_view, parent, false);
            FootViewHolder footViewHolder = new FootViewHolder(view);
            return footViewHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {

            ((MyViewHolder) holder).jcVideoPlayer.setUp(
                    VideoConstant.videoUrls[0][position], JCVideoPlayer.SCREEN_LAYOUT_LIST,
                    VideoConstant.videoTitles[0][position]);

//            通过url解析网络图片
            Picasso.with(((MyViewHolder) holder).jcVideoPlayer.getContext())
                    .load(VideoConstant.videoThumbs[0][position])
                    .into(((MyViewHolder) holder).jcVideoPlayer.thumbImageView);

//            ((MyViewHolder)holder).jcVideoPlayer.thumbImageView.setImageResource(R.drawable.notyet);
            //通过视频Url地址自动获取视频的截图
//            Bitmap bitmap=getNetVideoBitmap( VideoConstant.videoUrls[0][position]);
//            ((MyViewHolder)holder).jcVideoPlayer.thumbImageView.setImageBitmap(bitmap);

        } else if (holder instanceof FootViewHolder) {
            //上拉时候的两种状态 尚未实现
//            ((FootViewHolder) holder).foot_viem_item_layout.setVisibility(View.VISIBLE);
//             switch(load_more_status) {
//                case PULL_LOAD_MORE:
//                    ((FootViewHolder)holder).foot_view_item_tv.setText("上拉可加载更多");
//                    break;
//                 case LOADING_MORE:
//                     ((FootViewHolder)holder).progressBar.setVisibility(View.VISIBLE);
//                     ((FootViewHolder)holder).foot_view_item_tv.setText("正在加载");
//                     break;
//
//            }
            ((FootViewHolder) holder).progressBar.setVisibility(View.VISIBLE);
            ((FootViewHolder) holder).foot_view_item_tv.setText("loading");
        }
    }



    public void setA(int[] a) {
        this.a = a;
    }


    //获取RecyclerView 的Item View的个数
    @Override
    public int getItemCount() {
        return videoIndexs.length + 1;//加了个底部VIEW
    }


    //添加底部布局 用于上拉加载时显示
    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        JCVideoPlayerStandard jcVideoPlayer;

        public MyViewHolder(View itemView) {
            super(itemView);
            jcVideoPlayer = (JCVideoPlayerStandard) itemView.findViewById(R.id.videoplayer);
        }
    }

    class FootViewHolder extends RecyclerView.ViewHolder {
        private TextView foot_view_item_tv;
        private LinearLayout foot_viem_item_layout;
        private ProgressBar progressBar;

        public FootViewHolder(View view) {
            super(view);
            foot_viem_item_layout = (LinearLayout) view.findViewById(R.id.footViewLayout);
            foot_view_item_tv = (TextView) view.findViewById(R.id.text);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        }
    }
    public static Bitmap getNetVideoBitmap(String VedioUrl){
        Bitmap bitmap=null;
        MediaMetadataRetriever retriever=new MediaMetadataRetriever();

        try{
            retriever.setDataSource(VedioUrl,new HashMap());
            bitmap=retriever.getFrameAtTime();
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }finally {
            retriever.release();
        }
        return bitmap;
    }   ;
}