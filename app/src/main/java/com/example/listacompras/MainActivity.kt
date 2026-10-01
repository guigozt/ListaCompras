package com.example.listacompras

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.listacompras.ui.theme.ListaComprasTheme


class Produto(
    val nome: String,
    val preco: Double,
    val quantidade: Int
) {

    fun calcularTotal(): Double {
        return preco * quantidade
    }
}


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ListaComprasTheme {
                ListaComprasApp()
            }
        }
    }
}


// ==========================================
// COMPOSABLE PRINCIPAL
// ==========================================

@Composable
fun ListaComprasApp() {

    var nome by remember {
        mutableStateOf("")
    }

    var preco by remember {
        mutableStateOf("")
    }

    var quantidade by remember {
        mutableStateOf("")
    }

    var total by remember {
        mutableStateOf(0.0)
    }

    val produtos = remember {
        mutableStateListOf<Produto>()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {

            Text("Lista de Compras")

            TextField(
                value = nome,
                onValueChange = {
                    nome = it
                },
                label = {
                    Text("Nome do produto")
                },
                placeholder = {
                    Text("Ex: Arroz")
                }
            )

            TextField(
                value = preco,
                onValueChange = {
                    preco = it
                },
                label = {
                    Text("Preço")
                },
                placeholder = {
                    Text("Ex: 25.90")
                }
            )

            TextField(
                value = quantidade,
                onValueChange = {
                    quantidade = it
                },
                label = {
                    Text("Quantidade")
                },
                placeholder = {
                    Text("Ex: 2")
                }
            )

            Button(
                onClick = {

                    val produto = Produto(
                        nome = nome,
                        preco = preco.toDouble(),
                        quantidade = quantidade.toInt()
                    )

                    produtos.add(produto)

                    total += produto.calcularTotal()

                    nome = ""
                    preco = ""
                    quantidade = ""
                }
            ) {
                Text("Cadastrar")
            }

            for (produto in produtos) {

                Text(
                    "${produto.nome} - " +
                            "${produto.quantidade} x R$ ${produto.preco} = " +
                            "R$ ${produto.calcularTotal()}"
                )
            }

            Text(
                "Total da compra: R$ $total"
            )
        }
    }
}


// ==========================================
// PREVIEW
// ==========================================

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewListaCompras() {

    ListaComprasTheme {
        ListaComprasApp()
    }
}
