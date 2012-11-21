package com.otakutlan.trackingsepomex;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class ObtainTracking extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
    	String tn = intent.getStringExtra(MainTracker.EXTRA_TN);
    	String year = intent.getStringExtra(MainTracker.EXTRA_YEAR);
    	String url = "http://www.sepomex.gob.mx/lservicios/seguimientopiezas/emsportal.aspx?__EVENTTARGET=&__EVENTARGUMENT=&__VIEWSTATE=%2FwEPDwUKMTkyNDgzNzUyNA9kFgICAQ9kFgICAQ9kFgYCCQ8QZBAVAgQyMDEyBDIwMTEVAgQyMDEyBDIwMTEUKwMCZ2dkZAITD2QWAgIDDzwrAAsAZAIVDw8WAh4HVmlzaWJsZWhkZBgBBR5fX0NvbnRyb2xzUmVxdWlyZVBvc3RCYWNrS2V5X18WAwUHYnRuRmluZAUIYnRuY2xlYW4FB2J0bmhlbHBZ26NsMzIPVVEjPmAigNU6x1UwWw%3D%3D&__EVENTVALIDATION=%2FwEWBwKzyvzNCAKu853DBAKK1JX3AQKK1IHQCAL5zb65CwKtktUtAsm8xLUOBnW961DO0RKqrEwYbbUxjP0PkrI%3D&txtNGuia="+tn+"&cboanio="+year+"&btnFind.x=55&btnFind.y=15";
    	
    	Elements tabla = null;
    	String htmlResult = "<html><body>No se encontraron datos</body></html>"; 	
    	
    	try {
    		Document doc = Jsoup.connect(url).get();
			tabla = doc.select("table.dgrid");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, "Error en la conexion", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
		
		if (tabla != null && tabla.html().length() > 0)
    		htmlResult = "<html><body><table cellspacing=\"0\" cellpadding=\"4\" rules=\"all\" border=\"1\" id=\"dg\" style=\"background-color:White;border-width:1px;border-style:None;width:650px;border-collapse:collapse;\">"+tabla.html()+"</table></body></html>";
		
		TextView textView = new TextView(this);
    	textView.setTextSize(8);
    	textView.setText(htmlResult);    	
    	
    	WebView webView = new WebView(this);
    	webView.loadData(htmlResult, "text/html", "UTF-8");

    	setContentView(webView);
    }

}
