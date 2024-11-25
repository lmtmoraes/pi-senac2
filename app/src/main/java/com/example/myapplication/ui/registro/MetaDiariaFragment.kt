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
import com.example.myapplication.databinding.FragmentMetaDiariaBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MetaDiariaFragment : Fragment() {

    private lateinit var binding: FragmentMetaDiariaBinding
    private val viewModel: MetaViewModel by viewModels()
    private val args: MetaDiariaFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMetaDiariaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.carregarValorSalvo()


        val aguaMeta = args.aguaMeta
        val aguaTotalIngerida = args.aguaTotalIngerida

        val metaDiariaText = "Meta diária: $aguaMeta ml"
        val totalConsumidoText = "Total ingerido: $aguaTotalIngerida ml"
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val currentDate = LocalDate.now().format(formatter)

        binding.tvData.text = viewModel.data.value

        binding.tvMetaDiaria.text = metaDiariaText
        binding.tvTotalConsumido.text = totalConsumidoText

        binding.btnSalvarRegistro.setOnClickListener {
            viewModel.salvarValor(currentDate, aguaMeta, aguaTotalIngerida)
            viewModel.onSalvarRegistroClicked()
        }

        binding.tvAtualizarQuantidade.setOnClickListener {
            viewModel.onQuantidadeIngeridaClicked()
        }

        binding.tvEditarMeta.setOnClickListener {
            viewModel.onEditarMetaClicked()
        }

        setupObservers(aguaMeta, aguaTotalIngerida)
    }

    private fun setupObservers(meta: String, quantidade: String) {
        viewModel.salvarRegistroClicked.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Meta diária salva", Toast.LENGTH_SHORT).show()
            findNavController().navigate(MetaDiariaFragmentDirections.metaToMenu())
        }
        viewModel.quantidadeIngeridaClicked.observe(viewLifecycleOwner){
            findNavController().navigate(MetaDiariaFragmentDirections.metaToQuantidadeIngerida(meta, quantidade))
        }
        viewModel.editarMetaClicked.observe(viewLifecycleOwner){
            findNavController().navigate(MetaDiariaFragmentDirections.metaToEditarMeta(meta, quantidade))
        }
    }
}