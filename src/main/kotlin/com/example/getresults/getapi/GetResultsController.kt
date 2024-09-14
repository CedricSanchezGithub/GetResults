import com.example.getresults.getapi.GetResultsScraper
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/scraper")
class GetResultsController(private val getResultsScraper: GetResultsScraper) {

    @GetMapping("/data")
    fun getData(): ResponseEntity<List<String>> {
        println("Fetching data via GET /api/scraper/data")
        return ResponseEntity.ok(getResultsScraper.getScrapedData())
    }

    @PostMapping("/scrape")
    fun triggerScrape(): ResponseEntity<String> {
        println("Triggering scrape via POST /api/scraper/scrape")
        getResultsScraper.scrapeData()
        return ResponseEntity.ok("Scraping process initiated")
    }
}