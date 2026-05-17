package sv.edu.ues.vl23003.loginappbase.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import sv.edu.ues.vl23003.loginappbase.R;
import sv.edu.ues.vl23003.loginappbase.databinding.ActivityHomeBinding;
import sv.edu.ues.vl23003.loginappbase.ui.fragments.InicioFragment;
import sv.edu.ues.vl23003.loginappbase.ui.fragments.PerfilFragment;
import sv.edu.ues.vl23003.loginappbase.ui.fragments.ProductosFragment;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            loadFragment(new InicioFragment());
        }

        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                selectedFragment = new InicioFragment();
            } else if (id == R.id.nav_products) {
                selectedFragment = new ProductosFragment();
            } else if (id == R.id.nav_profile) {
                selectedFragment = new PerfilFragment();
            }

            if (selectedFragment != null) {
                loadFragment(selectedFragment);
                return true;
            }
            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}