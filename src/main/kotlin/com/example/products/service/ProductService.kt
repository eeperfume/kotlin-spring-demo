package com.example.products.service

import com.example.products.entity.Product
import com.example.products.repository.ProductRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {
    fun getAllProducts(): List<Product> = productRepository.findAll()

    fun getProductById(id: Long): Product = productRepository.findById(id).orElseThrow {
        IllegalArgumentException("$id 제품을 찾을 수 없습니다.")
    }

    fun addProduct(product: Product): Product {
        return productRepository.save(product)
    }

    fun updateProduct(id: Long, product: Product): Product {
        val existingProduct = getProductById(id)
        val updatedProduct = existingProduct.copy(name = product.name, price = product.price)
        return productRepository.save(updatedProduct)
    }

    fun deleteProduct(id: Long) {
        if (!productRepository.existsById(id)) {
            throw EntityNotFoundException("$id 제품을 찾을 수 없습니다.")
        }
        productRepository.deleteById(id)
    }
}