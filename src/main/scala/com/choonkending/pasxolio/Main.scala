package com.choonkending.pasxolio


object Main {
  def main(args: Array[String]): Unit = {
    val server = new NettyAppServer()
    server.start()
  }
}
