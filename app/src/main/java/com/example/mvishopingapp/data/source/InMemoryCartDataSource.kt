package com.example.mvishopingapp.data.source

import com.example.mvishopingapp.data.model.CartItem


class InMemoryCartDataSource {

    private val items = mutableListOf<CartItem>()

    suspend fun getItems(): List<CartItem> {
        return items.toList() // Always return a copy
    }

    suspend fun addItem(item: CartItem) {
        items.add(item)
    }

    suspend fun removeItem(itemId: Int) {
        items.removeAll { it.id == itemId }
    }

    suspend fun updateItemQuantity(itemId: Int, newQuantity: Int) {
        items.replaceAll { item ->
            if (item.id == itemId) item.copy(quantity = newQuantity) else item
        }
    }
}
