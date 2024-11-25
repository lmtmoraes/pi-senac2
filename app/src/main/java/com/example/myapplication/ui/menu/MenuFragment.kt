package com.example.myapplication.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private val viewModel: MenuViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupListeners()

        viewModel.carregarValorSalvo()
    }

    private fun setupListeners() {
        binding.btnConsultarRegistros.setOnClickListener {
            viewModel.onConsultarRegistrosClicked()
        }
        binding.btnRegistrosDia.setOnClickListener {
            viewModel.onRegistroDoDiaClicked()
        }
        binding.btnAtualizarLembretes.setOnClickListener {
            viewModel.onAtualizarFrequenciaClicked()
        }
    }

    private fun setupObservers() {
        viewModel.consultarClicked.observe(viewLifecycleOwner) {
            navigateToConsultarRegistros()
        }
        viewModel.registroDoDiaClicked.observe(viewLifecycleOwner) {
            navigateToRegistroDoDia()
        }
        viewModel.atualizarFrequenciaClicked.observe(viewLifecycleOwner) {
            navigateToAtualizarFrequencia()
        }
    }

    private fun navigateToConsultarRegistros() {
        if(viewModel.data.value?.isEmpty() == true){
            Toast.makeText(requireContext(), "Nenhum registro encontrado", Toast.LENGTH_SHORT).show()
        } else{
            findNavController().navigate(MenuFragmentDirections.actionMenuToRegistrosSalvos(viewModel.valorSalvo.value.toString(),
                viewModel.valorIngerido.value.toString()))
        }

    }

    private fun navigateToRegistroDoDia() {
        if (viewModel.data.value?.isEmpty() == true) {
            findNavController().navigate(MenuFragmentDirections.actionMenuToRegistro())
        } else {
            Toast.makeText(requireContext(), "Já existe um registro para hoje", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToAtualizarFrequencia() {
        findNavController().navigate(MenuFragmentDirections.actionMenuToFrequencia())
    }
}
