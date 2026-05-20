package sv.edu.ues.vl23003.loginappbase.ui.fragments; // paquete donde vive el fragmento de productos

import android.os.Bundle; // importacion para acceder al estado del fragmento
import android.text.TextUtils; // importacion para validar cadenas de texto
import android.view.LayoutInflater; // importacion para inflar la vista desde un xml
import android.view.View; // importacion para representar la vista creada
import android.view.ViewGroup; // importacion para recibir el contenedor padre
import android.widget.Toast; // importacion para mostrar mensajes breves al usuario

import androidx.fragment.app.Fragment; // importacion de la clase base de fragmento
import androidx.recyclerview.widget.LinearLayoutManager; // importacion para organizar items en lista vertical
import androidx.recyclerview.widget.RecyclerView; // importacion para mostrar listas de elementos

import com.google.android.material.button.MaterialButton; // importacion para botones con estilo material
import com.google.android.material.textfield.TextInputEditText; // importacion para campos de texto con estilo material

import java.util.List; // importacion para trabajar con listas

import sv.edu.ues.vl23003.loginappbase.R; // importacion de recursos del proyecto
import sv.edu.ues.vl23003.loginappbase.ui.utils.PrefManager; // importacion del gestor de preferencias locales

public class ProductosFragment extends Fragment { // clase que muestra la lista de productos

    private RecyclerView rvProductos; // vista reciclable para mostrar lista de productos
    private ProductoAdapter adapter; // adaptador que controla los items de la lista
    private PrefManager prefManager; // gestor para guardar y leer productos

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { // metodo que construye la vista del fragmento
        View view = inflater.inflate(R.layout.fragment_productos, container, false); // se infla el layout de productos

        prefManager = new PrefManager(requireContext()); // se crea el gestor de preferencias

        rvProductos = view.findViewById(R.id.rvProductos); // se obtiene la vista reciclable
        TextInputEditText edtNew = view.findViewById(R.id.edtNewProducto); // se obtiene el campo para ingresar nuevo producto
        MaterialButton btnAdd = view.findViewById(R.id.btnAddProducto); // se obtiene el boton para agregar producto

        rvProductos.setLayoutManager(new LinearLayoutManager(requireContext())); // se configura la lista como vertical

        cargarProductos(); // se cargan los productos guardados

        btnAdd.setOnClickListener(v -> { // al presionar el boton de agregar
            String nombre = edtNew.getText() != null ? edtNew.getText().toString().trim() : ""; // se obtiene el nombre del producto
            if (TextUtils.isEmpty(nombre)) { // se valida que el nombre no este vacio
                Toast.makeText(requireContext(), "Ingrese el nombre del producto", Toast.LENGTH_SHORT).show(); // se informa que debe ingresar nombre
                return; // se detiene la ejecucion
            }
            prefManager.addProduct(nombre); // se guarda el producto en preferencias
            cargarProductos(); // se recarga la lista para ver el nuevo producto
            edtNew.setText(""); // se limpia el campo de entrada
            Toast.makeText(requireContext(), "Producto agregado", Toast.LENGTH_SHORT).show(); // se informa que fue agregado
        });

        return view; // se devuelve la vista ya configurada
    }

    private void cargarProductos() { // metodo para cargar y mostrar los productos
        List<String> listaProductos = prefManager.getProductList(); // se obtiene la lista de productos guardados

        adapter = new ProductoAdapter(listaProductos, new ProductoAdapter.OnItemClickListener() { // se crea el adaptador con callback de click
            @Override
            public void onItemClick(String item) { // cuando se hace click en un producto
                Toast.makeText(getContext(), "Seleccionaste: " + item, Toast.LENGTH_SHORT).show(); // se informa cual producto fue seleccionado
            }
        }, new ProductoAdapter.OnDeleteClickListener() { // callback para cuando se elimina un producto
            @Override
            public void onDeleteClick(String item) { // cuando se presiona eliminar en un producto
                prefManager.removeProduct(item); // se elimina el producto de preferencias
                cargarProductos(); // se recarga la lista sin el producto eliminado
                Toast.makeText(getContext(), "Producto eliminado", Toast.LENGTH_SHORT).show(); // se informa que fue eliminado
            }
        });

        rvProductos.setAdapter(adapter); // se asigna el adaptador a la lista
    }
}