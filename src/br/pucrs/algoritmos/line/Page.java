package br.pucrs.algoritmos.line;

/**
 * Created by Rodrigo on 27/05/2017.
 */
public class Page {
    private Integer quantity;
    private Integer pageNumber;

    public Page(Integer pageNumber) {
        this.pageNumber = pageNumber;
        this.quantity = 0;
    }

    public void sumOneQuantity() {
        this.quantity++;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n")
                .append("\t\t")
                .append("Page ")
                .append(pageNumber)
                .append("\n")
                .append("\t\t\t")
                .append("Quantity ")
                .append(quantity);
        return builder.toString();
    }
}
