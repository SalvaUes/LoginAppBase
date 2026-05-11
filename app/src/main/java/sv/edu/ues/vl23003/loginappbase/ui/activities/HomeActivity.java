package sv.edu.ues.vl23003.loginappbase.ui.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import sv.edu.ues.vl23003.loginappbase.R;


// Panel principal post-login. Configurar BottomNavigationView para intercambiar
// los 3 fragments: Inicio, Productos, Perfil dentro del FragmentContainerView.
// Usar getSupportFragmentManager y replace para cambiar fragments sin recrear activity.
// No olvidar vincular el menu home_menu.xml con los IDs de cada fragment.

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}