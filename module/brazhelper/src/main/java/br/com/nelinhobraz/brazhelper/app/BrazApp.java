package br.com.nelinhobraz.brazhelper.app;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by emanuelbraz on 3/25/17.
 */

public class BrazApp {

    private static final String tag = "BrazApp";

    public static void openExternallAppByActionChooser(Context context, String actionName){
        try {
            Intent i = new Intent(actionName);
            context.startActivity(i.createChooser(i, "Selecione app:"));
        }catch (Exception exception){
            Log.d(tag, "Erro ao tentar abrir app by ActionName.");
        }
    }

    public static void openExternallAppByPackageName(Context context, String packageNameOrAction){
        try {
            //"com.cygery.repetitouch.free"
            Intent i = context.getPackageManager().getLaunchIntentForPackage(packageNameOrAction);
            context.startActivity(i);
        }catch (Exception exception){
            Log.d(tag, "Erro ao tentar abrir app externo.");
        }
    }
}
