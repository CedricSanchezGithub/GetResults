package com.example.getresults.models

import jakarta.persistence.*

@Entity
@Table
data class AuthorsEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,
    var name: String ? = null
)