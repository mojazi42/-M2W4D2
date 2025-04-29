package com.example.mvishopingapp.domain.repository

import com.example.mvishopingapp.data.model.CartItem

interface CartRepository {
    suspend fun getCartItems(): List<CartItem>
    suspend fun addItemToCart(item: CartItem)
    suspend fun removeItemFromCart(itemId: Int)
    suspend fun updateItemQuantity(itemId: Int, newQuantity: Int)
}

