package com.example.frontlinerproject.adapter;

import java.util.List;
import com.example.frontlinerprojek.R;
import com.example.model.GetListActiveServiceGroupByTicketCategoryModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ServiceGroupAdapter extends ArrayAdapter<GetListActiveServiceGroupByTicketCategoryModel>{
	
	private Context context;
	private List<GetListActiveServiceGroupByTicketCategoryModel> serviceList;
	private LayoutInflater inflater = null;

	public ServiceGroupAdapter(Context context, List<GetListActiveServiceGroupByTicketCategoryModel> serviceList) {
		super(context, R.layout.row_service_group, serviceList);
		
		this.context = context;
		this.serviceList = serviceList;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public GetListActiveServiceGroupByTicketCategoryModel getItem(int position) {
		return serviceList.get(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
        if (serviceList == null && serviceList.size() <= 0) {
            return view;
        }
        if (view == null) {
        	view = inflater.inflate(R.layout.row_service_group, parent, false);
        	ViewHolder viewHolder = new ViewHolder();
        	viewHolder.textViewServiceGroup = (TextView) view.findViewById(R.id.textViewServiceGroup);
        	view.setTag(viewHolder);
        }
        
        GetListActiveServiceGroupByTicketCategoryModel service = getItem(position);
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.textViewServiceGroup.setText(service.getName());
		return view;
	}
	
	public View getCustomView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
        if (serviceList == null && serviceList.size() <= 0) {
            return view;
        }
        if (view == null) {
        	view = inflater.inflate(R.layout.row_service_group, parent, false);
        	ViewHolder viewHolder = new ViewHolder();
        	viewHolder.textViewServiceGroup = (TextView) view.findViewById(R.id.textViewServiceGroup);
        	view.setTag(viewHolder);
        }
        
        GetListActiveServiceGroupByTicketCategoryModel service = getItem(position);
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.textViewServiceGroup.setText(service.getName());
		return view;
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}
	
	static class ViewHolder {
        protected TextView textViewServiceGroup;
    }

}
