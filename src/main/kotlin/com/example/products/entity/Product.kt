package com.example.products.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "products")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val name: String,

    @Column
    val price: Int,

    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
)
//{
//    // JPA에서 필요한 기본 생성자
//    protected constructor() : this(0, "", 0, LocalDateTime.now())
//    /**
//     * JPA가 기본 생성자를 요구하는 이유
//     *
//     * 1. 프록시 객체 생성
//     *   JPA는 엔티티 객체를 직접 사용하지 않고, 프록시 객체(Hibernate에서는 Lazy Proxy)를 생성하여 사용함.
//     *   프록시 객체를 생성하려면 매개변수가 없는 생성자가 필요함.
//     *
//     * 2. 리플렉션 사용
//     *   JPA는 new 키워드를 사용하지 않고, 리플렉션을 통해 객체를 생성함.
//     *   리플렉션은 기본 생성자가 없으면 객체를 생성할 수 없음.
//     *
//     * 3. JPA 스펙 요구사항
//     *   JPA 스펙에서 엔티티 클래스는 반드시 public 또는 protected 기본 생성자를 가져야 한다고 명시돼 있음.
//     *   기본 생성자가 없으면 JPA 구현체(Hibernate 등)가 엔티티를 인스턴스화할 수 없으므로 오류가 발생함.
//     *   JPA 스펙:
//     *     "The entity class must have a no-arg constructor. The no-arg constructor must be public or protected."
//     *     "엔터티 클래스에는 인수 없는 생성자가 있어야 합니다. 인수 없는 생성자는 public 또는 protected여야 합니다."
//     *
//     * 4. 데이터베이스에서 엔티티를 재구성
//     *   기본 생성자가 없을 경우,
//     *   JPA는 데이터베이스에서 데이터 조회 후
//     *   해당 데이터를 기반으로 엔티티 클래스를 인스턴스화 할 수 없고,
//     *   인스턴스화 과정에서 InstantiationException이 발생함.
//     *   이에 따라 데이터베이스에서 엔티티를 재구성하기 위해 기본 생성자가 필요함.
//     */
//}
// 엔티티 클래스에서 기본 생성자를 반드시 생성해야하는 문제는 kotlin-jpa 플러그인을 통해 해결할 수 있음.