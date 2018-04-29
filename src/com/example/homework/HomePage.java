package com.example.homework;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomePage extends Activity implements OnClickListener{
	private Middle middle;
	private Left left;
	private Right right;
	private Button m;
	private Button l;
	private Button r;
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);
	m=(Button) findViewById(R.id.home);
	l=(Button) findViewById(R.id.level);
	r=(Button) findViewById(R.id.about);
	m.setOnClickListener(this);
	l.setOnClickListener(this);
	r.setOnClickListener(this);
	FragmentManager fm = getFragmentManager();
	FragmentTransaction transaction = fm.beginTransaction();
	middle = new Middle();
	transaction.replace(R.id.id_content, middle);
	transaction.commit();
}

@Override
public void onClick(View v) {
	FragmentManager fm = getFragmentManager();
	FragmentTransaction transaction = fm.beginTransaction();
	switch (v.getId()) {
	case R.id.home:
		if(middle==null){
			middle=new Middle();
		}
		transaction.replace(R.id.id_content, middle);
		break;
    case R.id.level:
		if (left==null) {
			left=new Left();
		}
		transaction.replace(R.id.id_content, left);
		break;
    case R.id.about:
		if(right==null){
			right=new Right();
		}
		transaction.replace(R.id.id_content, right);
		break;
	default:
		break;
	}
    
	transaction.commit();
}
}

