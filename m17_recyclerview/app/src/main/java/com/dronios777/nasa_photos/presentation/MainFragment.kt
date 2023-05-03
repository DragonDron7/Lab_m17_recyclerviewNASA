package com.dronios777.nasa_photos.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dronios777.nasa_photos.R
import com.dronios777.nasa_photos.data.FotoModel
import com.dronios777.nasa_photos.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MainFragment : Fragment() {

    /* companion object {
         fun newInstance() = MainFragment()
     }*/

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    private val photoAdapter = PhotoListAdapter { photo -> onItemClick(photo) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView1.adapter = photoAdapter

        //слушатель для обновления фото
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }

        //показываем/скрываем индикатор загрузки
        viewModel.isLoading.onEach {
            binding.swipeRefresh.isRefreshing = it
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        //загружаем фото в адаптер
        viewModel.photo.onEach {
            //   photoAdapterOld.setData(it)
            photoAdapter.submitList(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

    private fun onItemClick(item: FotoModel) {
        val bundle = Bundle() //переменная для набора данных
        bundle.putString("Img_src", item.img_src)
        findNavController().navigate(R.id.photoFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}



























