package com.example.evaluacion;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUsuario, edtEmail, edtPassword, edtConfirmar;
    Button btnGuardar, btnRegresar;

    PrefsManager prefsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUsuario = findViewById(R.id.edtUsuario);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmar = findViewById(R.id.edtConfirmar);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnRegresar = findViewById(R.id.btnRegresar);

        prefsManager = new PrefsManager(this);

        btnGuardar.setOnClickListener(v -> guardar());
        btnRegresar.setOnClickListener(v -> finish());
    }

    private void guardar() {
        String user = edtUsuario.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String pass = edtPassword.getText().toString().trim();
        String confirm = edtConfirmar.getText().toString().trim();

        // validaciones
        if (user.length() < 3) {
            toast("Usuario minimo 3 caracteres");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            toast("Email invalido");
            return;
        }

        if (pass.length() < 5 || !pass.matches("[a-zA-Z0-9]+")) {
            toast("Password minimo 5 y alfanumerico");
            return;
        }

        if (!pass.equals(confirm)) {
            toast("Passwords no coinciden");
            return;
        }

        // guardar
        prefsManager.guardarUsuario(user, pass, email);

        toast("Usuario registrado");

        limpiar();
    }

    private void limpiar() {
        edtUsuario.setText("");
        edtEmail.setText("");
        edtPassword.setText("");
        edtConfirmar.setText("");
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}