package com.choonkending.pasxolio

object Main {
  def main(args: Array[String]): Unit = {
    val stocks = FetchStocksController.fetchStocks()
    stocks match {
      case Right(s) => println(s)
      case Left(e) => println(e)
    }
  }
}
