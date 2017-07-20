package br.com.nelinhobraz.brazhelper.locale;

import android.content.Context;
import android.content.Intent;

/**
 * Created by emanuelbraz on 13/06/17.
 */

public class BrazLocale {

    /* Open settings/locale device */
    public void openChangeLocaleSettings(Context localContext){
        Intent i = new Intent(android.provider.Settings.ACTION_LOCALE_SETTINGS);
        localContext.startActivity(i);
    }

    /* Enables download language packages (speech recognizer can work offline) from Language settings screen */
    public void openChangeLocaleAndInputSettings(Context localContext){
        Intent intent=new Intent(android.provider.Settings.ACTION_INPUT_METHOD_SETTINGS);
        localContext.startActivity(intent);
    }

    /* Change app locale */
    public void changeLocaleToCurrentApp(Context localContext){
        //Mudar localizacao do app atual
//        Locale locale = new Locale(Locale.ENGLISH.getLanguage());
//        Locale.setDefault(locale);
//        Configuration config = getBaseContext().getResources().getConfiguration();
//        config.locale = locale;
//        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }
}
