package sv.edu.ues.vl23003.loginappbase.ui.fragments; // paquete donde vive el adaptador de productos

import android.view.LayoutInflater; // importacion para inflar vistas desde xml
import android.view.View; // importacion para representar la vista creada
import android.view.ViewGroup; // importacion para recibir el contenedor padre
import android.widget.TextView; // importacion para mostrar texto en pantalla
import com.google.android.material.button.MaterialButton; // importacion para botones con estilo material
import sv.edu.ues.vl23003.loginappbase.R; // importacion de recursos del proyecto

import androidx.annotation.NonNull; // importacion para marcar parametros no nulos
import androidx.recyclerview.widget.RecyclerView; // importacion de la clase base del adaptador

import java.util.List; // importacion para trabajar con listas

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> { // clase adaptadora para mostrar productos en lista

    private final List<String> listaProductos; // lista que contiene los nombres de productos
    private final OnItemClickListener onItemClick; // callback para cuando se hace click en un producto
    private final OnDeleteClickListener onDeleteClick; // callback para cuando se elimina un producto

    public interface OnItemClickListener { // interfaz para definir el comportamiento al hacer click
        void onItemClick(String item); // metodo que se ejecuta cuando se hace click
    }

    public interface OnDeleteClickListener { // interfaz para definir el comportamiento al eliminar
        void onDeleteClick(String item); // metodo que se ejecuta cuando se elimina
    }

    public ProductoAdapter(List<String> listaProductos, OnItemClickListener onItemClick, OnDeleteClickListener onDeleteClick) { // constructor que recibe la lista y callbacks
        this.listaProductos = listaProductos; // se asigna la lista de productos
        this.onItemClick = onItemClick; // se asigna el callback de click
        this.onDeleteClick = onDeleteClick; // se asigna el callback de eliminacion
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { // metodo para crear el view holder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false); // se infla el layout de un item producto
        return new ProductoViewHolder(view); // se devuelve el view holder con la vista inflada
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) { // metodo para asignar datos al view holder
        String producto = listaProductos.get(position); // se obtiene el producto en la posicion actual
        holder.tvNombre.setText(producto); // se muestra el nombre del producto

        final OnItemClickListener listener = onItemClick; // se guarda el callback de click
        holder.itemView.setOnClickListener(v -> { // se configura el evento click
            if (listener != null) listener.onItemClick(producto); // se ejecuta el callback si existe
        });

        final OnDeleteClickListener deleteListener = onDeleteClick; // se guarda el callback de eliminacion
        holder.btnEliminar.setOnClickListener(v -> { // se configura el evento del boton eliminar
            if (deleteListener != null) deleteListener.onDeleteClick(producto); // se ejecuta el callback de eliminacion si existe
        });
    }

    @Override
    public int getItemCount() { // metodo que devuelve la cantidad de items
        return listaProductos.size(); // se devuelve el tamaño de la lista de productos
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder { // clase interna que representa un item en la vista
        public TextView tvNombre; // texto que muestra el nombre del producto
        public MaterialButton btnEliminar; // boton para eliminar el producto

        public ProductoViewHolder(View itemView) { // constructor del view holder
            super(itemView); // llamada a la clase padre
            tvNombre = itemView.findViewById(R.id.tvNombreProducto); // se vincula el texto de nombre
            btnEliminar = itemView.findViewById(R.id.btnEliminarProducto); // se vincula el boton de eliminar
        }
    }
}




