 package com.mikemiller.scrollsync;

import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements ScrollViewListener {


    TextView boxOne;
    TextView boxTwo;
    TextView boxThree;
    
    private ObservableScrollView scrollView1 = null;
    private ObservableScrollView scrollView2 = null;
    private ObservableScrollView scrollView3 = null;
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		
		boxOne = (TextView)findViewById(R.id.TEXT_ONE_ID);
		boxTwo = (TextView)findViewById(R.id.TEXT_TWO_ID);
		boxThree = (TextView)findViewById(R.id.TEXT_THREE_ID);
		

        scrollView1 = (ObservableScrollView) findViewById(R.id.SCROLLER_ONE_ID);
        scrollView1.setScrollViewListener(this);
        scrollView2 = (ObservableScrollView) findViewById(R.id.SCROLLER_TWO_ID);
        scrollView2.setScrollViewListener(this);
        scrollView3 = (ObservableScrollView) findViewById(R.id.SCROLLER_THREE_ID);
        scrollView3.setScrollViewListener(this);
	
        loadDocs();
	}
	
	private String getAssetString(String fileName)
	{
        try {
    		AssetManager am = getAssets();
            BufferedReader br = new BufferedReader(new InputStreamReader(am.open(fileName),"ISO-8859-1"));
            
            String text = "";
            String line;
            while((line = br.readLine()) != null)
                text = text + line + "\n";
            br.close();
             
             return text;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		return "";
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void loadDocs(){
		boxOne.setText(getAssetString("example_english"));
		boxTwo.setText(getAssetString("example_german"));
		boxThree.setText(getAssetString("example_translated"));
    }

    public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
    	
        if(scrollView != scrollView1) {
            scrollView1.scrollTo(x, y);
        }
        if(scrollView != scrollView2) {
            scrollView2.scrollTo(x, y);
        }
        if(scrollView != scrollView3) {
            scrollView3.scrollTo(x, y);
        }
    }

}
