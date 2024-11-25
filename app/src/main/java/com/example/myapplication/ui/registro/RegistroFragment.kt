package com.example.myapplication.ui.registro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentRegistroBinding
import com.example.myapplication.ui.menu.MenuViewModel

class RegistroFragment : Fragment() {

    private lateinit var binding: FragmentRegistroBinding
    private val viewModel: RegistroViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    private fun setupListeners() {
        // Clique no botão Confirmar meta (ml)
        binding.btnConfirmarMetaMl.setOnClickListener {
            val metaMl = binding.etMetaMl.text.toString().trim()
            if (metaMl.isNotEmpty()) {
                // Lógica para salvar a meta em ml ou navegar
                findNavController().navigate(RegistroFragmentDirections.registroToMeta(metaMl, "0"))
            } else {
                Toast.makeText(requireContext(), "Por favor, insira a meta em ml.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnConfirmarMetaPeso.setOnClickListener {
            val peso = binding.etPeso.text.toString().trim()
            if (peso.isNotEmpty()) {
                // Lógica para calcular meta baseada no peso
                val metaCalculada = calcularMetaPorPeso(peso.toDouble()).toString()
                findNavController().navigate(RegistroFragmentDirections.registroToMeta(metaCalculada, "0"))
            } else {
                Toast.makeText(requireContext(), "Por favor, insira o peso.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calcularMetaPorPeso(peso: Double): Int {
        return (peso * 35).toInt()
    }
}
