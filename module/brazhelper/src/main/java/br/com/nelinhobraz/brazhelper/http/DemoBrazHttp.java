package br.com.nelinhobraz.brazhelper.http;

import android.app.Activity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by emanuelbraz on 3/25/17.
 */

public class DemoBrazHttp extends Activity{

    private BrazHttpController serviceController;
    public DemoBrazHttp(){

        serviceController = new BrazHttpController();

        get();
        post();
    }

    private void get(){



        serviceController.get("http://www...", new BrazHttpCallbackAsync(this) {

            public void onTaskDone(String responseData){
                super.onTaskDone(responseData);

                Log.d(TAG, responseData.toString());

                JSONObject mainObject = null;
                try {

                    mainObject = new JSONObject(responseData);
                    JSONArray jArray = mainObject.getJSONArray("data");

//                    Gson gson = new Gson();
//                    promoCodeArrayList = gson.fromJson(jArray.toString(), new TypeToken<ArrayList<PromoCodeDataModel>>(){}.getType());
//
//                    CustomAdapter adapter = new CustomAdapter(getActivity(), promoCodeArrayList);
//                    lvItems.setAdapter(adapter);

                } catch (JSONException e) {
                    Log.d(TAG, "Erro na recepcao do json");
                    e.printStackTrace();
                }
            }

            public void onError(){
                super.onError();
            }

            @Override
            public void onServerOrConnectionError(int statusCode, String responseMessage) { super.onServerOrConnectionError(statusCode, responseMessage); }

        }).execute();
    }


    private void post(){

        HashMap<String, String> example = new HashMap<String, String>();
        example.put("data", "v1");

        serviceController.post().prepare("http://www...", example, new BrazHttpCallbackAsync() {

            public void onTaskDone(String responseData){
                super.onTaskDone(responseData);

                Log.d(TAG, responseData.toString());
            }

            public void onError(){
                super.onError();
            }

            @Override
            public void onServerOrConnectionError(int statusCode, String responseMessage) { super.onServerOrConnectionError(statusCode, responseMessage); }
        }).execute();
    }
}


