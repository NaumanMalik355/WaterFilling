package com.example.wafill;

public class OrderItem {
    private int mSize,mPrice,mQuantity;

    public OrderItem(int mSize, int mPrice, int mQuantity) {
        this.mSize = mSize;
        this.mPrice = mPrice;
        this.mQuantity = mQuantity;
    }

    public int getmSize() {
        return mSize;
    }

    public void setmSize(int mSize) {
        this.mSize = mSize;
    }

    public int getmPrice() {
        return mPrice;
    }

    public void setmPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public int getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }
}
