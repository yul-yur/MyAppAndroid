package com.example.myappandroid

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

data class AppInfo(
    val name: String,
    val description: String,
    val category: String,
    val icon: ImageVector
)

val appList = listOf(
    AppInfo(
        name = "Почта",
        description = "Письма, вложения и удобные папки",
        category = "Продуктивность",
        icon = Icons.Default.Email
    ),
    AppInfo(
        name = "Телефон",
        description = "Звонки и журнал вызовов",
        category = "Связь",
        icon = Icons.Default.Phone
    ),
    AppInfo(
        name = "Календарь",
        description = "Встречи, события и напоминания",
        category = "Продуктивность",
        icon = Icons.Default.DateRange
    ),
    AppInfo(
        name = "Магазин",
        description = "Покупки и доставка до двери",
        category = "Покупки",
        icon = Icons.Default.ShoppingCart
    ),
    AppInfo(
        name = "Поиск",
        description = "Быстрый поиск по интернету",
        category = "Утилиты",
        icon = Icons.Default.Search
    ),
    AppInfo(
        name = "Фото",
        description = "Снимки, альбомы и редактор",
        category = "Медиа",
        icon = Icons.Default.Face
    ),
    AppInfo(
        name = "Пароли",
        description = "Безопасное хранение паролей",
        category = "Безопасность",
        icon = Icons.Default.Lock
    ),
    AppInfo(
        name = "Избранное",
        description = "Сохранённые материалы и закладки",
        category = "Утилиты",
        icon = Icons.Default.Star
    )
)