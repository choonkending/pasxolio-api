package com.choonkending.pasxolio

object FetchStocksController {
  def fetchStocks(): Vector[Stock] = {
    Vector(Stock(name = "APT", price = "10.20"))
  }
}

