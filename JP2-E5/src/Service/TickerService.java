package Service;

import Entity.Ticker;
import Interface.IGeneral;

import java.util.*;
import java.util.stream.Collectors;

public class TickerService implements IGeneral<Ticker> {
    private List<Ticker> tickers = new ArrayList<>();

    @Override
    public List<Ticker> getAll() {
        return tickers;
    }

    @Override
    public void add(Ticker ticker) {
        tickers.add(ticker);
    }

    @Override
    public Map<String, List<Ticker>> groupBy(String attribute) {
        if ("exchange".equals(attribute)) {
            return tickers.stream()
                    .collect(Collectors.groupingBy(Ticker::getExchange));
        }
        return Collections.emptyMap();
    }
}
