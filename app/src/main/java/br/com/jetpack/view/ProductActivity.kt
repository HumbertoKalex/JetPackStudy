package br.com.jetpack.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import br.com.jetpack.R

/**
 *Created by humbertokalex
 */

/*
    Este projeto tenta aplicar o modelo de arquitetura baseado em feature,
    então este modulo seria um exemplo de uma das features do app.
    Irei dar um exemplo de projeto multi modular envolvendo
    serviços de api, testes e styles em breve!
 */

class ProductActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController(R.id.nav_host_main)
    }
}