package com.criticalmass.coroutines.importer.nhl

import com.criticalmass.coroutines.constants.NHL

class GameTargetHandler(val gameId: Int = NHL.nextGameId) {
    fun getNextGame(): Int {
        if (gameId == NHL.firstGameId) {
            println("Importing the First Game!")
        }

        // Update the next target game Id
        val nextGameId = gameId + 1
        NHL.nextGameId = nextGameId

        return gameId
    }
}
