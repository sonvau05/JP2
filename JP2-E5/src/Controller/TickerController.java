package Controller;

import Entity.Ticker;
import Service.TickerService;

public class TickerController {
    private TickerService tickerService = new TickerService();

    public void createTicker(int id, String symbol, String exchange, String companyName) {
        Ticker ticker = new Ticker(id, symbol, exchange, companyName);
        tickerService.add(ticker);
    }

    public void displayAllTickers() {
        tickerService.getAll().forEach(System.out::println);
    }

    public void displayTickersByExchange() {
        tickerService.groupBy("exchange").forEach((exchange, tickers) -> {
            System.out.println("Exchange: " + exchange);
            tickers.forEach(System.out::println);
        });
    }
}
