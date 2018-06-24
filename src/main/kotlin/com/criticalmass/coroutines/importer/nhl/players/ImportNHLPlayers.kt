package com.criticalmass.coroutines.importer.nhl.players

import com.criticalmass.coroutines.constants.Hashids
import com.criticalmass.coroutines.constants.NHL
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager

class ImportNHLPlayers {
    fun start() {
        val hashids = Hashids.config
        FuelManager.instance.basePath = NHL.statsEndpoint

        val (request, response, result) = Fuel.get("/people/8446899").responseString()
        print(result.get())
    }
}
