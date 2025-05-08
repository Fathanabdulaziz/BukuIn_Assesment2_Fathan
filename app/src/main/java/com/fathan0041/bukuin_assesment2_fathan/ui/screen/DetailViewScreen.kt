package com.fathan0041.bukuin_assesment2_fathan.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fathan0041.bukuin_assesment2_fathan.R
import com.fathan0041.bukuin_assesment2_fathan.navigation.Screen
import com.fathan0041.bukuin_assesment2_fathan.ui.theme.BukuIn_Assesment2_FathanTheme
import com.fathan0041.bukuin_assesment2_fathan.util.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailViewScreen(navController: NavHostController, id: Long?) {
    val context = LocalContext.current
    val factory = ViewModelFactory(context)
    val viewModel: DetailViewModel = viewModel(factory = factory)

    var showDialog by remember { mutableStateOf(false) }
    var judulBuku by remember { mutableStateOf("") }
    var kategori by remember { mutableStateOf("") }
    var harga by remember { mutableStateOf("") }
    var catatan by remember { mutableStateOf("") }

    LaunchedEffect(id) {
        if (id == null) return@LaunchedEffect
        val data = viewModel.getListBuku(id) ?: return@LaunchedEffect
        judulBuku = data.judulBuku
        kategori = data.kategori
        harga = data.harga
        catatan = data.catatan
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Text(text = stringResource(R.string.detail_catatan))
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.FormUbah.withId(id ?: 0L))
                    }) {
                        if (id != null) {
                            DeleteAction(
                                onEdit = {
                                    navController.navigate(Screen.FormUbah.withId(id))
                                },
                                onDelete = {
                                    showDialog = true
                                }
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.label_judul_buku, judulBuku),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
            Text(text = stringResource(R.string.label_kategori, kategori))
            Text(text = stringResource(R.string.label_harga, formatNumber(harga.toFloatOrNull() ?: 0f)))
            Text(text = stringResource(R.string.label_catatan, catatan))
        }
        if (id != null && showDialog){
            DisplayAlertDialog(
                onDismissRequest = { showDialog = false}) {
                showDialog = false
                viewModel.delete(id)
                navController.popBackStack()
            }
        }
    }
}
@Composable
fun DeleteAction(
    onEdit: () -> Unit,
    onDelete: () -> Unit
){
    var  expanded by remember { mutableStateOf(false) }
    IconButton(onClick = { expanded = true}) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.lainnya),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text(text = stringResource(id = R.string.edit_catatan)) },
                onClick = {
                    expanded = false
                    onEdit()
                }
            )

            DropdownMenuItem(
                text = { Text(text = stringResource(id = R.string.hapus)) },
                onClick = {
                    expanded = false
                    onDelete()
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailViewModel() {
    BukuIn_Assesment2_FathanTheme(themeId = 0) {
        DetailScreen(rememberNavController())
    }
}