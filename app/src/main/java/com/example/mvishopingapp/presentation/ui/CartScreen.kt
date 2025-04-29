package com.example.mvishopingapp.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mvishopingapp.presentation.intent.CartIntent
import com.example.mvishopingapp.data.model.CartItem
import com.example.mvishopingapp.presentation.state.CartState
import com.example.mvishopingapp.presentation.vm.CartViewModel

@Composable
fun CartScreen(viewModel: CartViewModel) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is CartState.Loading -> {
            CircularProgressIndicator()
        }
        is CartState.Success -> {
            val cartItems = (state as CartState.Success).items
            val total = (state as CartState.Success).total

            Column {
                LazyColumn {
                    items(cartItems) { item ->
                        CartItemRow(
                            item = item,
                            onRemove = {
                                viewModel.handleIntent(CartIntent.RemoveItem(item.id))
                            },
                            onUpdateQuantity = { newQuantity ->
                                viewModel.handleIntent(CartIntent.UpdateQuantity(item.id, newQuantity))
                            }
                        )
                    }
                }
                Text("Total: $total")
            }
        }
        is CartState.Error -> {
            Text("Error: ${(state as CartState.Error).message}")
        }
    }
}
