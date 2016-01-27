package com.wpmac.zhihuiyun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wpmac.zhihuiyun.R;
import com.wpmac.zhihuiyun.Utils.Utils;
import com.wpmac.zhihuiyun.model.meterTypeBean;

import java.util.List;

/**
 * Created by wpmac on 16/1/21.
 */
public class MeterListviewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<meterTypeBean> list;
    public  MeterListviewAdapter(Context context,List<meterTypeBean> l ) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = l;
    }
    public void setDatasource(Context mContext, List<meterTypeBean> mList) {

        this.context = mContext;
        this.list = mList;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }




    /* (non-Javadoc)
     * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup arg2) {
        final ViewHolder viewHolder;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_metertype_listview, null);
            viewHolder = new ViewHolder();
            viewHolder.code= (TextView) convertView.findViewById(R.id.code);
            viewHolder.name= (TextView) convertView.findViewById(R.id.name);
            viewHolder.des= (TextView) convertView.findViewById(R.id.des);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(Utils.isValidValue(list.get(position).getCode())){
            viewHolder.code.setText("表计类型编码:"+list.get(position).getCode());
        }else{
            viewHolder.code.setText("表计类型编码:"+"");
        }
        if(Utils.isValidValue(list.get(position).getName())){
            viewHolder.name.setText("表计类型名称:"+list.get(position).getName());
        }else{
            viewHolder.name.setText("表计类型名称:"+"");
        }
        if(Utils.isValidValue(list.get(position).getDes())){
            viewHolder.des.setText("表计类型描述:"+list.get(position).getDes());
        }else{
            viewHolder.des.setText("表计类型描述:"+"");
        }
        return convertView;
    }

    public class ViewHolder {
        private TextView code,name,des;

    }
}
