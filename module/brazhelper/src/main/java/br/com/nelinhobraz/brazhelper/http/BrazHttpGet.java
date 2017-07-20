package br.com.nelinhobraz.brazhelper.http;

/**
 * Created by emanuelbraz on 3/25/17.
 */

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by emanuelbraz on 3/10/17.
 */

public class BrazHttpGet extends AsyncTask<String, Void, String> {

    private String TAG = BrazHttpGet.class.getSimpleName();
    public static String ERROR_RESPONSE_CODE = "error_response_code";

    private OnBrazHttpTaskListener onTaskDoneListener;
    private String urlStr = "";

    public BrazHttpGet() {

    }

    public BrazHttpGet(String url, OnBrazHttpTaskListener onTaskDoneListener) {
        this.onTaskDoneListener = onTaskDoneListener;
        this.urlStr = url;
    }

    public BrazHttpGet prepare(String url, OnBrazHttpTaskListener onTaskDoneListener) {
        this.onTaskDoneListener = onTaskDoneListener;
        this.urlStr = url;
        return this;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        this.onTaskDoneListener.showProgressDialog();
    }

    @Override
    public String doInBackground(String... params) {

        try {

            Log.d(TAG, urlStr);
            URL mUrl = new URL(urlStr);
            HttpURLConnection httpConnection = (HttpURLConnection) mUrl.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Content-length", "0");
            httpConnection.setUseCaches(false);
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setConnectTimeout(100000);
            httpConnection.setReadTimeout(100000);

            httpConnection.connect();

            int responseCode = httpConnection.getResponseCode();

            Log.d("Status Code: ", String.valueOf(responseCode));

            this.onTaskDoneListener.hideProgressDialog();

            if (responseCode != HttpURLConnection.HTTP_OK) {

                onTaskDoneListener.onServerOrConnectionError(httpConnection.getResponseCode(), httpConnection.getResponseMessage());
                return BrazHttpGet.ERROR_RESPONSE_CODE;

            } else {

                BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                return sb.toString();
            }
        } catch (IOException e) {
            this.onTaskDoneListener.hideProgressDialog();
            e.printStackTrace();
        } catch (Exception ex) {
            this.onTaskDoneListener.hideProgressDialog();
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if (s != null) {
            if (s != BrazHttpGet.ERROR_RESPONSE_CODE) {
                if (onTaskDoneListener != null) {
                    onTaskDoneListener.onTaskDone(s);
                }
            }
        } else {
            onTaskDoneListener.onError();
        }
    }

}

/*

        // Convert JSON to Java Object
        Staff staff = gson.fromJson(reader, Staff.class);
        System.out.println(staff);

        // Convert JSON to JsonElement, and later to String

        JsonElement json = gson.fromJson(reader, JsonElement.class);
        String jsonInString = gson.toJson(json);
        System.out.println(jsonInString);

        6.1 Convert a JSON Array to a List, using TypeToken

        String json = "[{\"name\":\"mkyong\"}, {\"name\":\"laplap\"}]";
        List<Staff> list = gson.fromJson(json, new TypeToken<List<Staff>>(){}.getType());
        list.forEach(x -> System.out.println(x));
        6.2 Convert a JSON to a Map

        String json = "{\"name\":\"mkyong\", \"age\":33}";
        Map<String, Object> map = gson.fromJson(json, new TypeToken<Map<String, Object>>(){}.getType());
        map.forEach((x,y)-> System.out.println("key : " + x + " , value : " + y));

        JSONObject mainObject = new JSONObject(Your_Sring_data);
        JSONObject uniObject = mainObject.getJSONObject("university");
        String  uniName = uniObject.getJsonString("name");
        String uniURL = uniObject.getJsonString("url");

        JSONObject oneObject = mainObject.getJSONObject("1");
        String id = oneObject.getJsonString("id");

        JSONArray jArray = jObject.getJSONArray("data");
        for ( int i = 0; i < jsonarray.length() ; i++)
            {
               //this object inside array you can do whatever you want
               JSONObject object = jsonarray.getJSONObject(i);
               e/ou
                String oneObjectsItem = object.getString("STRINGNAMEinTHEarray");
            }


//        PromoCodeDataModel pcdm = new PromoCodeDataModel();
//        pcdm.setCode("123");
//        pcdm.setDescription("Desconto de tantas vezes");
//        pcdm.setName("Francisco Madureira");
//
//        Gson gson = new Gson();
//        String promocodedatamodel = gson.toJson(pcdm);

//        Log.d(TAG, promocodedatamodel);
        //{"code":"123","description":"Desconto de tantas vezes","name":"Francisco Madureira","id":0,"rate":0}
         */


//    JSONObject mainObject = null;
//            try {
//
//                    mainObject = new JSONObject(responseData);
//                    JSONArray jArray = mainObject.getJSONArray("data");
//
//                    Gson gson = new Gson();
//                    List<PromoCodeDataModel> list = gson.fromJson(jArray.toString(), new TypeToken<List<PromoCodeDataModel>>(){}.getType());
//
//
//        for (int i = 0; i < list.size(); i++){
//        Log.d(TAG, list.get(i).getCode());
//        }
//                for ( int i = 0; i < jArray.length() ; i++)
//                {
//                    //this object inside array you can do whatever you want
//                    JSONObject object = jArray.getJSONObject(i);
//                    String strItem = object.getString("code");
//                    Log.d(TAG, strItem);
//
//                }

//    JsonObject complaint = new JsonObject();
//complaint.addProperty("key", "value");