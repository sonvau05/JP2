import Controller.TickerController;
import Entity.Pricing;
import Service.PricingService;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        TickerController tickerController = new TickerController();
        tickerController.createTicker(1, "AAPL", "NASDAQ", "Apple Inc.");
        tickerController.createTicker(2, "GOOGL", "NASDAQ", "Alphabet Inc.");
        tickerController.createTicker(3, "MSFT", "NASDAQ", "Microsoft Corp.");

        tickerController.displayAllTickers();
        tickerController.displayTickersByExchange();

        PricingService pricingService = new PricingService();
        pricingService.add(new Pricing(1, 1, LocalDateTime.now(), 150.00, 155.00, 156.00));
        pricingService.add(new Pricing(2, 2, LocalDateTime.now(), 2200.00, 2210.00, 2220.00));
        pricingService.add(new Pricing(3, 3, LocalDateTime.now(), 300.00, 310.00, 320.00));

        System.out.println("Average closing price: " + pricingService.calculateAverageClosingPrice());
        pricingService.findHighestPrice().ifPresent(p -> System.out.println("Highest price: " + p.getClosePrice()));
    }
}

