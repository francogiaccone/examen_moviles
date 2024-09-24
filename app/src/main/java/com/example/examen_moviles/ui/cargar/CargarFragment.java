package com.example.examen_moviles.ui.cargar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.examen_moviles.databinding.FragmentCargarBinding;

public class CargarFragment extends Fragment {

    private FragmentCargarBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CargarViewModel cargarViewModel =
                new ViewModelProvider(this).get(CargarViewModel.class);

        binding = FragmentCargarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        cargarViewModel.getMMensaje().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

                //limpiar campos si se agrega el producto
                if (s.equals("Producto agregado")) {
                    binding.etCodigo.setText("");
                    binding.etDescripcion.setText("");
                    binding.etPrecio.setText("");
                    binding.etStock.setText("");
                }

                binding.tvError.setText(s);

                //implementacion para borrar el mensaje de aviso cuando pase los 3 segundos
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.tvError.setText("");
                    }
                }, 3000);
            }
        });

        binding.btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = binding.etCodigo.getText().toString().trim();
                String descripcion = binding.etDescripcion.getText().toString().trim();
                String precio = binding.etPrecio.getText().toString().trim();
                String stock = binding.etStock.getText().toString().trim();

                cargarViewModel.cargarProducto(codigo, descripcion, precio, stock);
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}