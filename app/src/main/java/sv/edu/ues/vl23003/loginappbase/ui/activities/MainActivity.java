package sv.edu.ues.vl23003.loginappbase.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import sv.edu.ues.vl23003.loginappbase.databinding.ActivityMainBinding;
import sv.edu.ues.vl23003.loginappbase.ui.utils.PrefManager;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prefManager = new PrefManager(this);

        //  usuario por defecto
        prefManager.saveUser("admin", "12345");

        binding.btnLogin.setOnClickListener(v -> {
            String user = binding.etUser.getText().toString();
            String pass = binding.etPass.getText().toString();

            if (prefManager.validateCredentials(user, pass)) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        });

        binding.tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
