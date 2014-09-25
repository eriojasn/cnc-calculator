package com.simon.cncdistanccalculator3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class GetCaseActivity extends ActionBarActivity implements OnItemClickListener {
	public final static String EXTRA_CASE = "com.simon.cncdistancecalculator.CASE";
	public final static String EXTRA_LINETYPE = "com.simon.cncdistancecalculator.LINETYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_case);
        
     // Get the message from the intent
        Intent intent = getIntent();
        String lineType = intent.getStringExtra(GetLineActivity.EXTRA_LINETYPE);
        
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, lineType));
        gridview.setOnItemClickListener(this);
    }
    
    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) { 
    	Intent previousIntent = getIntent();
        String lineType = previousIntent.getStringExtra(GetLineActivity.EXTRA_LINETYPE);
      
    	Intent intent = new Intent(this, GetAttributesActivity.class);
    	intent.putExtra(EXTRA_CASE, position);
    	intent.putExtra(EXTRA_LINETYPE, lineType);
    	
    	startActivity(intent);
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
}
