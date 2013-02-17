package org.codebone;

import retrofit.http.RestAdapter;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

abstract public class RestActivity extends Activity {
	
	public final static String APISERVER = "http://192.168.0.41:8081/";
	protected RestAdapter restAdapter = new RestAdapter.Builder()
    .setServer(APISERVER)
    .build();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    }
    
    protected void run() {
    	new RestAsyncTask().execute();
    }
    
    abstract protected void beforeAsyncJob();
    abstract protected void doAsyncJob();
    abstract protected void afterAsyncJob();
    
    class RestAsyncTask extends AsyncTask<Void, Void, Void> {
    	
    	@Override
    	protected void onPreExecute() {
    		beforeAsyncJob();
    	}
    	
    	@Override
    	protected Void doInBackground(Void... params) {
    		doAsyncJob();
    		return null;
    	}
    	
    	@Override
    	protected void onPostExecute(Void param) {
    		afterAsyncJob();
    	}
    }
}
