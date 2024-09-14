package com.example.getresults.getapi

import com.example.getresults.models.AuthorsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GetResultsRepository : JpaRepository<AuthorsEntity, String>
