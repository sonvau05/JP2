package Entity;

import java.time.LocalDate;

public class StatisticsView {
    private int id;
    private int view;
    private int addToCart;
    private int checkOut;
    private LocalDate createAtDate;

    public StatisticsView() {;}

    public StatisticsView(int id, LocalDate createAtDate, int checkOut, int addToCart, int view) {
        this.id = id;
        this.createAtDate = createAtDate;
        this.checkOut = checkOut;
        this.addToCart = addToCart;
        this.view = view;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreateAtDate() {
        return createAtDate;
    }

    public void setCreateAtDate(LocalDate createAtDate) {
        this.createAtDate = createAtDate;
    }

    public int getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(int checkOut) {
        this.checkOut = checkOut;
    }

    public int getAddToCart() {
        return addToCart;
    }

    public void setAddToCart(int addToCart) {
        this.addToCart = addToCart;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getMonthOfDate() {
        return this.createAtDate.getMonthValue();
    }

    public int getYearOfDate() {
        return this.createAtDate.getYear();
    }
}
