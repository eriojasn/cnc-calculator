package com.simon.cncdistanccalculator3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayLengthsActivity extends ActionBarActivity {
	public final static String EXTRA_ABOUT = "com.simon.cncdistancecalculator.ABOUT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_lengths);
        
        // Get the message from the intent
        Intent intent = getIntent();
        String radiusS = intent.getStringExtra(GetAttributesActivity.EXTRA_RADIUS);
        String alphaS = intent.getStringExtra(GetAttributesActivity.EXTRA_ALPHA);
        String drawPointXS = intent.getStringExtra(GetAttributesActivity.EXTRA_DRAWPOINTX);
        String drawPointYS = intent.getStringExtra(GetAttributesActivity.EXTRA_DRAWPOINTY);
        double radiusD = Double.parseDouble(radiusS);
        double alphaD = Double.parseDouble(alphaS);
        double drawPointXD = Double.parseDouble(drawPointXS);
        double drawPointYD = Double.parseDouble(drawPointYS);
        String lineType = intent.getStringExtra(GetAttributesActivity.EXTRA_LINETYPE);
        int caseType = intent.getIntExtra(GetAttributesActivity.EXTRA_CASE, 0);
        
        double firstPointX = 0;
        double firstPointY = 0;
        double secondPointX = 0;
        double secondPointY = 0;
        
        double lengths[] = this.GetLengths(radiusD, alphaD);
        String lengthsS[] = {"","","",""};
        
        String firstPointXS = "";
        String firstPointYS = "";
        String secondPointXS = "";
        String secondPointYS = "";
        
        ImageView i = new ImageView(this);
        i = (ImageView)findViewById(R.id.selected_image);
        
        if(lineType.equals("horizontal"))
        {
        	if(caseType == 0)
        	{
        		firstPointX = drawPointXD + lengths[3];
        		firstPointY = drawPointYD;
        		secondPointX = drawPointXD - lengths[2];
        		secondPointY = drawPointYD - lengths[1];
        		i.setImageResource(R.drawable.h1);
        	}
        	if(caseType == 1)
        	{
          		firstPointX = drawPointXD - lengths[3];
    			firstPointY = drawPointYD;
    			secondPointX = drawPointXD + lengths[2];
    			secondPointY = drawPointYD - lengths[1];
        		i.setImageResource(R.drawable.h2);
        	}
        	if(caseType == 2)
        	{
          		firstPointX = drawPointXD + lengths[3];
    			firstPointY = drawPointYD;
    			secondPointX = drawPointXD - lengths[2];
    			secondPointY = drawPointYD + lengths[1];
        		i.setImageResource(R.drawable.h3);
        	}
        	if(caseType == 3)
        	{
          		firstPointX = drawPointXD - lengths[3];
    			firstPointY = drawPointYD;
    			secondPointX = drawPointXD + lengths[2];
    			secondPointY = drawPointYD + lengths[1];
        		i.setImageResource(R.drawable.h4);
        	}
        }
        else
        {
        	if(caseType == 0)
        		firstPointX = drawPointXD;
        		firstPointY = drawPointYD - lengths[3];
        		secondPointX = drawPointXD + lengths[1];
        		secondPointY = drawPointYD + lengths[2];
        		i.setImageResource(R.drawable.v1);
        	if(caseType == 1)
        		firstPointX = drawPointXD;
        		firstPointY = drawPointYD - lengths[3];
        		secondPointX = drawPointXD - lengths[1];
        		secondPointY = drawPointYD + lengths[2];
        		i.setImageResource(R.drawable.v2);
        	if(caseType == 2)
        		firstPointX = drawPointXD;
        		firstPointY = drawPointYD + lengths[3];
        		secondPointX = drawPointXD + lengths[1];
        		secondPointY = drawPointYD - lengths[2];
        		i.setImageResource(R.drawable.v3);
        	if(caseType == 3)
        		firstPointX = drawPointXD;
        		firstPointY = drawPointYD + lengths[3];
        		secondPointX = drawPointXD - lengths[1];
        		secondPointY = drawPointYD - lengths[2];
        		i.setImageResource(R.drawable.v4);
        }
        
        
        for(int j = 0; j < lengthsS.length; j++)
        {
        	lengthsS[j] = String.valueOf(lengths[j]);
        }
        
        firstPointXS = String.valueOf(firstPointX);
        firstPointYS = String.valueOf(firstPointY);
        secondPointXS = String.valueOf(secondPointX);
        secondPointYS = String.valueOf(secondPointY);
        
        
        TextView t = new TextView(this); 
        
        t=(TextView)findViewById(R.id.length_one); 
        t.setText(lengthsS[0]);
        
        t=(TextView)findViewById(R.id.length_two); 
        t.setText(lengthsS[1]);
        
        t=(TextView)findViewById(R.id.length_three); 
        t.setText(lengthsS[2]);
        
        t=(TextView)findViewById(R.id.length_four); 
        t.setText(lengthsS[3]);

        t=(TextView)findViewById(R.id.first_point_x); 
        t.setText(firstPointXS);

        t=(TextView)findViewById(R.id.first_point_y); 
        t.setText(firstPointYS);

        t=(TextView)findViewById(R.id.second_point_x); 
        t.setText(secondPointXS);

        t=(TextView)findViewById(R.id.second_point_y); 
        t.setText(secondPointYS);
        
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
    
    public double[] GetLengths(double radius, double alpha) {
    	double[] lengths = {0, 0, 0, 0};
    	
    	alpha = Math.toRadians(alpha);
    	
    	lengths[0] = radius * Math.cos(alpha);
    	lengths[1] = radius - lengths[0];
    	lengths[2] = lengths[1] / Math.tan(alpha);
    	lengths[3] = radius * Math.sin(alpha) - lengths[2];
    	
    	return lengths;
    }
    
    public void restart(View view) {
    	Intent intent = new Intent(this, GetLineActivity.class);

    	startActivity(intent);
    }
    
    public void showTitle(View view) {
    	Intent intent = new Intent(this, TitleActivity.class);
    	intent.putExtra(EXTRA_ABOUT, true);

    	startActivity(intent);
    }
}