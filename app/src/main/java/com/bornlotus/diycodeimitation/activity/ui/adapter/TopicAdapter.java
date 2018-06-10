package com.bornlotus.diycodeimitation.activity.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bornlotus.diycodeimitation.R;
import com.bornlotus.diycodeimitation.activity.api.module.topic.Topic;
import com.bornlotus.diycodeimitation.activity.utils.ImageUtils;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {

    private List<Topic> mTopics;
    private Context mContext;

    public TopicAdapter(Context context, List<Topic> topics) {
        this.mTopics = topics;
        this.mContext = context;
    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopicViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_topic_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(TopicViewHolder holder, int position) {
        Topic topic = mTopics.get(position);
        holder.nodeName.setText(topic.getNode_name());
        holder.userName.setText(topic.getUser().getName());
        holder.title.setText(topic.getTitle());
        ImageUtils.loadImage(mContext, topic.getUser().getAvatar_url(), holder.avatar);
        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击条目", Toast.LENGTH_SHORT).show();
            }
        });
        holder.nodeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击Node_Name", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTopics.size();
    }

    class TopicViewHolder extends RecyclerView.ViewHolder {

        private View itemLayout;
        private ImageView avatar;
        private TextView userName;
        private TextView point;
        private TextView time;
        private View contentWrapper;
        private TextView title;
        private TextView preview;
        private TextView nodeName;
        private TextView state;
        private ImageView collection;
        private ImageView like;

        public TopicViewHolder(View itemView) {
            super(itemView);
            itemLayout = itemView.findViewById(R.id.item);
            avatar = itemView.findViewById(R.id.avatar);
            userName = itemView.findViewById(R.id.username);
            point = itemView.findViewById(R.id.point);
            time = itemView.findViewById(R.id.time);
            contentWrapper = itemView.findViewById(R.id.content_wrap);
            title = itemView.findViewById(R.id.title);
            preview = itemView.findViewById(R.id.preview);
            nodeName = itemView.findViewById(R.id.node_name);
            state = itemView.findViewById(R.id.state);
            collection = itemView.findViewById(R.id.collection);
            like = itemView.findViewById(R.id.like);
        }
    }

}
