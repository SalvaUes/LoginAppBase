package sv.edu.ues.vl23003.loginappbase.ui.activities;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.vl23003.loginappbase.R;
import sv.edu.ues.vl23003.loginappbase.ui.utils.PrefManager;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUsuario, edtEmail, edtPassword, edtConfirmar;
    Button btnGuardar, btnRegresar;
    PrefManager prefManager;  // ← Cambiado: PrefManager (sin 's')

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

        prefManager = new PrefManager(this);  // ← Cambiado: PrefManager (sin 's')

        btnGuardar.setOnClickListener(v -> guardar());
        btnRegresar.setOnClickListener(v -> finish());
    }

    private void guardar() {
        String user = edtUsuario.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String pass = edtPassword.getText().toString().trim();
        String confirm = edtConfirmar.getText().toString().trim();

        if (user.length() < 3) {
            toast("Usuario mínimo 3 caracteres");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            toast("Email inválido");
            return;
        }

        if (pass.length() < 5 || !pass.matches("[a-zA-Z0-9]+")) {
            toast("Password mínimo 5 y alfanumérico");
            return;
        }

        if (!pass.equals(confirm)) {
            toast("Passwords no coinciden");
            return;
        }

        // Nota: PrefManager original no tiene guardarUsuario con email
        // Por ahora guardamos solo usuario y password
        prefManager.saveUser(user, pass);
        toast("Usuario registrado exitosamente");
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