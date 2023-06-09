package com.example.denem221

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class XmlResult {


    fun xmlCurrency(): List<Currency> {
        val arr = mutableListOf<Currency>()
        val url = "https://www.tcmb.gov.tr/kurlar/today.xml"
        val doc: Document = Jsoup.connect(url).timeout(15000).ignoreContentType(true).get()
        val elements: Elements = doc.getElementsByTag("Currency")
        for (item in elements) {
            val Isim = item.getElementsByTag("Isim").text()
            val ForexBuying = item.getElementsByTag("ForexBuying").text()
            val ForexSelling = item.getElementsByTag("ForexSelling").text()
            val BanknoteBuying = item.getElementsByTag("BanknoteBuying").text()
            val BanknoteSelling = item.getElementsByTag("BanknoteSelling").text()

            val currency =
                Currency(Isim, ForexBuying, ForexSelling, BanknoteBuying, BanknoteSelling);
            arr.add(currency)
        }
        return arr
    }
}