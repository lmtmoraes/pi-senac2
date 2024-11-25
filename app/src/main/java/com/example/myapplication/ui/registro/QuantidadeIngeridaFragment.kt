package com.example.myapplication.ui.registro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentQuantidadeIngeridaBinding

class QuantidadeIngeridaFragment : Fragment() {

    private lateinit var binding: FragmentQuantidadeIngeridaBinding
    private val args: QuantidadeIngeridaFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuantidadeIngeridaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnConfirmarMetaMl.setOnClickListener {
            val quantidadeIngerida = binding.etMetaMl.text.toString().trim()
            if (quantidadeIngerida.isNotEmpty()) {
                findNavController().navigate(QuantidadeIngeridaFragmentDirections.quantidadeIngeridaToMeta(args.aguaMeta,
                    quantidadeIngerida))
            } else {
                Toast.makeText(requireContext(), "Por favor, insira a quantidade ingerida.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
