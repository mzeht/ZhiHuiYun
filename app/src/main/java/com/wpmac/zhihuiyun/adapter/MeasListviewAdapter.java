package com.wpmac.zhihuiyun.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wpmac.zhihuiyun.R;
import com.wpmac.zhihuiyun.Utils.Utils;
import com.wpmac.zhihuiyun.model.measTypeBean;

import java.util.List;

/**
 * Created by wpmac on 16/1/21.
 */
public class MeasListviewAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<measTypeBean> list;
    public MeasListviewAdapter(Context context, List<measTypeBean> l ) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = l;
    }
    public void setDatasource(Context mContext, List<measTypeBean> mList) {

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
            convertView = inflater.inflate(R.layout.item_meastype_listview, null);
            viewHolder = new ViewHolder();
            viewHolder.code= (TextView) convertView.findViewById(R.id.code);
            viewHolder.name= (TextView) convertView.findViewById(R.id.name);
            viewHolder.des= (TextView) convertView.findViewById(R.id.des);
            viewHolder.unit= (TextView) convertView.findViewById(R.id.unit);
            viewHolder.unit_symbol= (TextView) convertView.findViewById(R.id.unit_symbol);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(Utils.isValidValue(list.get(position).getCode())){
            viewHolder.code.setText("量测类型编码:"+list.get(position).getCode());
        }else{
            viewHolder.code.setText("量测类型编码:"+"");
        }
        if(Utils.isValidValue(list.get(position).getName())){
            viewHolder.name.setText("量测类型名称:"+list.get(position).getName());
        }else{
            viewHolder.name.setText("量测类型名称:"+"");
        }
        if(Utils.isValidValue(list.get(position).getUnit())){
            viewHolder.unit.setText("量测类型单位名称:"+list.get(position).getUnit());
        }else{
            viewHolder.unit.setText("量测类型单位名称:"+"");
        }
        if(Utils.isValidValue(list.get(position).getUnit_symbol())){
            viewHolder.unit_symbol.setText("量测类型单位符号:"+list.get(position).getUnit_symbol());
        }else{
            viewHolder.unit_symbol.setText("量测类型单位符号:"+"");
        }
        if(Utils.isValidValue(list.get(position).getDes())){
            viewHolder.des.setText("类型描述:"+list.get(position).getDes());
        }else{
            viewHolder.des.setText("类型描述:"+"");
        }

        return convertView;
    }

    public class ViewHolder {
        private TextView code,name,des,unit,unit_symbol;

    }
}
