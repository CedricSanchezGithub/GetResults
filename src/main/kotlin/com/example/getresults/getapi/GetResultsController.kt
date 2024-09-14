import com.example.getresults.getapi.GetResultsRepository
import com.example.getresults.getapi.GetResultsScraper
import com.example.getresults.getapi.GetResultsService
import com.example.getresults.models.AuthorsEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/scraper")
class GetResultsController(private val getResultsScraper: GetResultsScraper,
                           private val getResultsRepository: GetResultsRepository,
                            private val getResultsService: GetResultsService
) {

    @GetMapping("/datas")
    fun getData(): MutableList<AuthorsEntity> {
        println("Fetching data via GET /api/scraper/data")
        return getResultsService.getAllData()
    }

    @PostMapping("/scrape")
    fun triggerScrape(): ResponseEntity<String> {
        println("Triggering scrape via POST /api/scraper/scrape")
        getResultsScraper.scrapeData()
        return ResponseEntity.ok("Scraping process initiated")
    }
}