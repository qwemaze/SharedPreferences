package in.edelworks.sharedpreferences;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Sharedpreferences extends CordovaPlugin {
	public static final String PUT_STRING = "putString";
	public static final String GET_STRING = "getString";
	public static final String PUT_BOOLEAN = "putBoolean";
	public static final String GET_BOOLEAN = "getBoolean";
	public static final String PUT_INT = "putInt";
	public static final String GET_INT = "getInt";
	public static final String PUT_FLOAT = "putFloat";
	public static final String GET_FLOAT = "getFloat";
	public static final String PUT_LONG = "putLong";
	public static final String GET_LONG = "getLong";
	public static final String REMOVE = "remove";
	public static final String CLEAR = "clear";
	public static final String SHARED_PREFERENCES = "SharedPreferences";
	public static String PREF_FILE = "";
	public static final String[] MODE_ARRAY = {"MODE_APPEND", "MODE_PRIVATE"};
	SharedPreferences SharedPref = null;
	SharedPreferences.Editor editor;

	@Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		//create shared Preferences
		//two param filename and mode
		//returns true if created with success message and false if not with exception message
		Context context = cordova.getActivity();
		if (SharedPref == null) {
			SharedPref = PreferenceManager.getDefaultSharedPreferences(context);
		}
		if(PUT_STRING.equals(action)){
			editor = SharedPref.edit();
			try{
				editor.putString(args.getString(0), args.getString(1));
				editor.commit();
			}catch (Exception e){
				callbackContext.error("Error editing Key " + args.getString(0) + " with value " + args.getString(1) + e.getMessage());
				return false;
			}
			callbackContext.success("Added Value " + args.getString(1) + " to Preferences key " + args.getString(0));
			return true;
		}else if(GET_STRING.equals(action)){
			String KeyVal;
			try{
				if(SharedPref.contains(args.getString(0))){
					KeyVal = SharedPref.getString(args.getString(0), "");
					callbackContext.success(KeyVal);
					return true;
				}else{
					callbackContext.error("No data");
					return false;
				}
			}catch (Exception e){
				callbackContext.error("Could Not Retreive " + args.getString(0) + e.getMessage());
				return false;
			}
		}else if(PUT_BOOLEAN.equals(action)){
			editor = SharedPref.edit();
			try{
				editor.putBoolean(args.getString(0), args.getBoolean(1));
				editor.commit();
			}catch (Exception e){
				callbackContext.error("Error editing Key " + args.getString(0) + " with value " + args.getBoolean(1) + e.getMessage());
				return false;
			}
			callbackContext.success("Added Value " + args.getBoolean(1) + " to Preferences key " + args.getString(0));
			return true;
		}else if(GET_BOOLEAN.equals(action)){
			Boolean KeyVal;
			try{
				if(SharedPref.contains(args.getString(0))){
					KeyVal = SharedPref.getBoolean(args.getString(0), false);
					if(KeyVal.equals(true)){
						callbackContext.success(1);
					}else{
						callbackContext.success(0);
					}
					return true;
				}else{
					callbackContext.error("No data");
					return false;
				}
			}catch (Exception e){
				callbackContext.error("Could Not Retreive " + args.getString(0) + e.getMessage());
				return false;
			}
		}else if(PUT_INT.equals(action)){
			editor = SharedPref.edit();
			try{
				editor.putInt(args.getString(0), args.getInt(1));
				editor.commit();
			}catch (Exception e){
				callbackContext.error("Error editing Key " + args.getString(0) + " with value " + args.getInt(1) + e.getMessage());
				return false;
			}
			callbackContext.success("Added Value " + args.getInt(1) + " to Preferences key " + args.getString(0));
			return true;
		}else if(GET_INT.equals(action)){
			Integer KeyVal;
			try{
				if(SharedPref.contains(args.getString(0))){
					KeyVal = SharedPref.getInt(args.getString(0), 0);
					callbackContext.success(KeyVal);
					return true;
				}else{
					callbackContext.error("No data");
					return false;
				}
			}catch (Exception e){
				callbackContext.error("Could Not Retreive " + args.getString(0) + e.getMessage());
				return false;
			}
		}else if(PUT_LONG.equals(action)){
			editor = SharedPref.edit();
			try{
				editor.putLong(args.getString(0), args.getLong(1));
				editor.commit();
			}catch (Exception e){
				callbackContext.error("Error editing Key " + args.getString(0) + " with value " + args.getLong(1) + e.getMessage());
				return false;
			}
			callbackContext.success("Added Value " + args.getLong(1) + " to Preferences key " + args.getString(0));
			return true;
		}else if(GET_LONG.equals(action)){
			Long KeyVal;
			try{
				if(SharedPref.contains(args.getString(0))){
					KeyVal = SharedPref.getLong(args.getString(0), 0);
					callbackContext.success(KeyVal.toString());
					return true;
				}else{
					callbackContext.error("No data");
					return false;
				}
			}catch (Exception e){
				callbackContext.error("Could Not Retreive " + args.getString(0) + e.getMessage());
				return false;
			}
		}else if(REMOVE.equals(action)){
			editor = SharedPref.edit();
			try{
				editor.remove(args.getString(0));
				editor.commit();
			}catch (Exception e){
				callbackContext.error("Error editing Key " + args.getString(0) + " with value " + args.getLong(1) + e.getMessage());
				return false;
			}
			callbackContext.success("Removed Value from Key " + args.getString(0));
			return true;
		}else if(CLEAR.equals(action)){
			editor = SharedPref.edit();
			try{
				editor.clear();
				editor.commit();
			}catch (Exception e){
				callbackContext.error("Could Not Clear Shared preference File " + e.getMessage());
				return false;
			}
			callbackContext.success("Cleared preference File ");

			return true;
		}else{
			callbackContext.error("Invalid Action");
			return false;
		}
    }

	public static boolean in_array(String[] haystack, String needle) {
	    for(int i=0;i<haystack.length;i++) {
	        if(haystack[i].equals(needle)) {
	            return true;
	        }
	    }
	    return false;
	}
}
