package com.example.examen_moviles.ui.listar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen_moviles.R;
import com.example.examen_moviles.models.Producto;

import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolderProducto> {

    private ArrayList<Producto> productos;
    private LayoutInflater li;

    public ProductoAdapter(ArrayList<Producto> productos, LayoutInflater li) {
        this.productos = productos;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.item, parent, false);
        return new ViewHolderProducto(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProducto holder, int position) {
        Producto producto = productos.get(position);
        holder.tvCodigo.setText("cod(" + producto.getCodigo() + ")");
        holder.tvDescripcion.setText(producto.getDescripcion());
        holder.tvPrecio.setText("$" + String.valueOf(producto.getPrecio()));
        holder.tvStock.setText(String.valueOf(producto.getStock()) + " unidades");
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class ViewHolderProducto extends RecyclerView.ViewHolder {

        TextView tvCodigo, tvDescripcion, tvPrecio, tvStock;

        public ViewHolderProducto(@NonNull View itemView) {
            super(itemView);

            tvCodigo = itemView.findViewById(R.id.tvCodigo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvStock = itemView.findViewById(R.id.tvStock);

        }
    }
}