package com.example.examen_moviles.ui.listar;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.examen_moviles.MainActivity;
import com.example.examen_moviles.models.Producto;

import java.util.ArrayList;
import java.util.Comparator;

public class ListarViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Producto>> mProductos;

    public MutableLiveData<ArrayList<Producto>> getmProductos(){
        if(mProductos == null){
            mProductos = new MutableLiveData<>();
        }
        return mProductos;
    }

    public void listarProductos(){

        ArrayList<Producto> productos = MainActivity.productos; //crear copia de prodcutos, asi no ordena el original

        productos.sort(new Comparator<Producto>() {

            @Override
            public int compare(Producto producto, Producto t1) {
                return Double.compare(t1.getPrecio(), producto.getPrecio());
            }
        });
        mProductos.setValue(productos);
    }
}