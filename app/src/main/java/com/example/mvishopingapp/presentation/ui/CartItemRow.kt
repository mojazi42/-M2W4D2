package com.example.mvishopingapp.presentation.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mvishopingapp.data.model.CartItem

@Composable
fun CartItemRow(
    item: CartItem,
    onRemove: () -> Unit,
    onUpdateQuantity: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("${item.name}  x${item.quantity}", Modifier.weight(1f))
        Text("$${item.price * item.quantity}", Modifier.weight(1f))

        Button({ onUpdateQuantity(item.quantity + 1) }) { Text("+") }
        Button({
            if (item.quantity > 1) onUpdateQuantity(item.quantity - 1)
        }) { Text("-") }
        Button(onRemove) { Text("Remove") }
    }
}