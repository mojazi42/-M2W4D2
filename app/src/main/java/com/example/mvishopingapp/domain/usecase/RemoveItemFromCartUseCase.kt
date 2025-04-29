package com.example.mvishopingapp.domain.usecase

import com.example.mvishopingapp.domain.repository.CartRepository

class RemoveItemFromCartUseCase(private val repository: CartRepository) {
    suspend operator fun invoke(itemId: Int) {
        repository.removeItemFromCart(itemId)
    }
}
