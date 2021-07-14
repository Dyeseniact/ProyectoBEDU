package models;

import org.jetbrains.annotations.NotNull;

public class Articles extends Product {
    public Articles(int id,String title, String author, String genre, Double price, int Stock, Boolean favorite, int discount, String namePublication, int yearPublication, String monthPublication) {
        super();
        this.namePublication = namePublication;
        this.yearPublication = yearPublication;
        this.monthPublication = monthPublication;
    }

    private String namePublication="";
    private int yearPublication;
    private String monthPublication;

    public String getNamePublication() {
        return namePublication;
    }

    public void setNamePublication(String namePublication) {
        this.namePublication = namePublication;
    }

    public int getYearPublication() {
        return yearPublication;
    }

    public void setYearPublication(int yearPublication) {
        this.yearPublication = yearPublication;
    }

    public String getMonthPublication() {
        return monthPublication;
    }

    public void setMonthPublication(String monthPublication) {
        this.monthPublication = monthPublication;
    }

    @Override
    public int getId() {
        return 0;
    }

    @NotNull
    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void setTitle(@NotNull String title) {

    }

    @NotNull
    @Override
    public String getAuthor() {
        return null;
    }

    @Override
    public void setAuthor(@NotNull String author) {

    }

    @NotNull
    @Override
    public String getGenre() {
        return null;
    }

    @Override
    public void setGenre(@NotNull String genre) {

    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public void setPrice(double price) {

    }

    @Override
    public int getStock() {
        return 0;
    }

    @Override
    public void setStock(int stock) {

    }

    @Override
    public boolean getFavorite() {
        return false;
    }

    @Override
    public void setFavorite(boolean favorite) {

    }

    @Override
    public int getDiscount() {
        return 0;
    }

    @Override
    public void setDiscount(int discount) {

    }

    @Override
    public void setId(int id) {

    }
}
