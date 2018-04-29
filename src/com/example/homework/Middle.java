package com.example.homework;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Middle extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.home, container, false);
		Button start=(Button) view.findViewById(R.id.start);
		Button exit=(Button) view.findViewById(R.id.exit);
		start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getActivity(),MainActivity.class);
				getActivity().finish();
			    startActivity(intent);
			}
		});
		exit.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				getActivity().finish();
			}
		});
		return view;
	}

}
