package com.example.frontlinerproject.adapter;

import java.util.List;
import com.example.frontlinerprojek.R;
import com.example.model.GetListActiveServiceTypeByTicketCategoryUmumModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ServiceTypeAdapter extends ArrayAdapter<GetListActiveServiceTypeByTicketCategoryUmumModel>{
	
	private Context context;
	private List<GetListActiveServiceTypeByTicketCategoryUmumModel> serviceList;
	private LayoutInflater inflater = null;

	public ServiceTypeAdapter(Context context, List<GetListActiveServiceTypeByTicketCategoryUmumModel> serviceList) {
		super(context, R.layout.row_service_type, serviceList);
		
		this.context = context;
		this.serviceList = serviceList;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public GetListActiveServiceTypeByTicketCategoryUmumModel getItem(int position) {
		return serviceList.get(position);
	}
	
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		return getCustomView(position, convertView, parent);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
        if (serviceList == null && serviceList.size() <= 0) {
            return view;
        }
        if (view == null) {
        	view = inflater.inflate(R.layout.row_service_type, parent, false);
        	ViewHolder viewHolder = new ViewHolder();
        	viewHolder.textViewServiceType = (TextView) view.findViewById(R.id.textViewServiceType);
        	view.setTag(viewHolder);
        }
        
        GetListActiveServiceTypeByTicketCategoryUmumModel service = getItem(position);
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.textViewServiceType.setText(service.getName());
		return view;
	}
	
	public View getCustomView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
        if (serviceList == null && serviceList.size() <= 0) {
            return view;
        }
        if (view == null) {
        	view = inflater.inflate(R.layout.row_service_type, parent, false);
        	ViewHolder viewHolder = new ViewHolder();
        	viewHolder.textViewServiceType = (TextView) view.findViewById(R.id.textViewServiceType);
        	view.setTag(viewHolder);
        }
        
        GetListActiveServiceTypeByTicketCategoryUmumModel service = getItem(position);
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.textViewServiceType.setText(service.getName());
		return view;
	}
	
	static class ViewHolder {
        protected TextView textViewServiceType;
    }

}
