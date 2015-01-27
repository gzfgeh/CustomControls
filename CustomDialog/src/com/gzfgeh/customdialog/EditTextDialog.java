package com.gzfgeh.customdialog;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gzfgeh.customdialog.R;

@SuppressLint({ "InlinedApi", "InflateParams" }) public class EditTextDialog extends CustomDialog implements View.OnClickListener{
	private Context context;
	@SuppressWarnings("unused")
	private View view;
	private RelativeLayout editTextDialogLayout;
	@SuppressWarnings("unused")
	private EditText editText;
	
	public EditTextDialog(Context context, View view){
		super(context);
		this.context = context;
		this.view = view;
		initView();
	}
	
	public EditTextDialog(Context context, int theme, View view) {
		super(context, theme);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.view = view;
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		editTextDialogLayout = (RelativeLayout) LayoutInflater.from(context).inflate(
				R.layout.edit_text_dialog, null);
		editText = (EditText) editTextDialogLayout.findViewById(R.id.edit_text);
		setEditTextDialogProperties();
	}

	private void setEditTextDialogProperties() {
		// TODO Auto-generated method stub
		this.setCustomDialogWindow(800, LayoutParams.WRAP_CONTENT)
			.setCustomDialogBackground("#FFFFFF")
			.setCustomDialogTitleVisibility(View.VISIBLE)
			.setCustomDialogTitleCenterText("Dialog")
			.setCustomDialogCenterMessageDefaultVisibility(View.GONE)
			.setCustomDialogMsgVisibility(View.VISIBLE)
			.setCustomDialogMessageView(editTextDialogLayout, context)
			.setCustomDialogBottomLeftBtnVisibility(View.VISIBLE)
			.setCustomDialogBottomLeftBtnText("确定")
			.setCustomDialogBottomLeftBtnClick(this)
			.setCustomDialogBottomRightBtnVisibility(View.VISIBLE)
			.setCustomDialogBottomRightBtnText("取消")
			.setCustomDialogBottomRightBtnClick(this)
			.setCustomDialogDisappearOnTouchOutside(false);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.custom_dialog_bottom_left_btn:
			Toast.makeText(context, "click left", Toast.LENGTH_SHORT).show();
			break;
		case R.id.custom_dialog_bottom_right_btn:
			Toast.makeText(context, "click right", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
		this.dismiss();
	}
}
