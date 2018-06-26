package com.criticalmass.coroutines.models

data class PlaysList(
  val allPlays: Array<Map<String, AllPlays>>,
  val scoringPlays: Array<Int>,
  val penaltyPlays: Array<Int>
)

data class Play(
  val id: Int
)

data class AllPlays(
  val players: Array<String>,
  val result: Map<String, String>,
  val about: Map<String, String>,
  val team: Team
)
