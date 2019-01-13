package ca.era.stride.application;

import android.content.Context;

import ca.era.stride.model.ERA;

public class EraApp {
    private static ERA era;
    private static Context appContext;
    private static Context activityContext;

    public static ERA getEra() {
        if (era == null) {
            // load model
            era = load();
        }
        return era;
    }

    private static ERA load() {
        //Code to load from persistence or something
        // INSERT CODE HERE

        // If still null after retrieving from persistence layer, then create new ERA object
        if(era == null){
            era = new ERA();
        }
        return era;
    }

    public static void setAppContext(Context context) {
        EraApp.appContext = context;
    }

    public static Context getAppContext() {
        return appContext;
    }

    public static Context getActivityContext() {
        return activityContext;
    }

    public static void setActivityContext(Context activityContext) {
        EraApp.activityContext = activityContext;
    }
}
