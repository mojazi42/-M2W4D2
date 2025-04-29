package com.example.mvishopingapp.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvishopingapp.data.model.CartItem
import com.example.mvishopingapp.domain.usecase.AddItemToCartUseCase
import com.example.mvishopingapp.domain.usecase.GetCartItemsUseCase
import com.example.mvishopingapp.presentation.intent.CartIntent
import com.example.mvishopingapp.presentation.state.CartState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


import com.example.mvishopingapp.domain.usecase.RemoveItemFromCartUseCase
import com.example.mvishopingapp.domain.usecase.UpdateItemQuantityUseCase


class CartViewModel(
    private val getItemsUseCase: GetCartItemsUseCase,
    private val addItemUseCase: AddItemToCartUseCase,
    private val removeItemUseCase: RemoveItemFromCartUseCase,
    private val updateQuantityUseCase: UpdateItemQuantityUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<CartState>(CartState.Loading)
    val state: StateFlow<CartState> = _state

    fun handleIntent(intent: CartIntent) {
        when (intent) {
            is CartIntent.LoadCart -> loadItems()
            is CartIntent.AddItem -> addItem(intent.item)
            is CartIntent.RemoveItem -> removeItem(intent.itemId)
            is CartIntent.UpdateQuantity -> updateQuantity(intent.itemId, intent.newQuantity)
        }
    }

    private fun loadItems() {
        viewModelScope.launch {
            try {
                _state.value = CartState.Loading
                val items = getItemsUseCase()
                val total = items.sumOf { it.price * it.quantity }
                _state.value = CartState.Success(items, total)
            } catch (e: Exception) {
                _state.value = CartState.Error("Failed to load cart")
            }
        }
    }

    private fun addItem(item: CartItem) {
        viewModelScope.launch {
            try {
                addItemUseCase(item)
                handleIntent(CartIntent.LoadCart)
            } catch (e: Exception) {
                _state.value = CartState.Error("Failed to add item")
            }
        }
    }

    private fun removeItem(itemId: Int) {
        viewModelScope.launch {
            try {
                removeItemUseCase(itemId)
                handleIntent(CartIntent.LoadCart)
            } catch (e: Exception) {
                _state.value = CartState.Error("Failed to remove item")
            }
        }
    }

    private fun updateQuantity(itemId: Int, quantity: Int) {
        viewModelScope.launch {
            try {
                updateQuantityUseCase(itemId, quantity)
                handleIntent(CartIntent.LoadCart)
            } catch (e: Exception) {
                _state.value = CartState.Error("Failed to update quantity")
            }
        }
    }
}
