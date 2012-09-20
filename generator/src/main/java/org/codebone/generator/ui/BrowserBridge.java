package org.codebone.generator.ui;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

public class BrowserBridge extends BrowserFunction {
	
	private static final String receiveFunctionName = "sendDataToNativeApplication";
	private static final String sendFunctionName = "receiveDataFromNativeApplication";
	
	private Browser browser;
	private ScriptCallListner scriptCallListner;
	
	BrowserBridge (Browser browser, ScriptCallListner scriptCallListner) {
        super (browser, receiveFunctionName);
        this.browser = browser;
        this.scriptCallListner = scriptCallListner;
    }
	
    public Object function (Object[] arguments) {
        scriptCallListner.call(arguments);
        return null;
    }
    
    public void callScript(String operation, String data) {
    	String script = sendFunctionName + "('"+operation+"','"+data+"');";
    	boolean result = browser.execute(script);
    	if (!result) {
			System.out.println("Script was not executed.");
		}
    }
    
    public interface ScriptCallListner {
    	public void call(Object[] arguments);
    }
}
