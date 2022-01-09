package com.mrmischievousx.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.MenuItem
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    lateinit var controller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller = findNavController(R.id.fragmentContainerView)

        setupActionBarWithNavController(controller)
    }

    override fun onNavigateUp(): Boolean {
        return controller.navigateUp() || super.onNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed();
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}