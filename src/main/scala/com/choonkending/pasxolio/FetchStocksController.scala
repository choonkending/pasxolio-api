package com.choonkending.pasxolio

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.client.googleapis.auth.oauth2.{GoogleAuthorizationCodeFlow, GoogleClientSecrets}
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport

import com.google.api.services.sheets.v4.SheetsScopes
import com.google.api.services.sheets.v4.Sheets

import java.io._
import java.util.Arrays

object FetchStocksController {
  private val APPLICATION_NAME = "PASXOLIO-API"
  private val DATA_STORE_DIR = new java.io.File(
    System.getProperty("user.home"),
    ".credentials/sheets.googleapis.com-scala-pasxolio"
  )
  private val JSON_FACTORY = JacksonFactory.getDefaultInstance()
  private val SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS_READONLY)
  private val HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport()
  private val DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR)

  private def authorize(): Credential = {
    // Load client secrets
    println(getClass.getClassLoader().getResourceAsStream("client_secret.json"))
    val in = getClass.getResourceAsStream("/client_secret.json")
    val clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in))

    val flow =
      new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT,
        JSON_FACTORY,
        clientSecrets,
        SCOPES)
      .setDataStoreFactory(DATA_STORE_FACTORY)
      .setAccessType("offline")
      .build()
    new AuthorizationCodeInstalledApp(
      flow,
      new LocalServerReceiver()
    ).authorize("user")
  }

  private def getSheetsService(): Sheets = {
    val credential = authorize()
    new Sheets.Builder(
      HTTP_TRANSPORT,
      JSON_FACTORY,
      credential)
    .setApplicationName(APPLICATION_NAME)
    .build()
  }

  def fetchStocks(): Unit = {
    val service = getSheetsService()
    val range = "Sheet1!A2:B7"
    val spreadsheetId = sys.env.get("SPREADSHEET_ID")
    spreadsheetId match {
      case Some(id) => {
        val values = service.spreadsheets().values()
          .get(id, range)
          .execute()
          .getValues()
          println(values)
      }
      case _ => {}
    }
  }
}

