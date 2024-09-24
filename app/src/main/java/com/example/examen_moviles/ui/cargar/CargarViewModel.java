package com.example.examen_moviles.ui.cargar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.examen_moviles.MainActivity;
import com.example.examen_moviles.models.Producto;

public class CargarViewModel extends ViewModel {

    private MutableLiveData<String> mMensaje;

    public LiveData<String> getMMensaje() {
        if(mMensaje == null){
            mMensaje = new MutableLiveData<>();
        }
        return mMensaje;
    }

    public void cargarProducto(String codigo, String descripcion, String precioString, String stockString) {

        //validar campos vacios
        if (codigo.isEmpty() || descripcion.isEmpty() || precioString.isEmpty() || stockString.isEmpty()) {
            mMensaje.setValue("Hay campos vacios");
            return;
        }

        //buscar producto repetido
        for (Producto prod : MainActivity.productos) {
            if (prod.getCodigo().equals(codigo)) {
                mMensaje.setValue("Ya existe un producto con ese codigo");
                return;
            }
        }

        try {
            double precio = Double.parseDouble(precioString);
            int stock = Integer.parseInt(stockString);

            Producto producto = new Producto(codigo, descripcion, precio, stock);

            MainActivity.productos.add(producto);

            mMensaje.setValue("Producto agregado");
        } catch (NumberFormatException e) {
            mMensaje.setValue("Ingrese valores numericos en Precio y/o Stock");
        }
    }
}