package com.example.examen_moviles.ui.listar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.examen_moviles.databinding.FragmentListarBinding;
import com.example.examen_moviles.models.Producto;

import java.util.ArrayList;

public class ListarFragment extends Fragment {

    private FragmentListarBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ListarViewModel listarViewModel =
                new ViewModelProvider(this).get(ListarViewModel.class);

        binding = FragmentListarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listarViewModel.getmProductos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Producto>>() {
            @Override
            public void onChanged(ArrayList<Producto> productos) {
                ProductoAdapter adaptador = new ProductoAdapter(productos, inflater);
                GridLayoutManager grilla = new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);

                binding.rvProductos.setAdapter(adaptador);
                binding.rvProductos.setLayoutManager(grilla);
            }
        });

        listarViewModel.listarProductos();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}