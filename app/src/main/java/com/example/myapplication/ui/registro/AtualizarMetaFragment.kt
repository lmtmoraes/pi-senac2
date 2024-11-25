package com.example.myapplication.ui.registro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentAtualizarMetaBinding


class AtualizarMetaFragment : Fragment() {

    private lateinit var binding: FragmentAtualizarMetaBinding
    private val args: AtualizarMetaFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAtualizarMetaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnConfirmarMetaMl.setOnClickListener {
            val novaMeta = binding.etMetaMl.text.toString().trim()
            if (novaMeta.isNotEmpty()) {
                findNavController().navigate(AtualizarMetaFragmentDirections.atualizarMetaToMetaDiaria(novaMeta,
                    args.aguaTotalIngerida))
            } else {
                Toast.makeText(requireContext(), "Por favor, insira uma nova meta.", Toast.LENGTH_SHORT).show()
            }
        }


        binding.btnConfirmarMetaPeso.setOnClickListener {
            val peso = binding.etPeso.text.toString().trim()
            if (peso.isNotEmpty()) {
                // Lógica para calcular meta baseada no peso
                val metaCalculada = calcularMetaPorPeso(peso.toDouble()).toString()
                findNavController().navigate(AtualizarMetaFragmentDirections.atualizarMetaToMetaDiaria(metaCalculada, args.aguaTotalIngerida))
            } else {
                Toast.makeText(requireContext(), "Por favor, insira o peso.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun calcularMetaPorPeso(peso: Double): Int {
        return (peso * 35).toInt()
    }

}