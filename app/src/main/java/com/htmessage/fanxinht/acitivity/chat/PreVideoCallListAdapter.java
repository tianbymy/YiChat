package com.htmessage.fanxinht.acitivity.chat;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.htmessage.fanxinht.R;
import com.htmessage.fanxinht.domain.User;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 项目名称：YiChat
 * 类描述：PreVideoCallListAdapter 描述:
 * 创建人：songlijie
 * 创建时间：2017/3/14 13:56
 * 邮箱:814326663@qq.com
 */
public class PreVideoCallListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<User> beans = new ArrayList<>();
    private ArrayList<String> userIds = new ArrayList<>();
    // 用来控制CheckBox的选中状况
    private static HashMap<Integer, Boolean> isSelected;
    public PreVideoCallListAdapter(Context mContext, ArrayList<User> beans, ArrayList<String> userIds) {
        this.mContext = mContext;
        isSelected = new HashMap<Integer, Boolean>();
        this.beans = beans;
        this.userIds = userIds;
        initDate();
    }

    @Override
    public int getCount() {
        return beans.size();
    }
    // 初始化isSelected的数据
    private void initDate() {
        for (int i = 0; i < beans.size(); i++) {
            getIsSelected().put(i, false);
        }
    }
    @Override
    public User getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = View.inflate(mContext, R.layout.latout_pre_videocall_item,null);
            holder = new ViewHolder();
            holder.iv_game_avatar = (ImageView) convertView.findViewById(R.id.iv_game_avatar);
            holder.tv_username = (TextView) convertView.findViewById(R.id.tv_username);
            holder.checkbox = (CheckBox) convertView.findViewById(R.id.checkbox);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        User gameBean = beans.get(position);
        if (userIds.contains(gameBean.getUsername())){
            getIsSelected().put(position, true);
        }
        holder.tv_username.setText(gameBean.getNick());
        Glide.with(mContext).load(gameBean.getAvatar()).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.default_avatar).error(R.drawable.default_avatar).into(holder.iv_game_avatar);
        // 根据isSelected来设置checkbox的选中状况
        holder.checkbox.setChecked(getIsSelected().get(position));
        return convertView;
    }

    public static class ViewHolder{
         public TextView tv_username;
        public ImageView iv_game_avatar;
        public CheckBox checkbox;
    }
    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }
}
