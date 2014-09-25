package com.simon.cncdistanccalculator3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

public class TitleActivity extends ActionBarActivity {
	private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        setContentView(R.layout.activity_title);
        
        Intent intent = getIntent();
        boolean isAbout = intent.getBooleanExtra(DisplayLengthsActivity.EXTRA_ABOUT, false);
        
        if(!isAbout){
	        thread = new Thread(){
	            @Override
	            public void run(){
	                try {
	                    synchronized(this){
	                        wait(3000);
	                    }
	                }
	                catch(InterruptedException ex){                    
	                }
	                Intent intent = new Intent(TitleActivity.this, GetLineActivity.class);
	            	startActivity(intent);              
	            }
	        };
        }
        else
        {
	        thread = new Thread(){
	            @Override
	            public void run(){
	                try {
	                    synchronized(this){
	                        wait(10000);
	                    }
	                }
	                catch(InterruptedException ex){                    
	                }
	                finish();              
	            }
	        };
        }

        thread.start(); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.get_line, menu);
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


@Override
public boolean onTouchEvent(MotionEvent evt)
{
    if(evt.getAction() == MotionEvent.ACTION_DOWN)
    {
        synchronized(thread){
            thread.notifyAll();
        }
    }
    return true;
}

}
