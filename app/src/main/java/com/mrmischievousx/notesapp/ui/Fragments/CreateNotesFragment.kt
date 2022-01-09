package com.mrmischievousx.notesapp.ui.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import com.mrmischievousx.notesapp.Model.Notes
import com.mrmischievousx.notesapp.R
import com.mrmischievousx.notesapp.ViewModel.NotesViewModel
import com.mrmischievousx.notesapp.databinding.FragmentCreateNotesBinding
import java.text.SimpleDateFormat
import java.util.*
import androidx.navigation.Navigation
import androidx.appcompat.app.AppCompatActivity
import com.mrmischievousx.notesapp.ui.Adapter.Generator

class CreateNotesFragment : Fragment() {
    lateinit var binding: FragmentCreateNotesBinding
    var priority: String = "1"
    private val viewModel: NotesViewModel by viewModels()
    val generateColor: Generator = Generator()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)

        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }

        binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)

        binding.pGreen.setOnClickListener {
            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
            priority = "1"
        }
        binding.pYellow.setOnClickListener {
            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
            priority = "2"
        }
        binding.pRed.setOnClickListener {
            binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
            priority = "3"
        }

        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.editTitle.text.toString()
        val subTitle = binding.editSubTitle.text.toString()
        val notes = binding.editNotes.text.toString()
        val currentDate = SimpleDateFormat("MMMM d, yyyy").format(Date())
        if (title.isNotEmpty() && subTitle.isNotEmpty() && notes.isNotEmpty()) {
            val newNote =
                Notes(
                    null,
                    title = title,
                    subTitle = subTitle,
                    notes = notes,
                    date = currentDate,
                    priority = priority
                )
            viewModel.addNotes(newNote)
            Toast.makeText(requireActivity(), "Notes Created Successfully", Toast.LENGTH_SHORT)
                .show()
        }
        Navigation.findNavController(it!!)
            .navigate(R.id.action_createNotesFragment_to_homeFragment)
    }

}

//Log.e("@@@@", "Pressed", )