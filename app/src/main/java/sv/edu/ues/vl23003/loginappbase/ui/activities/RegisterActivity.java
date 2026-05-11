package sv.edu.ues.vl23003.loginappbase.ui.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import sv.edu.ues.vl23003.loginappbase.R;

// Registro de nuevo usuario. Validar: usuario min 3 chars, pass min 5 alfanumerico,
// confirmacion igual, email con formato valido. Guardar en SharedPrefs via PrefsManager.
// Boton Guardar: mostrar toast, limpiar campos. Boton Regresar: finish() para volver.
// Usar ViewBinding y mostrar feedback visual si alguna validacion falla.


public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}