package com.example.frontlinerproject.adapter;

import java.util.List;

import com.example.frontlinerprojek.R;
import com.example.model.LatestQueueAlternated;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AntrianAdapter extends ArrayAdapter<LatestQueueAlternated> {
	private Context context;
	private List<LatestQueueAlternated> tickets;
	private LayoutInflater inflater = null;

	public AntrianAdapter(Context context, List<LatestQueueAlternated> tickets) {
		super(context, R.layout.row_antrian, tickets);

		this.context = context;
		this.tickets = tickets;
		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public LatestQueueAlternated getItem(int position) {
		return tickets.get(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
        if (tickets == null && tickets.size() <= 0) {
            return view;
        }
        if (view == null) {
        	view = inflater.inflate(R.layout.row_antrian, parent, false);
        	ViewHolder viewHolder = new ViewHolder();
        	viewHolder.nourut = (TextView) view.findViewById(R.id.nourut);
        	view.setTag(viewHolder);
        }
        
        LatestQueueAlternated latestQueueAlternated = getItem(position);
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.nourut.setText(latestQueueAlternated.getTicketNumber());
        if(position == 0){
        	viewHolder.nourut.setBackgroundResource(R.drawable.antrian_row_1);
        }
		return view;
	}
	
	static class ViewHolder {
        protected TextView nourut;
    }

}
