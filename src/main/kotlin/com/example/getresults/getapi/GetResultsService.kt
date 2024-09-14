package com.example.getresults.getapi

import com.example.getresults.models.AuthorsEntity
import org.springframework.stereotype.Service

@Service
class GetResultsService(private val getResultsRepository: GetResultsRepository) {
    fun saveDataScraped(data: AuthorsEntity): AuthorsEntity {
        return getResultsRepository.save(data)
    }
}