package com.simon.cncdistanccalculator3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class GetAttributesActivity extends ActionBarActivity {
	public final static String EXTRA_ALPHA = "com.simon.cncdistancecalculator.ALPHA";
    public final static String EXTRA_RADIUS = "com.simon.cncdistancecalculator.RADIUS";
    public final static String EXTRA_DRAWPOINTX = "com.simon.cncdistancecalculator.DRAWPOINTX";
    public final static String EXTRA_DRAWPOINTY = "com.simon.cncdistancecalculator.DRAWPOINTY";
	public final static String EXTRA_CASE = "com.simon.cncdistancecalculator.CASE";
	public final static String EXTRA_LINETYPE = "com.simon.cncdistancecalculator.LINETYPE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_attributes);
        
        Intent intent = getIntent();
        String lineType = intent.getStringExtra(GetCaseActivity.EXTRA_LINETYPE);
        int caseType = intent.getIntExtra(GetCaseActivity.EXTRA_CASE, 0);
       
        ImageView i = new ImageView(this);
        i = (ImageView)findViewById(R.id.selected_image);
        
        if(lineType.equals("horizontal"))
        {
        	if(caseType == 0)
        		i.setImageResource(R.drawable.h1);
        	if(caseType == 1)
        		i.setImageResource(R.drawable.h2);
        	if(caseType == 2)
        		i.setImageResource(R.drawable.h3);
        	if(caseType == 3)
        		i.setImageResource(R.drawable.h4);
        }
        else
        {
        	if(caseType == 0)
        		i.setImageResource(R.drawable.v1);
        	if(caseType == 1)
        		i.setImageResource(R.drawable.v2);
        	if(caseType == 2)
        		i.setImageResource(R.drawable.v3);
        	if(caseType == 3)
        		i.setImageResource(R.drawable.v4);
        }

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
 
    public void sendAttributes(View view) {
    	Context context = getApplicationContext();
    	CharSequence text = "Te faltaron unas medidas!";
    	int duration = Toast.LENGTH_SHORT;

    	Toast toast = Toast.makeText(context, text, duration);
    	toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 150);
    	
    	Intent intent = getIntent();
        String lineType = intent.getStringExtra(GetCaseActivity.EXTRA_LINETYPE);
        int caseType = intent.getIntExtra(GetCaseActivity.EXTRA_CASE, 0);
    	
    	EditText alpha = (EditText) findViewById(R.id.alpha);
    	EditText radius = (EditText) findViewById(R.id.radius);
    	EditText drawPointX = (EditText) findViewById(R.id.draw_point_x);
    	EditText drawPointY = (EditText) findViewById(R.id.draw_point_y);
    	
    	String alphaS = alpha.getText().toString();
    	String radiusS = radius.getText().toString();
    	String drawPointXS = drawPointX.getText().toString();
    	String drawPointYS = drawPointY.getText().toString();
    	
    	
    	intent = new Intent(this, DisplayLengthsActivity.class);
    	
    	intent.putExtra(EXTRA_ALPHA, alphaS);
    	intent.putExtra(EXTRA_RADIUS, radiusS);
    	intent.putExtra(EXTRA_LINETYPE, lineType);
    	intent.putExtra(EXTRA_CASE, caseType);
    	intent.putExtra(EXTRA_DRAWPOINTX, drawPointXS);
    	intent.putExtra(EXTRA_DRAWPOINTY, drawPointYS);
    	
    	if(alphaS.equals("") || radiusS.equals(""))
    		toast.show();
    	else
    		startActivity(intent);
    }

}
