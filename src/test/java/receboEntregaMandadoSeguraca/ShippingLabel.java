package receboEntregaMandadoSeguraca;

import java.util.Date;

public class ShippingLabel {

    private Customer from;
    private Customer to;
    private Integer priority;
    private String pod;
    private String carrier;
    private Date dateShipped;
    private Integer weight;
    private Integer quantity;
    private String shipToPostalCode;
    private String po;
    private String serialShippingContainer;

    public Customer getFrom() {
        return from;
    }

    public void setFrom(Customer from) {
        this.from = from;
    }

    public Customer getTo() {
        return to;
    }

    public void setTo(Customer to) {
        this.to = to;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public Date getDateShipped() {
        return dateShipped;
    }

    public void setDateShipped(Date dateShipped) {
        this.dateShipped = dateShipped;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getShipToPostalCode() {
        return shipToPostalCode;
    }

    public void setShipToPostalCode(String shipToPostalCode) {
        this.shipToPostalCode = shipToPostalCode;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getSerialShippingContainer() {
        return serialShippingContainer;
    }

    public void setSerialShippingContainer(String serialShippingContainer) {
        this.serialShippingContainer = serialShippingContainer;
    }
}
