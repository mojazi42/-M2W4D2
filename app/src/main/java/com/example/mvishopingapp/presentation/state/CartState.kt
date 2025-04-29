package com.example.mvishopingapp.presentation.state
import com.example.mvishopingapp.data.model.CartItem

sealed class CartState {
    object Loading : CartState()
    data class Success(val items: List<CartItem>, val total: Double) : CartState()
    data class Error(val message: String) : CartState()
}
