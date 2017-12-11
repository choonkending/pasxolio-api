package com.choonkending.pasxolio

import io.circe.Json
import io.circe.syntax._

object StockEncoder {
  def toJson(stock: Stock): Json = Json.obj(
    "name" -> stock.name.asJson,
    "price" -> stock.price.asJson
  )
}