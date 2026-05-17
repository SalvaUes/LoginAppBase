package sv.edu.ues.vl23003.loginappbase.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import sv.edu.ues.vl23003.loginappbase.R;
import sv.edu.ues.vl23003.loginappbase.ui.utils.PrefsManager;

public class InicioFragment extends Fragment {

    private PrefsManager prefsManager;
    private TextView tvBienvenida;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        prefsManager = new PrefsManager(requireContext());
        tvBienvenida = view.findViewById(R.id.tvBienvenida);

        String usuario = prefsManager.getUsuario();
        if (!usuario.isEmpty()) {
            tvBienvenida.setText("Bienvenido, " + usuario + "!");
        } else {
            tvBienvenida.setText("Bienvenido a la aplicación");
        }

        return view;
    }
}