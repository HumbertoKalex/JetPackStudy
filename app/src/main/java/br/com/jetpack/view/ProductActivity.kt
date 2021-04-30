package br.com.jetpack.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import br.com.jetpack.R

/**
 *Created by humbertokalex
 */

class ProductActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController(R.id.nav_host_main)
    }
}