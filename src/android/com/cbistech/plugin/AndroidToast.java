package com.cbistech.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import android.widget.Toast;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import java.io.IOException;
import java.util.ArrayList;
import android.content.res.AssetFileDescriptor;


public class AndroidToast extends CordovaPlugin {
	int length=0;
	int compleeted=0;
	int i = 0;
	ArrayList<String> numList=new ArrayList<String>();
	  @Override
	  public boolean execute(
		String action, 
		JSONArray args, 
		CallbackContext callbackContext
	  ) throws JSONException {
		if("toast".equals(action)){
		  toast(args.getString(0));
		 return true; 
		}

		if("play".equals(action)){
		  play(args.getString(0));
		 return true; 
		}


		return false;
	  }

  
   private void toast(String title) {
	   Toast.makeText(webView.getContext(),title, Toast.LENGTH_LONG).show();
   }
	
	private void play(String title) {
		String word = title.trim();
		length = word.length();
		 getSpeekerContent(title);
		playMusic("balance.mp3");  
		Toast.makeText(cordova.getActivity().getApplicationContext(),title+"",1000).show();
	}


	
  
 ////////////////////////////////////////////////////////////////////////////////
	
	private void playMusic(String path) {
       android.content.res.AssetFileDescriptor afd = null;
        try {
            String file="/android_asset/www/js/resource/"+path;
  			String f = file.substring(15);
        	afd = cordova.getActivity().getAssets().openFd(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
		 
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) 	{
                mp.stop();
                if(i <  numList.size()) {
                    i++;
							Toast.makeText(cordova.getActivity().getApplicationContext(),"onCompl",1000).show();
                    playMusic(numList.get(i-1));
                } else i = 0;

            }
        });
        mediaPlayer.start();
    }

    private void getSpeekerContent(String trim) {
        numList.clear();
		
		  if (trim.charAt(1)==0){
            getSpeekerContent(trim.substring(1));
                    return;
        }
		
        int length = trim.length();
        if (length == 4) {
            thousend(trim);
        } else if (length == 3) {
            hundred(trim);
        } else if (length == 2) {
            tens(trim);
        } else if (length == 1) {
            once(trim);
        }

    }

	private void once(String trim) {
        char startChar = trim.charAt(0);
        String start = startChar+"";
        int p = Integer.parseInt(start);
        switch (p) {
            case 1:
                numList.add("One.mp3");
                break;
            case 2:
                numList.add("Two.mp3");
                break;
            case 3:
                numList.add("Three.mp3");
                break;
            case 4:
                numList.add("Four.mp3");
                break;
            case 5:
                numList.add("Five.mp3");
                break;
            case 6:
                numList.add("Six.mp3");
                break;
            case 7:
                numList.add("Seven.mp3");
                break;
            case 8:
                numList.add("Eight.mp3");
                break;
            case 9:
                numList.add("Nine.mp3");
                break;
        }
    }

    private void tens(String trim) {
        String superVal=trim;
        char startChar = trim.charAt(0);
        String start = startChar+"";
        int p = Integer.parseInt(start);
        switch (p) {
            case 1:
                goForTenToNigtin(superVal);
                return;
            case 2:
                numList.add("Twenty.mp3");
                break;
            case 3:
                numList.add("Thirty.mp3");
                break;
            case 4:
                numList.add("Forty.mp3");
                break;
            case 5:
                numList.add("Fifty.mp3");
                break;
            case 6:
                numList.add("Sixty.mp3");
                break;
            case 7:
                numList.add("Seventy.mp3");
                break;
            case 8:
                numList.add("Eighty.mp3");
                break;
            case 9:
                numList.add("Ninety.mp3");
                break;
        }
        once(superVal.substring(1));
    }

	private void goForTenToNigtin(String superVal) {
        int i = Integer.parseInt(superVal);
        switch (i){
            case 10:
                numList.add("Ten.mp3");
                break;
            case 11:
                numList.add("Eleven.mp3");
                break;
            case 12:
                numList.add("Twelve.mp3");
                break;
            case 13:
                numList.add("Thirteen.mp3");
                break;
            case 14:
                numList.add("Forteen.mp3");
                break;
            case 15:
                numList.add("Fivteen.mp3");
                break;
            case 16:
                numList.add("Sixteen.mp3");
                break;
            case 17:
                numList.add("Seventeen.mp3");
                break;
            case 18:
                numList.add("Eighteen.mp3");
                break;
            case 19:
                numList.add("Nineteen.mp3");
                break;
        }
    }

    private void hundred(String trim) {
        String superVal=trim;
        char startChar = trim.charAt(0);
        String start = startChar+"";
        int p = Integer.parseInt(start);
        switch (p) {
            case 1:
                numList.add("One Hundred.mp3");
                break;
            case 2:
                numList.add("Two Hundred.mp3");
                break;
            case 3:
                numList.add("Three Hundred.mp3");
                break;
            case 4:
                numList.add("Four Hundred.mp3");
                break;
            case 5:
                numList.add("Five Hundred.mp3");
                break;
            case 6:
                numList.add("Six Hundred.mp3");
                break;
            case 7:
                numList.add("Seven Hundred.mp3");
                break;
            case 8:
                numList.add("Eight Hundred.mp3");
                break;
            case 9:
                numList.add("Nine Hundred.mp3");
                break;
        }
        tens(superVal.substring(1));
    }

    private void thousend(String trim) {
		Toast.makeText(cordova.getActivity().getApplicationContext(),"1000",1000).show();
        String superVal=trim;
        char startChar = trim.charAt(0);
        String start = startChar+"";
        int p = Integer.parseInt(start);
        switch (p) {
            case 1:
                numList.add("One Thousand.mp3");
				Toast.makeText(cordova.getActivity().getApplicationContext(),"1000",1000).show();

                break;
            case 2:
                numList.add("Two Thousand.mp3");
                break;
            case 3:
                numList.add("Three Thousand.mp3");
                break;
            case 4:
                numList.add("Four Thousand.mp3");
                break;
            case 5:
                numList.add("Five Thousand.mp3");
                break;
            case 6:
                numList.add("Six Thousand.mp3");
                break;
            case 7:
                numList.add("Seven Thousand.mp3");
                break;
            case 8:
                numList.add("Eight Thousand.mp3");
                break;
            case 9:
                numList.add("Nine Thousand.mp3");
                break;
        }
        hundred(superVal.substring(1));
				Toast.makeText(cordova.getActivity().getApplicationContext(),"1000 ",1000).show();


    }
	
 ////////////////////////////////////////////////////////////////////////////////
}  

