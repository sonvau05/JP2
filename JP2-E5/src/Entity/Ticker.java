package Entity;

public class Ticker {
    private int id;
    private String symbol;
    private String exchange;
    private String companyName;

    public Ticker(int id, String symbol, String exchange, String companyName) {
        this.id = id;
        this.symbol = symbol;
        this.exchange = exchange;
        this.companyName = companyName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Ticker{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", exchange='" + exchange + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
