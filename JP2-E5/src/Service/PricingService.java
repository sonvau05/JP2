package Service;

import Entity.Pricing;
import Interface.IGeneral;

import java.util.*;
import java.util.stream.Collectors;

public class PricingService implements IGeneral<Pricing> {
    private List<Pricing> pricings = new ArrayList<>();

    @Override
    public List<Pricing> getAll() {
        return pricings;
    }

    @Override
    public void add(Pricing pricing) {
        pricings.add(pricing);
    }

    @Override
    public Map<String, List<Pricing>> groupBy(String attribute) {
        if ("dateTime".equals(attribute)) {
            return pricings.stream()
                    .collect(Collectors.groupingBy(pricing -> pricing.getDateTime().toLocalDate().toString()));
        } else if ("tickerId".equals(attribute)) {
            return pricings.stream()
                    .collect(Collectors.groupingBy(pricing -> String.valueOf(pricing.getTickerId())));
        }
        return Collections.emptyMap();
    }

    public Double calculateAverageClosingPrice() {
        return pricings.stream()
                .collect(Collectors.averagingDouble(Pricing::getClosePrice));
    }

    public Optional<Pricing> findHighestPrice() {
        return pricings.stream()
                .max(Comparator.comparingDouble(Pricing::getClosePrice));
    }
}
