package com.mugwumps.stackapp;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import com.mugwumps.stackapp.*;

import java.util.*;
import java.util.Arrays;

@SuppressWarnings("deprecation")
public class StackApp extends Activity {

	int item;
	private EditText digitField;
	public int digit;
	private String stackString;
	TextView stackDisplay;
	Button Pushbutton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stack_app);
		
		Pushbutton=(Button)findViewById(R.id.button1);
		stackDisplay = (TextView)findViewById(R.id.StackContents);
		
		
		Pushbutton.setOnClickListener(onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener(){
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			digitField = (EditText)findViewById(R.id.editText1);
			digit = Integer.parseInt(digitField.getText().toString());
			push(digit);
			stackString = view(); 
			stackDisplay.setText(stackString);
			Toast toast = new Toast (getApplicationContext());
			toast.setGravity(Gravity.TOP, 0, 0);
			toast.makeText(StackApp.this, InfoMessage, toast.LENGTH_SHORT).show();
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.stack_app, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	//SECTION WHERE ALL THE METHODS ARE IMPLEMENTED
	private int maxSize = 3;
	public Object [] stack = new Object[maxSize]; 
	public int top = 0;
	public String InfoMessage;
	//haven't tested this
	public void push(Object item) {
		if (top == stack.length){
			InfoMessage = "Stack is full";
			return;
			//throw new IllegalStateException("Stack is full");
		}
		if (Integer.parseInt(item.toString()) >= 10){
			InfoMessage = "Use only single digits";
			//throw new IllegalStateException("Use only single digits");
			return;
		}
		else{
			stack[top++] = item;
			InfoMessage = String.valueOf(item) + " is pushed to the stack";
			return;
		}
	}
	//haven't tested this.
	public String view() {
		StringBuffer SB = new StringBuffer();
		SB.append(" [ ");
		for (int i = 0; i < maxSize; i++){
			if(stack[i] == null)
				SB.append("_ ");
			else
				SB.append(stack[i] + " ");
		}
		SB.append("] ");
		return SB.toString();
	}
	
	public boolean isEmpty() {
		return top == 0;
	}
	
}
