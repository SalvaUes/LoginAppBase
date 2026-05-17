package sv.edu.ues.vl23003.loginappbase.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import sv.edu.ues.vl23003.loginappbase.R;
import sv.edu.ues.vl23003.loginappbase.ui.utils.PrefManager;

public class InicioFragment extends Fragment {

    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        PrefManager prefManager = new PrefManager(requireContext());
        TextView tvBienvenida = view.findViewById(R.id.tvBienvenida);

        String usuario = prefManager.getUsuario();
        if (!usuario.isEmpty()) {
            tvBienvenida.setText(getString(R.string.bienvenida_usuario, usuario));
        } else {
            tvBienvenida.setText(getString(R.string.bienvenida_default));
        }

        return view;
    }
}