package com.criticalmass.coroutines

import com.criticalmass.coroutines.importer.nhl.players.ImportNHLPlayers
import kotlinx.coroutines.experimental.async
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RootController {
    @RequestMapping("/", produces = [APPLICATION_JSON_VALUE])
    fun index() {
        async { ImportNHLPlayers().start() }
    }
}
