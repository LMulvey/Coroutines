package com.criticalmass.coroutines

import com.criticalmass.coroutines.importer.nhl.games.ImportNHLGames
import kotlinx.coroutines.experimental.async
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RootController {
    @RequestMapping("/", produces = [APPLICATION_JSON_VALUE])
    fun index(): String {
        async { ImportNHLGames().start() }

        return "Imports Started"
    }
}
