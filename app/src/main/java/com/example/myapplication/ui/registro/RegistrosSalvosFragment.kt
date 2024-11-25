package com.example.myapplication.ui.registro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRegistrosSalvosBinding

class RegistrosSalvosFragment : Fragment() {

    private lateinit var binding: FragmentRegistrosSalvosBinding
    private val viewModel: RegistrosSalvosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout using ViewBinding
        binding = FragmentRegistrosSalvosBinding.inflate(inflater, container, false)
        viewModel.carregarValorSalvo()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()

        binding.tvDate.text = viewModel.data.value.toString()

        binding.btnEdit.setOnClickListener {
            viewModel.onEditClicked()
        }
        binding.btnRemove.setOnClickListener {
            viewModel.onRemoveClicked()
        }
    }

    private fun setupObservers() {
        viewModel.editClicked.observe(viewLifecycleOwner){
            navigateToMetaDiaria()
        }
        viewModel.removeClicked.observe(viewLifecycleOwner){
            viewModel.salvarValor("", "0", "0")
            Toast.makeText(requireContext(), "Registro removido", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack(R.id.menuFragment, false)
        }
    }

    private fun navigateToMetaDiaria() {
        findNavController().navigate(RegistrosSalvosFragmentDirections.registrosSalvosToMetaDiaria(viewModel.valorSalvo.value.toString(),
            viewModel.valorIngerido.value.toString()))
    }
}