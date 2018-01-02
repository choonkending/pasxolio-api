package com.choonkending.pasxolio

import io.circe.Json
import io.circe.syntax._

object StocksEncoder {
  def toJson(stocks: Vector[Stock]): Json = stocks.map(StockEncoder.toJson).asJson
}
