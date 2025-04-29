package com.example.mvishopingapp.domain.usecase

import com.example.mvishopingapp.domain.repository.CartRepository

class UpdateItemQuantityUseCase(private val repository: CartRepository) {
    suspend operator fun invoke(itemId: Int, newQuantity: Int) {
        repository.updateItemQuantity(itemId, newQuantity)
    }
}
