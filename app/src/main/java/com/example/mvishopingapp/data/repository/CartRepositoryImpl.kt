package com.example.mvishopingapp.data.repository

import com.example.mvishopingapp.data.model.CartItem
import com.example.mvishopingapp.data.source.InMemoryCartDataSource
import com.example.mvishopingapp.domain.repository.CartRepository

class CartRepositoryImpl(private val dataSource: InMemoryCartDataSource) : CartRepository {

    override suspend fun getCartItems() = dataSource.getItems()

    override suspend fun addItemToCart(item: CartItem) = dataSource.addItem(item)

    override suspend fun removeItemFromCart(itemId: Int) = dataSource.removeItem(itemId)

    override suspend fun updateItemQuantity(itemId: Int, newQuantity: Int) = dataSource.updateItemQuantity(itemId, newQuantity)
}

