package sv.edu.ues.vl23003.loginappbase.ui.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import sv.edu.ues.vl23003.loginappbase.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder> {

    private final List<String> listaProductos;
    private final OnItemClickListener onItemClick;
    private final OnDeleteClickListener onDeleteClick;

    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(String item);
    }

    public ProductoAdapter(List<String> listaProductos, OnItemClickListener onItemClick, OnDeleteClickListener onDeleteClick) {
        this.listaProductos = listaProductos;
        this.onItemClick = onItemClick;
        this.onDeleteClick = onDeleteClick;
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        String producto = listaProductos.get(position);
        holder.tvNombre.setText(producto);

        final OnItemClickListener listener = onItemClick;
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(producto);
        });

        final OnDeleteClickListener deleteListener = onDeleteClick;
        holder.btnEliminar.setOnClickListener(v -> {
            if (deleteListener != null) deleteListener.onDeleteClick(producto);
        });
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public static class ProductoViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNombre;
        public MaterialButton btnEliminar;

        public ProductoViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombreProducto);
            btnEliminar = itemView.findViewById(R.id.btnEliminarProducto);
        }
    }
}




