package com.example.getresults.getapi

import com.example.getresults.models.AuthorsEntity
import org.jsoup.Jsoup
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val URL_TO_SCRAPE: String = "https://quotes.toscrape.com/"
const val CSS_SELECTOR_TO_SCRAPE: String = "small.author"
const val TIMER_UNTIL_SCRAPE: Int = 1000 * 60

@Service
class GetResultsScraper(
    private val getResultsService: GetResultsService
) {
    private val scrapedData = mutableListOf<String>()

    private var lastExecutionTime: Long = 0

    @Scheduled(fixedRate = (1000 * TIMER_UNTIL_SCRAPE).toLong()) // in ms
    fun scrapeData() {
        lastExecutionTime = System.currentTimeMillis()
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        println("Launching scrapeData() at ${LocalDateTime.now().format(formatter)}")

        try {
            val document = Jsoup.connect(URL_TO_SCRAPE).get()
            val authors = document.select(CSS_SELECTOR_TO_SCRAPE)
            scrapedData.clear() // Clear old data
            authors.forEach { author ->
                val authorName = author.text()
                scrapedData.add(authorName)
                println(scrapedData)
                getResultsService.saveDataScraped(AuthorsEntity(name = authorName))
            }
        } catch (e: Exception) {
            println("Error during scraping: ${e.message}")
            e.printStackTrace()
        }
    }

    @Scheduled(fixedRate = 10000)
    fun getTimeUntilNextExecution() {
        val currentTime = System.currentTimeMillis()
        val elapsedTime = currentTime - lastExecutionTime
        val timeUntilNext = (maxOf(TIMER_UNTIL_SCRAPE - elapsedTime, 0) / 1000) + 1
        println("Time until next execution: $timeUntilNext seconds")
    }
}
