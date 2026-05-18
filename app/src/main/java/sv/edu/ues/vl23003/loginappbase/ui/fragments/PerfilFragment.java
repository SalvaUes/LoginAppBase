package sv.edu.ues.vl23003.loginappbase.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import sv.edu.ues.vl23003.loginappbase.R;
import sv.edu.ues.vl23003.loginappbase.ui.utils.PrefManager;

public class PerfilFragment extends Fragment {
    
    private PrefManager prefManager;
    private TextView tvUsuario, tvEmail;
    private Button btnLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        prefManager = new PrefManager(requireContext());

        tvUsuario = view.findViewById(R.id.tvPerfilUsuario);
        tvEmail = view.findViewById(R.id.tvPerfilEmail);
        btnLogout = view.findViewById(R.id.btnLogout);

        cargarDatosUsuario();

        btnLogout.setOnClickListener(v -> mostrarDialogoLogout());

        return view;
    }

    private void cargarDatosUsuario() {
        String usuario = prefManager.getUsuario();
        String email = prefManager.getEmail();

        tvUsuario.setText("Usuario: " + (usuario.isEmpty() ? "No disponible" : usuario));
        tvEmail.setText("Email: " + (email.isEmpty() ? "No disponible" : email));
    }

    private void mostrarDialogoLogout() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Cerrar sesión")
                .setMessage("¿Estás seguro que deseas cerrar sesión?")
                .setPositiveButton("Sí", (dialog, which) -> cerrarSesion())
                .setNegativeButton("No", null)
                .show();
    }

    private void cerrarSesion() {
        prefManager.logout();
        Toast.makeText(requireContext(), "Sesión cerrada", Toast.LENGTH_SHORT).show();

        if (getActivity() != null) {
            getActivity().finish();
        }
    }
}