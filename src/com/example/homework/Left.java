package com.example.homework;

import android.app.Fragment;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.view.ViewGroup;

public class Left extends Fragment implements OnClickListener{
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View view=inflater.inflate(R.layout.pass, container, false);
	Button bt1=(Button) view.findViewById(R.id.bt1);
	Button bt2=(Button) view.findViewById(R.id.bt2);
	Button bt3=(Button) view.findViewById(R.id.bt3);
	Button bt4=(Button) view.findViewById(R.id.bt4);
	Button bt5=(Button) view.findViewById(R.id.bt5);
	bt1.setOnClickListener(this);
	bt2.setOnClickListener(this);
	bt3.setOnClickListener(this);
	bt4.setOnClickListener(this);
	bt5.setOnClickListener(this);
	return view;
}

@Override
public void onClick(View v) {
	Intent intent=new Intent(getActivity(), MainActivity.class);
	switch (v.getId()) {
	case R.id.bt1:
		intent.putExtra("level", "1");
		break;
    case R.id.bt2:
    	intent.putExtra("level", "2");
		break;
    case R.id.bt3:
    	intent.putExtra("level", "3");
	    break;
    case R.id.bt4:
    	intent.putExtra("level", "4");
	    break;
    case R.id.bt5:
    	intent.putExtra("level", "5");
    	
	    break;
	default:
		break;
	}
	getActivity().finish();
	startActivity(intent);
}
}
