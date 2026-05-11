package sv.edu.ues.vl23003.loginappbase.ui.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import sv.edu.ues.vl23003.loginappbase.R;

// Esta clase maneja el login. Validar usuario/pass contra SharedPrefs usando PrefsManager.
// Si coincide, abrir HomeActivity con Intent. Si no, mostrar toast de error exacto.
// Menu: Registrar -> RegisterActivity, Salir -> cerrar app con finishAndRemoveTask.
// UI: usar ViewBinding, estilos personalizados y toolbar con titulo fijo "Login".


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}