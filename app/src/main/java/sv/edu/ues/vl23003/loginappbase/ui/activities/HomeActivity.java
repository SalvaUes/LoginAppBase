package sv.edu.ues.vl23003.loginappbase.ui.activities; // paquete de actividades principal de la pantalla interna

import android.os.Bundle; // importacion para usar el ciclo de vida de la actividad

import androidx.appcompat.app.AppCompatActivity; // importacion de la clase base de actividad compatible
import androidx.fragment.app.Fragment; // importacion para trabajar con fragmentos dentro de la pantalla

import sv.edu.ues.vl23003.loginappbase.R; // recurso que contiene ids y configuraciones del proyecto
import sv.edu.ues.vl23003.loginappbase.databinding.ActivityHomeBinding; // enlace de vista de la pantalla principal interna
import sv.edu.ues.vl23003.loginappbase.ui.fragments.InicioFragment; // fragmento que representa la vista de inicio
import sv.edu.ues.vl23003.loginappbase.ui.fragments.PerfilFragment; // fragmento que representa la vista de perfil
import sv.edu.ues.vl23003.loginappbase.ui.fragments.ProductosFragment; // fragmento que representa la vista de productos

public class HomeActivity extends AppCompatActivity { // clase que maneja la pantalla principal despues del acceso

    private ActivityHomeBinding binding; // enlace con los elementos visuales de esta actividad

    @Override
    protected void onCreate(Bundle savedInstanceState) { // metodo que se ejecuta al crear la actividad
        super.onCreate(savedInstanceState); // llamada obligatoria a la clase padre
        binding = ActivityHomeBinding.inflate(getLayoutInflater()); // se infla el layout usando view binding
        setContentView(binding.getRoot()); // se asigna la vista raiz como contenido

        if (savedInstanceState == null) { // si la actividad se crea por primera vez se muestra el inicio
            loadFragment(new InicioFragment()); // se carga el fragmento de inicio como vista inicial
        }

        binding.bottomNavigation.setOnItemSelectedListener(item -> { // se configura la navegacion inferior para cambiar de fragmento
            Fragment selectedFragment = null; // variable que guardara el fragmento elegido
            int id = item.getItemId(); // se obtiene el identificador del elemento pulsado

            if (id == R.id.nav_home) { // si se elige inicio se muestra ese fragmento
                selectedFragment = new InicioFragment(); // se asigna el fragmento de inicio
            } else if (id == R.id.nav_products) { // si se elige productos se muestra ese fragmento
                selectedFragment = new ProductosFragment(); // se asigna el fragmento de productos
            } else if (id == R.id.nav_profile) { // si se elige perfil se muestra ese fragmento
                selectedFragment = new PerfilFragment(); // se asigna el fragmento de perfil
            }

            if (selectedFragment != null) { // si existe un fragmento valido se reemplaza el actual
                loadFragment(selectedFragment); // se carga el fragmento seleccionado en el contenedor
                return true; // se indica que el evento fue atendido
            }
            return false; // se indica que el evento no pudo resolverse
        });
    }

    private void loadFragment(Fragment fragment) { // metodo auxiliar para cambiar el fragmento visible
        getSupportFragmentManager() // se inicia una transaccion del administrador de fragmentos
                .beginTransaction() // se prepara el cambio de fragmento
                .replace(R.id.fragment_container, fragment) // se reemplaza el contenido del contenedor por el fragmento recibido
                .commit(); // se confirma el cambio en pantalla
    }
}