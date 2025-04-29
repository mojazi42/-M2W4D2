package com.example.mvishopingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mvishopingapp.data.model.CartItem
import com.example.mvishopingapp.data.repository.CartRepositoryImpl
import com.example.mvishopingapp.data.source.InMemoryCartDataSource
import com.example.mvishopingapp.domain.usecase.AddItemToCartUseCase
import com.example.mvishopingapp.domain.usecase.GetCartItemsUseCase
import com.example.mvishopingapp.domain.usecase.RemoveItemFromCartUseCase
import com.example.mvishopingapp.domain.usecase.UpdateItemQuantityUseCase
import com.example.mvishopingapp.presentation.intent.CartIntent
import com.example.mvishopingapp.theme.MVIShopingAppTheme
import com.example.mvishopingapp.presentation.ui.CartScreen
import com.example.mvishopingapp.presentation.vm.CartViewModel
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataSource = InMemoryCartDataSource().apply {
            runBlocking {
                addItem(CartItem(1, "Test Item", 10.0, 2))
                addItem(CartItem(2, "Second Item", 15.0, 1))
            }
        }

        val repository = CartRepositoryImpl(dataSource)

// Create all 4 use cases
        val getCartItemsUseCase = GetCartItemsUseCase(repository)
        val addItemToCartUseCase = AddItemToCartUseCase(repository)
        val removeItemFromCartUseCase = RemoveItemFromCartUseCase(repository)
        val updateItemQuantityUseCase = UpdateItemQuantityUseCase(repository)

        val viewModel = CartViewModel(
            getCartItemsUseCase,
            addItemToCartUseCase,
            removeItemFromCartUseCase,
            updateItemQuantityUseCase
        )

        viewModel.handleIntent(CartIntent.LoadCart)


        setContent {
            CartScreen(viewModel)
        }

        setContent {
            MaterialTheme {
                CartScreen(viewModel)
            }
        }
    }
}


