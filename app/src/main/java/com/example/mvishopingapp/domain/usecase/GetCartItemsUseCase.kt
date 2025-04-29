package com.example.mvishopingapp.domain.usecase

import com.example.mvishopingapp.domain.repository.CartRepository

class GetCartItemsUseCase(private val repository: CartRepository) {
    suspend operator fun invoke() = repository.getCartItems()
}
