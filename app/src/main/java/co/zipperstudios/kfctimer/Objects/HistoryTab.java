package co.zipperstudios.kfctimer.Objects;

import io.realm.RealmObject;

/**
 * Created by Andrei on 7/23/2016.
 */

public class HistoryTab extends RealmObject {

    private long SystemTime;
    private String Product, Quantity;

    public HistoryTab() {}

    public long getSystemTime() {
        return this.SystemTime;
    }

    public String getProduct() {
        return this.Product;
    }

    public String getQuantity() {
        return this.Quantity;
    }

    public void setSystemTime(long time) {
        this.SystemTime = time;
    }

    public void setProduct(String product) {
        this.Product = product;
    }

    public void setQuantity(String quantity) {
        this.Quantity = quantity;
    }
}
