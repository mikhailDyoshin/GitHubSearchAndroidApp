package com.example.githubsearchapp.domain.usecase

class TransformFileSizeUseCase {

    operator fun invoke(fileSize: Int?): String? {

        if (fileSize == null) return null

        val kb = fileSize / 1024f
        val mb = kb / 1024f
        val gb = mb / 1024f

        val size = when {
            gb >= 1 -> "${roundSize(gb)} GB"
            mb >= 1 -> "${roundSize(mb)} MB"
            kb >= 1 -> "${roundSize(kb)} KB"
            else -> "$fileSize B"
        }

        return size
    }

    private fun roundSize(size: Float): String {
        return String.format("%.1f", size)
    }
}