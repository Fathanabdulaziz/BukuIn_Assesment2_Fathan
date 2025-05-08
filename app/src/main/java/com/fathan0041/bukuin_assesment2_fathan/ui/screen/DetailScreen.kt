package com.fathan0041.bukuin_assesment2_fathan.ui.screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fathan0041.bukuin_assesment2_fathan.R
import com.fathan0041.bukuin_assesment2_fathan.ui.theme.BukuIn_Assesment2_FathanTheme
import com.fathan0041.bukuin_assesment2_fathan.util.ViewModelFactory
import java.text.NumberFormat
import java.util.Locale

const val  KEY_ID_CATATAN ="idBuku"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long? = null){
    val context = LocalContext.current
    val factory = ViewModelFactory(context)
    val viewModel: DetailViewModel = viewModel(factory = factory)


    var  judulBuku by remember { mutableStateOf("") }
    var  kategori by remember { mutableStateOf("") }
    var harga by remember { mutableStateOf("") }
    var  catatan by remember { mutableStateOf("") }


    LaunchedEffect(Unit) {
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
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    if (id == null)
                        Text(text = stringResource(R.string.tambah_catatan))
                    else
                        Text(text = stringResource(R.string.edit_catatan))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {IconButton(onClick = {
                    if (judulBuku == ""||catatan == "" ||harga == ""){
                        Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                        return@IconButton
                    }
                    if (id == null){
                        viewModel.insert(judulBuku,kategori,harga, catatan)
                    }  else {
                        viewModel.update(id, judulBuku,kategori,harga, catatan)
                    }
                    navController.popBackStack()}) {
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = stringResource(R.string.simpan),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }

                }
            )
        }
    ) { padding ->
        FormCatatan(
            title = judulBuku,
            onTitleChange = {judulBuku = it},
            desc = catatan,
            onDescChange = {catatan = it},
            kategori = kategori,
            onKategori = {kategori = it},
            harga = harga,
            onHarga = {harga=it},
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun FormCatatan(
    title: String, onTitleChange: (String) -> Unit,
    kategori: String, onKategori: (String) -> Unit,
    desc: String, onDescChange: (String) -> Unit,
    harga: String, onHarga: (String) -> Unit,
    modifier: Modifier
){
    val hargaError by remember { mutableStateOf(false) }
    val list = kategoriList()
    val selectedList = remember { mutableStateListOf<String>() }

    Column (
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        OutlinedTextField(
            value = title,
            onValueChange = { onTitleChange(it)},
            label = { Text(text = stringResource(R.string.judul)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = kategori,
            onValueChange = { },
            readOnly = true,
            enabled = false,
            label = { Text(text = stringResource(R.string.kategori)) },
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                Box(
                    modifier = Modifier
                        .heightIn(max = 180.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        maxItemsInEachRow = 3
                    ) {
                        list.forEach {
                            FilterChip(
                                modifier = Modifier.padding(4.dp),
                                selected = it in selectedList,
                                onClick = {
                                    if (it in selectedList) {
                                        selectedList.remove(it)
                                    } else {
                                        selectedList.clear()
                                        selectedList.add(it)
                                    }
                                    onKategori(selectedList.joinToString())
                                },
                                label = { Text(text = it) }
                            )
                        }
                    }
                }
            }
        )
        OutlinedTextField(
            value = harga,
            onValueChange = { onHarga(it)},
            label = { Text(text = stringResource(R.string.harga)) },
            leadingIcon = { Text(text = "Rp") },
            supportingText = {
                Column {
                    ErrorHint(hargaError)
                    if (harga.toFloatOrNull() != null) {
                        Text("Price: Rp ${formatNumber(harga.toFloat())}")
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = desc,
            onValueChange = { onDescChange(it)},
            label = { Text(text = stringResource(R.string.isi_catatan)) },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxSize()

        )
    }
}

fun formatNumber(number: Float): String {
    val formatter = NumberFormat.getInstance(Locale("id", "ID"))
    return formatter.format(number)
}

@Composable
fun ErrorHint (isError: Boolean){
    if (isError){
        Text(text = stringResource(R.string.input_invalid))
    }
}

@Composable
fun kategoriList(): List<String> {
    return listOf(
        "Fantasy", " Science Fiction", "Dystopian",
        "Action & Adventure", "Detective & Mystery", "Thriller & Suspense",
        "Romance", "Horror", "Historical Fiction", "Contemporary Fiction",
        "Graphic Novels", "Biography", "History", "True Crime",
        "Science & Technology"

        )
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    BukuIn_Assesment2_FathanTheme(themeId = 0) {
        DetailScreen(rememberNavController())
    }
}