package com.example.quest6_187.uicontroller

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quest6_187.model.DataJK
import com.example.quest6_187.view.FormSiswa
import com.example.quest6_187.view.TampilSiswa
import com.example.quest6_187.viewmodel.SiswaViewModel

enum class Navigasi {
    Formulirku,
    Detail
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SiswaApp(
    modifier: Modifier = Modifier,
    viewModel: SiswaViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    Scaffold { isiRuang ->
        val uiState = viewModel.statusUI.collectAsState()

        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulirku.name,
            modifier = Modifier.padding(isiRuang)
        ) {
            composable(route = Navigasi.Formulirku.name) {
                val konteks = LocalContext.current

                FormSiswa(
                    pilihanJK = DataJK.JenisK.map { id ->
                        konteks.resources.getString(id)
                    },
                    OnSubmitBtnClick = { listData: MutableList<String> ->
                        viewModel.setSiswa(listData)
                        navController.navigate(Navigasi.Detail.name)
                    }
                )
            }

            composable(route = Navigasi.Detail.name) {
                TampilSiswa(
                    statusUiSiswa = uiState.value,
                    onBackBtnClick = {
                        cancelAndBackToFormulir(navController)
                    }
                )
            }
        }
    }
}

private fun cancelAndBackToFormulir(
    navController: NavHostController
) {
    navController.popBackStack(Navigasi.Formulirku.name, inclusive = false)
}
