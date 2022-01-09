package com.mrmischievousx.notesapp.ui.Fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mrmischievousx.notesapp.Model.Notes
import com.mrmischievousx.notesapp.R
import com.mrmischievousx.notesapp.ViewModel.NotesViewModel
import com.mrmischievousx.notesapp.databinding.FragmentHomeBinding
import com.mrmischievousx.notesapp.ui.Adapter.NotesAdapter

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: NotesViewModel by viewModels()
    var oldNotesList = arrayListOf<Notes>()
    lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity?)!!.actionBar?.show()

        val glm = GridLayoutManager(context, 3)
        glm.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position % 3 == 2) {
                    3
                } else when (position % 4) {
                    1, 3 -> 1
                    0, 2 -> 2
                    else ->                     //never gonna happen
                        -1
                }
            }
        }

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        viewModel.getNotes().observe(viewLifecycleOwner, { notes ->

            oldNotesList = notes as ArrayList<Notes>

//            binding.rcvAllNotes.layoutManager = StaggeredGridLayoutManager(
//                2, StaggeredGridLayoutManager
//                    .VERTICAL
//            )
            binding.rcvAllNotes.layoutManager = glm
            adapter = NotesAdapter(requireContext(), notes)
            binding.rcvAllNotes.adapter = adapter
        })

        binding.btnAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Search for Notes"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                notesFilter(newText)
                return true
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun notesFilter(newText: String?) {
        val newFilterList = arrayListOf<Notes>()
        for (i in oldNotesList) {
            if (i.title.contains(newText!!))
                newFilterList.add(i)
        }
        adapter.filtering(newFilterList)
    }

}