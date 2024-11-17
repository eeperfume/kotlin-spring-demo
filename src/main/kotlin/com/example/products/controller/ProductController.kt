package com.example.products.controller

import com.example.products.entity.Product
import com.example.products.service.ProductService
import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/products")
class ProductController(private val productService: ProductService) {

    // 모든 제품 조회 (GET /products)
    @GetMapping
    fun getAllProducts(model: Model): String {
        model.addAttribute("products", productService.getAllProducts())
        return "products/product_list"
    }

    // 제품 조회 (GET /products/{id})
    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long, model: Model): String {
        val product = productService.getProductById(id)
        model.addAttribute("product", product)
        return "products/product_details"
    }

    // 제품 추가 (POST /products)
    @PostMapping
    fun addProduct(@RequestParam name: String, @RequestParam price: Int): String {
        val product = Product(
            name = name,
            price = price,
        )
        productService.addProduct(product)
        return "redirect:/products"
    }

    // 제품 업데이트 (PUT /products/{id})
    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: Long, @RequestBody product: Product): Product =
        productService.updateProduct(id, product)

    // 제품 삭제 (DELETE /products/{id})
    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Void> {
        return try {
            productService.deleteProduct(id)
            ResponseEntity.noContent().build() // 204 No Content
        } catch (e: EntityNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build() // 제품이 없는 경우 404 반환
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build() // 500 Internal Server Error
        }
    }

    // 제품 추가 화면 전달 (GET /products/new)
    @GetMapping("/new")
    fun showAddProductForm(model: Model): String {
        return "products/product_form"
    }
}