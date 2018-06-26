package com.criticalmass.coroutines.importer.nhl.plays

import com.criticalmass.coroutines.models.AllPlays

class ImportNHLPlays(val plays: AllPlays) {
  fun start() {
    println(plays)
  }
}
