package com.choonkending.pasxolio

import unfiltered.netty.Server
import unfiltered.netty.cycle.Planify
import unfiltered.request._
import unfiltered.response._

class NettyAppServer {
  private val port = 3000

  val server: Server = unfiltered.netty.Server.http(port)
    .plan(Planify {
        case req@GET(Path("/stocks")) => {
          val stocks = FetchStocksController.fetchStocks()
          stocks match {
            case Right(s) => ResponseString(StocksEncoder.toJson(s).spaces2)
            case _ => InternalServerError ~> ResponseString("Something went wrong")
          }

        }
      }
    )

  def start(): Unit = {
    println("Starting server ... ")
    server.start()
  }

  def stop(): Unit = {
    println("Stopping server ... ")
    server.stop()
  }
}
