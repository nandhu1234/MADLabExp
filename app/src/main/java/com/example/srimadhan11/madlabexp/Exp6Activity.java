package com.example.srimadhan11.madlabexp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Exp6Activity extends AppCompatActivity {
    private Activity activity = this;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exp6);

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                ArrayList<String> headlines = new ArrayList<>();
                ArrayList<String> links = new ArrayList<>();
                String URL = "http://feed.androidauthority.com/";

                try {
                    URL url = new URL(URL);
                    URLConnection connection = url.openConnection();

                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(false);
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput(connection.getInputStream(), "UTF_8");
                    boolean insideItem = false;
                    int eventType = xpp.getEventType();

                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        if (eventType == XmlPullParser.START_TAG) {
                            if (xpp.getName().equalsIgnoreCase("item")) {
                                insideItem = true;
                            } else if (xpp.getName().equalsIgnoreCase("title")) {
                                if (insideItem)
                                    headlines.add(xpp.nextText());
                            } else if (xpp.getName().equalsIgnoreCase("link")) {
                                if (insideItem)
                                    links.add(xpp.nextText());
                            }
                        } else if (eventType == XmlPullParser.END_TAG &&
                                xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = false;
                        }
                        eventType = xpp.next();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                final ListAdapter adapter = new ListAdapter(activity, headlines, links);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ListView list = findViewById(R.id.list);
                        list.setAdapter(adapter);
                    }
                });
                return null;
            }
        }.execute();
    }
}
