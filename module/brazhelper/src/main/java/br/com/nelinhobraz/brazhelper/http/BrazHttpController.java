package br.com.nelinhobraz.brazhelper.http;

import java.util.HashMap;

/**
 * Created by emanuelbraz on 3/12/17.
 */

public class BrazHttpController {

    public BrazHttpController(){

    }

    public BrazHttpGet get(){
        return new BrazHttpGet();
    }

    public BrazHttpGet get(String url, OnBrazHttpTaskListener onTaskDoneListener){
        return new BrazHttpGet(url, onTaskDoneListener);
    }

    public BrazHttpPost post(){
        return new BrazHttpPost();
    }

    public BrazHttpPost post(String url, HashMap<String,String> hashMap, OnBrazHttpTaskListener onTaskDoneListener){
        return new BrazHttpPost().prepare(url, hashMap, onTaskDoneListener);
    }
}

