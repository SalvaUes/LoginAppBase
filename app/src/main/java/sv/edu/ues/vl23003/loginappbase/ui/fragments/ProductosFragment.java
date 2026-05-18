package sv.edu.ues.vl23003.loginappbase.ui.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import sv.edu.ues.vl23003.loginappbase.R;
import sv.edu.ues.vl23003.loginappbase.ui.utils.PrefManager;

public class ProductosFragment extends Fragment {

    private RecyclerView rvProductos;
    private ProductoAdapter adapter;
    private PrefManager prefManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_productos, container, false);

        prefManager = new PrefManager(requireContext());

        rvProductos = view.findViewById(R.id.rvProductos);
        TextInputEditText edtNew = view.findViewById(R.id.edtNewProducto);
        MaterialButton btnAdd = view.findViewById(R.id.btnAddProducto);

        rvProductos.setLayoutManager(new LinearLayoutManager(requireContext()));

        cargarProductos();

        btnAdd.setOnClickListener(v -> {
            String nombre = edtNew.getText() != null ? edtNew.getText().toString().trim() : "";
            if (TextUtils.isEmpty(nombre)) {
                Toast.makeText(requireContext(), "Ingrese el nombre del producto", Toast.LENGTH_SHORT).show();
                return;
            }
            prefManager.addProduct(nombre);
            cargarProductos();
            edtNew.setText("");
            Toast.makeText(requireContext(), "Producto agregado", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    private void cargarProductos() {
        List<String> listaProductos = prefManager.getProductList();

        adapter = new ProductoAdapter(listaProductos, new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String item) {
                Toast.makeText(getContext(), "Seleccionaste: " + item, Toast.LENGTH_SHORT).show();
            }
        }, new ProductoAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(String item) {
                prefManager.removeProduct(item);
                cargarProductos();
                Toast.makeText(getContext(), "Producto eliminado", Toast.LENGTH_SHORT).show();
            }
        });

        rvProductos.setAdapter(adapter);
    }
}