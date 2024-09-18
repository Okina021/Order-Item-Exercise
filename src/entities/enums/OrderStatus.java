package entities.enums;

public enum OrderStatus {
    PENDING_PAYMENT(1),
    PROCESSING(2),
    SHIPPED(3),
    DELIVERED(4);

    private Integer code;

    OrderStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }

}
