package com.example.kotlinDemo.kotlinjournalapp.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.user.restapi.user.models.Journal
import org.springframework.stereotype.Repository
@Repository
interface JournalRepository : JpaRepository<Journal, Long>{
}