package com.gzfgeh.customwheelview;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.gzfgeh.customwheelview.WheelView.OnWheelChangedListener;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TimeSelectWheelView extends RelativeLayout implements OnWheelChangedListener {
	private RelativeLayout titleView;
	@SuppressWarnings("unused")
	private TextView titleLeftText;
	private TextView titleYearText;
	private TextView titleMonthText;
	private TextView titleDayText;
	private LinearLayout wheelViews;
	private WheelView wheelYear;
	private WheelView wheelMonth;
	private WheelView wheelDay;
	private String[] years = new String[100];
	private String[] months = new String[12];
	private String[] tinyDays = new String[29];
	private String[] smallDays = new String[30];
	private String[] bigDays = new String[31];
	private String currentYear;
	private String currentMonth;
	private String currentDay;
	private StrericWheelAdapter yearsAdapter;
	private StrericWheelAdapter monthsAdapter;
	private StrericWheelAdapter tinyDaysAdapter;
	private StrericWheelAdapter smallDaysAdapter;
	private StrericWheelAdapter bigDaysAdapter;
	
	public TimeSelectWheelView(Context context){
		super(context);
		initLayout(context);
	}
	
	public TimeSelectWheelView(Context context, AttributeSet attrs){
		super(context, attrs);
		initLayout(context);
	}
	public TimeSelectWheelView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initLayout(context);
	}

	private void initLayout(Context context) {
		// TODO Auto-generated method stub
		LayoutInflater.from(context).inflate(R.layout.time_select_layout, this, true);
		titleView = (RelativeLayout) findViewById(R.id.time_select_title);
		titleLeftText = (TextView) findViewById(R.id.time_select_left_text);
		titleYearText = (TextView) findViewById(R.id.time_select_year_text);
		titleMonthText = (TextView) findViewById(R.id.time_select_month_text);
		titleDayText = (TextView) findViewById(R.id.time_select_day_text);
		wheelViews = (LinearLayout) findViewById(R.id.time_select_wheel_views);
		wheelYear = (WheelView) findViewById(R.id.time_select_wheel_year);
		wheelMonth = (WheelView) findViewById(R.id.time_select_wheel_month);
		wheelDay = (WheelView) findViewById(R.id.time_select_wheel_day);
		wheelYear.addChangingListener(this);
		wheelMonth.addChangingListener(this);
		wheelDay.addChangingListener(this);
		setInitData();
	}

	@SuppressLint("SimpleDateFormat") private void setInitData() {
		// TODO Auto-generated method stub
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date currentDate = new Date(System.currentTimeMillis());
		String date = format.format(currentDate);
		String[] times = date.split("-");
		currentYear = times[0];
		currentMonth = times[1];
		currentDay = times[2];
		
		for (int i = 0; i < years.length; i++) {
			years[i] = 1960 + i + " 年";
		}
		for (int i = 0; i < months.length; i++) {
			months[i] = 1 + i + " 月";
		}
		for (int i = 0; i < tinyDays.length; i++) {
			tinyDays[i] = 1 + i + " 日";
		}
		for (int i = 0; i < smallDays.length; i++) {
			smallDays[i] = 1 + i + " 日";
		}
		for (int i = 0; i < bigDays.length; i++) {
			bigDays[i] = 1 + i + " 日";
		}
		yearsAdapter = new StrericWheelAdapter(years);
		monthsAdapter = new StrericWheelAdapter(months);
		tinyDaysAdapter = new StrericWheelAdapter(tinyDays);
		smallDaysAdapter = new StrericWheelAdapter(smallDays);
		bigDaysAdapter = new StrericWheelAdapter(bigDays);
		wheelYear.setAdapter(yearsAdapter);
		wheelYear.setCurrentItem(Integer.valueOf(currentYear) - 1960);
		wheelYear.setCyclic(true);
		wheelMonth.setAdapter(monthsAdapter);
		wheelMonth.setCurrentItem(Integer.valueOf(currentMonth) - 1);
		wheelMonth.setCyclic(true);
		wheelDay.setAdapter(smallDaysAdapter);
		wheelDay.setCurrentItem(Integer.valueOf(currentDay) - 1);
		wheelDay.setCyclic(true);
		titleYearText.setText(wheelYear.getCurrentItemValue().split(" ")[0]);
		titleMonthText.setText(wheelMonth.getCurrentItemValue().split(" ")[0]);
		titleDayText.setText(wheelDay.getCurrentItemValue().split(" ")[0]);
	}
	
	
	public void setCurrentYear(String currentYear) {
		wheelYear.setCurrentItem(Integer.valueOf(currentYear) - 1960);
	}

	public void setCurrentMonth(String currentMonth) {
		wheelMonth.setCurrentItem(Integer.valueOf(currentMonth) - 1);
	}

	public void setCurrentDay(String currentDay) {
		wheelDay.setCurrentItem(Integer.valueOf(currentDay) - 1);
	}
	
	public String getSelectTime(){
		return titleYearText.getText().toString().trim() + "-" +
				titleMonthText.getText().toString().trim() + "-" +
				 titleDayText.getText().toString().trim();
	}
	
	public void setTitleClick(OnClickListener listener){
		titleView.setOnClickListener(listener);
	}
	
	public void setWheelViewsVisibility(int visibility){
		wheelViews.setVisibility(visibility);
	}
	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		// TODO Auto-generated method stub
		String trim = null;
		switch (wheel.getId()) {
		case R.id.time_select_wheel_year:
			trim = wheelYear.getCurrentItemValue().trim();
			trim = trim.split(" ")[0];
			titleYearText.setText(trim);
			if (isLeapYear(trim)) {
				if (Integer.valueOf(currentMonth) == 2) {
					wheelDay.setAdapter(tinyDaysAdapter);
				} else if(isBigMonth(Integer.valueOf(currentMonth))){
					wheelDay.setAdapter(bigDaysAdapter);
				} else {
					wheelDay.setAdapter(smallDaysAdapter);
				}
			} else if(isBigMonth(Integer.valueOf(currentMonth))){
				wheelDay.setAdapter(bigDaysAdapter);
			} else {
				wheelDay.setAdapter(smallDaysAdapter);
			}
			break;
			
		case R.id.time_select_wheel_month:
			currentMonth = wheelMonth.getCurrentItemValue().trim();
			currentMonth = currentMonth.split(" ")[0];
			titleMonthText.setText(currentMonth);
			switch (Integer.valueOf(currentMonth)) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				wheelDay.setAdapter(bigDaysAdapter);
				break;
			case 2:
				String yearString = wheelYear.getCurrentItemValue().trim();
				yearString = yearString.split(" ")[0];
				
				if (isLeapYear(yearString)) {
					wheelDay.setAdapter(tinyDaysAdapter);
				} else {
					wheelDay.setAdapter(smallDaysAdapter);
				}
				break;
				
			default:
				wheelDay.setAdapter(smallDaysAdapter);
				break;
			}
			break;
		case R.id.time_select_wheel_day:
			titleDayText.setText(wheelDay.getCurrentItemValue().trim().split(" ")[0]);
			break;
		}
	}

	private boolean isBigMonth(int month){
		boolean isBigMonth = false;
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			isBigMonth = true;
			break;

		default:
			isBigMonth = false;
			break;
		}
		return isBigMonth;
	}
	private boolean isLeapYear(String year) {
		int temp = Integer.parseInt(year);
		return temp % 4 == 0 ? true : false;
	}

}
