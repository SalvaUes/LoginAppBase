package sv.edu.ues.vl23003.loginappbase.ui.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    private static final String PREF_NAME = "LoginPrefs";
    private static final String KEY_USER = "user";
    private static final String KEY_PASS = "pass";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;

    public PrefManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    // El ROL 2 implementará el guardado real, pero necesitamos esto para que el ROL 3 funcione
    public void saveUser(String user, String pass) {
        editor.putString(KEY_USER, user);
        editor.putString(KEY_PASS, pass);
        editor.apply();
    }

    public boolean validateCredentials(String user, String pass) {
        String savedUser = pref.getString(KEY_USER, "");
        String savedPass = pref.getString(KEY_PASS, "");
        return user.equals(savedUser) && pass.equals(savedPass) && !user.isEmpty();
    }
}
