package com.prasad.poupularmoviesstage1.popularmoviesstage1;

import java.util.ArrayList;

import com.prasad.poupularmoviesstage1.popularmoviesstage1.data.MoviesData;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class MoviesDataAdapter extends BaseAdapter {

	private Context context;
	ArrayList<MoviesData> moviesDataArrayList;

	private class ViewHolder {
		ImageView gridIcon;
	}

	public MoviesDataAdapter(Context context, ArrayList<MoviesData> moviesDataArrayList) {

		this.moviesDataArrayList=moviesDataArrayList;
		this.context = context;

	}

	@Override
	public int getCount() {
		return moviesDataArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return moviesDataArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		MoviesData moviesData=moviesDataArrayList.get(position);

		ViewHolder holder = null;
		if (view == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.adapter_moviesdata,  parent,false);
			holder.gridIcon = (ImageView) view.findViewById(R.id.grid_icon);

			view.setTag(holder);

		} else
			holder = (ViewHolder) view.getTag();

		Logger.log("poster path::" + Constants.POSTERMAIN_PATH + moviesData.getPoster_path());

		Picasso.with(context).load(Constants.POSTERMAIN_PATH + moviesData.getPoster_path())
				.placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(holder.gridIcon);


//		Picasso.with(context).load("http://i.imgur.com/DvpvklR.png")
//				.placeholder(R.drawable.placeholder).error(R.drawable.placeholder).into(holder.gridIcon);


		return view;
	}
}
