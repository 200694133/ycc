package com.Ycc.Calculator.View;

import com.Ycc.Calculator.R;
import com.Ycc.Calculator.Util.MathUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * start页面
 * @author YY
 *
 */
public class StartActivity extends Activity {
	//清除、删除、乘、除、减、加、等于、点
	private Button calulator_keyboard_clear;
	private Button calulator_keyboard_del;
	private Button calulator_keyboard_multiply;
	private Button calulator_keyboard_divide;
	private Button calulator_keyboard_sub;
	private Button calulator_keyboard_add;
	private Button calulator_keyboard_equal;
	private Button calulator_keyboard_point;
	//数字 0-9
	private Button calulator_keyboard_zero;
	private Button calulator_keyboard_one;
	private Button calulator_keyboard_two;
	private Button calulator_keyboard_three;
	private Button calulator_keyboard_four;
	private Button calulator_keyboard_five;
	private Button calulator_keyboard_six;
	private Button calulator_keyboard_seven;
	private Button calulator_keyboard_eight;
	private Button calulator_keyboard_nine;
	
	private TextView calulator_view_text;
	private TextView calulator_view;
	private String expression = "";//用于保存表达式
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setContentView(R.layout.start_view);
		super.onCreate(savedInstanceState);
		iniWidght();
	}
	
	/**
	 * 初始化控件
	 */
	private void iniWidght(){
		//初始化算术按钮
		calulator_keyboard_clear = (Button) findViewById(R.id.calulator_keyboard_clear);
		calulator_keyboard_del = (Button) findViewById(R.id.calulator_keyboard_del);
		calulator_keyboard_multiply = (Button) findViewById(R.id.calulator_keyboard_multiply);
		calulator_keyboard_divide = (Button) findViewById(R.id.calulator_keyboard_divide);
		calulator_keyboard_sub = (Button) findViewById(R.id.calulator_keyboard_sub);
		calulator_keyboard_add = (Button) findViewById(R.id.calulator_keyboard_add);
		calulator_keyboard_equal = (Button) findViewById(R.id.calulator_keyboard_equal);
		calulator_keyboard_point = (Button) findViewById(R.id.calulator_keyboard_point);
		
		//初始化数字按钮
		calulator_keyboard_zero = (Button) findViewById(R.id.calulator_keyboard_zero);
		calulator_keyboard_one = (Button) findViewById(R.id.calulator_keyboard_one);
		calulator_keyboard_two = (Button) findViewById(R.id.calulator_keyboard_two);
		calulator_keyboard_three = (Button) findViewById(R.id.calulator_keyboard_three);
		calulator_keyboard_four = (Button) findViewById(R.id.calulator_keyboard_four);
		calulator_keyboard_five = (Button) findViewById(R.id.calulator_keyboard_five);
		calulator_keyboard_six = (Button) findViewById(R.id.calulator_keyboard_six);
		calulator_keyboard_seven = (Button) findViewById(R.id.calulator_keyboard_seven);
		calulator_keyboard_eight = (Button) findViewById(R.id.calulator_keyboard_eight);
		calulator_keyboard_nine = (Button) findViewById(R.id.calulator_keyboard_nine);
		
		calulator_view_text = (TextView) findViewById(R.id.calulator_view_text);
		calulator_view = (TextView) findViewById(R.id.calulator_view);
		
		//创建监听事件
		calulator_keyboard_clear.setOnClickListener(new ButtonOnclick());
		calulator_keyboard_del.setOnClickListener(new ButtonOnclick());
		calulator_keyboard_multiply.setOnClickListener(new ButtonOnclick());
		calulator_keyboard_divide.setOnClickListener(new ButtonOnclick());
		calulator_keyboard_sub.setOnClickListener(new ButtonOnclick());
		calulator_keyboard_add.setOnClickListener(new ButtonOnclick());
		calulator_keyboard_equal.setOnClickListener(new ButtonOnclick());
		calulator_keyboard_point.setOnClickListener(new ButtonOnclick());
		calulator_keyboard_zero.setOnClickListener(new ButtonOnclick());
		calulator_keyboard_one.setOnClickListener(new ButtonOnclick());
		calulator_keyboard_two.setOnClickListener(new ButtonOnclick());
		calulator_keyboard_three.setOnClickListener(new ButtonOnclick());
		calulator_keyboard_four.setOnClickListener(new ButtonOnclick());
		calulator_keyboard_five.setOnClickListener(new ButtonOnclick());
		calulator_keyboard_six.setOnClickListener(new ButtonOnclick());
		calulator_keyboard_seven.setOnClickListener(new ButtonOnclick());
		calulator_keyboard_eight.setOnClickListener(new ButtonOnclick());
		calulator_keyboard_nine.setOnClickListener(new ButtonOnclick());
		
		iniData();
	}
	
	/**
	 * 初始化数据
	 */
	private void iniData(){
		
	}
	
	/**
	 * 按钮监听事件
	 * @author YY
	 *
	 */
	class ButtonOnclick implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.calulator_keyboard_clear:
				expression="";
				calulator_view_text.setText(expression);
				break;
			case R.id.calulator_keyboard_del:
				int len = expression.length();
				if(len>0 && !calulator_view.getText().toString().equals("")){
				expression = expression.substring(0, len-1);
				calulator_view.setText(expression);
				}
				break;
			case R.id.calulator_keyboard_multiply:
				expression+="*";
				calulator_view.setText(expression);
				break;
			case R.id.calulator_keyboard_divide:
				expression+="/";
				calulator_view.setText(expression);
				break;
			case R.id.calulator_keyboard_sub:
				expression+="-";
				calulator_view.setText(expression);
				break;
			case R.id.calulator_keyboard_add:
				expression+="+";
				calulator_view.setText(expression);
				break;
			case R.id.calulator_keyboard_equal:
				calulator_view_text.setText(MathUtil.mixOperation(expression));
				expression = MathUtil.mixOperation(expression);
				calulator_view.setText("");
				break;
			case R.id.calulator_keyboard_point:
				expression+=".";
				calulator_view.setText(expression);
				break;
			case R.id.calulator_keyboard_zero:
				expression+=0;
				calulator_view.setText(expression);
				break;
			case R.id.calulator_keyboard_one:
				expression+=1;
				calulator_view.setText(expression);
				break;
			case R.id.calulator_keyboard_two:
				expression+=2;
				calulator_view.setText(expression);
				break;
			case R.id.calulator_keyboard_three:
				expression+=3;
				calulator_view.setText(expression);
				break;
			case R.id.calulator_keyboard_four:
				expression+=4;
				calulator_view.setText(expression);
				break;
			case R.id.calulator_keyboard_five:
				expression+=5;
				calulator_view.setText(expression);
				break;
			case R.id.calulator_keyboard_six:
				expression+=6;
				calulator_view.setText(expression);
				break;
			case R.id.calulator_keyboard_seven:
				expression+=7;
				calulator_view.setText(expression);
				break;
			case R.id.calulator_keyboard_eight:
				expression+=8;
				calulator_view.setText(expression);
				break;
			case R.id.calulator_keyboard_nine:
				expression+=9;
				calulator_view.setText(expression);
				break;
			}
		}
		
	}
}
