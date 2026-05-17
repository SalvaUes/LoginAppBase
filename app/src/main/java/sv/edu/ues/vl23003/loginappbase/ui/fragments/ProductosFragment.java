package sv.edu.ues.vl23003.loginappbase.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import sv.edu.ues.vl23003.loginappbase.R;

public class ProductosFragment extends Fragment {

    private ListView lvProductos;
    private List<String> listaProductos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_productos, container, false);

        lvProductos = view.findViewById(R.id.lvProductos);

        cargarProductos();

        lvProductos.setOnItemClickListener((parent, view1, position, id) -> {
            String producto = listaProductos.get(position);
            Toast.makeText(getContext(), "Seleccionaste: " + producto, Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    private void cargarProductos() {
        listaProductos = new ArrayList<>();
        listaProductos.add("Producto 1");
        listaProductos.add("Producto 2");
        listaProductos.add("Producto 3");
        listaProductos.add("Producto 4");
        listaProductos.add("Producto 5");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                listaProductos
        );

        lvProductos.setAdapter(adapter);
    }
}