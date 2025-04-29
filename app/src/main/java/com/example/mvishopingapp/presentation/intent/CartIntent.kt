package com.example.mvishopingapp.presentation.intent

import com.example.mvishopingapp.data.model.CartItem


sealed class CartIntent {
    object LoadCart : CartIntent()
    data class AddItem(val item: CartItem) : CartIntent()
    data class RemoveItem(val itemId: Int) : CartIntent()
    data class UpdateQuantity(val itemId: Int, val newQuantity: Int) : CartIntent()
}
