package com.example.mvishopingapp.domain.usecase

import com.example.mvishopingapp.data.model.CartItem
import com.example.mvishopingapp.domain.repository.CartRepository

class AddItemToCartUseCase(private val repository: CartRepository) {
    suspend operator fun invoke(item: CartItem) = repository.addItemToCart(item)
}
