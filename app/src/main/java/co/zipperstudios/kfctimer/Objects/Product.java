package co.zipperstudios.kfctimer.Objects;

import android.content.Context;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Andrei on 7/22/2016.
 */

public class Product extends RealmObject {
    //@PrimaryKey
    private long systemtime;

    private String product, mpackage;
    private int quantity;
    private long countdown;
    private int idFirstAlarm;
    private int idSecondAlarm;

    public Product() {}

    public Product(String _product, int _quantity, String _package, long _countdown, long _systemtime, int idFirstAlarm, int idSecondAlarm) {
        this.product = _product;
        this.quantity = _quantity;
        this.mpackage = _package;
        this.countdown = _countdown;
        this.systemtime = _systemtime;
        this.idFirstAlarm = idFirstAlarm;
        this.idSecondAlarm = idSecondAlarm;
    }

    public long getExpirationTimeStamp() {
        long dif = System.currentTimeMillis() - this.systemtime;
        return this.countdown - dif;
    }

    public int getFirstAlarm() { return this.idFirstAlarm; }

    public int getSecondAlarm() { return this.idSecondAlarm; }

    public void  setFirstAlarm(int alarm) { this.idFirstAlarm = alarm; }

    public void  setSecondAlarm(int alarm) { this.idSecondAlarm = alarm; }

    public long getSystemtime() { return this.systemtime; }

    public void setSystemtime(long time) { this.systemtime = time; }

    public String getProduct() {
        return this.product;
    }

    public void setProduct(String _product) {
        this.product = _product;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getQuantityString() {
        String quantity = "".concat(Long.toString(this.quantity)).concat(mpackage);

        return quantity;
    }

    public void setQuantity(int _quantity) {
        this.quantity = _quantity;
    }

    public String getMpackage() {
        return this.mpackage;
    }

    public void setMyPackage(String _package) {
        this.mpackage = _package;
    }

    public String getCountdownString() {
//        String countdown = "".concat(Integer.toString(mCountdown)).concat("'");
        Long timer = countdown / 60000;

        return (Long.toString(timer)).concat("'");
    }

    public long getCountdown() {
        return this.countdown;
    }

    public void setCountdown(long _countdown) {
        this.countdown = _countdown;
    }
}
