package com.example.evaluacion;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsManager {

    private static final String PREF_NAME = "loginData";

    private static final String KEY_USUARIO = "usuario";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public PrefsManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    // guardar usuario
    public void guardarUsuario(String usuario, String password, String email) {

        editor.putString(KEY_USUARIO, usuario);
        editor.putString(KEY_PASSWORD, password);
        editor.putString(KEY_EMAIL, email);

        editor.apply();
    }

    // obtener usuario
    public String getUsuario() {
        return preferences.getString(KEY_USUARIO, "");
    }

    // obtener password
    public String getPassword() {
        return preferences.getString(KEY_PASSWORD, "");
    }

    // obtener email
    public String getEmail() {
        return preferences.getString(KEY_EMAIL, "");
    }

    // verificar sesion
    public boolean haySesion() {
        return !getUsuario().isEmpty();
    }

    // cerrar sesion
    public void logout() {
        editor.clear();
        editor.apply();
    }
}