package com.gzfgeh.customdialog;

import com.gzfgeh.customdialog.EditTextDialog.OnSureClickListener;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements OnSureClickListener {
	private EditTextDialog dialog;
	private OnSureClickListener onSureClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    
    public void onClick(View view){
    	if (null == dialog){
    		dialog = new EditTextDialog(this, view);
    		dialog.setOnSureClickListener(this);
    	}
    	dialog.show();
    }


	@Override
	public void setOnSureClickListener(EditText editText, View view) {
		// TODO Auto-generated method stub
		Toast.makeText(this, editText.getText().toString(), Toast.LENGTH_SHORT).show();
	}
}
