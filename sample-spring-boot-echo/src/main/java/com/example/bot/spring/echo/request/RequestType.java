package com.example.bot.spring.echo.request;

/**
 * Created by yoichiro.manabe on 2016/11/21.
 */
public enum RequestType {
    GET_ITEM_LIST(0, "商品一覧"),
    GET_ITEM(1, "商品"),
    GET_ITEM_STOCK(2, "在庫"),
    UNKNOWN(9, "不明"),
    ;

    private final Integer id;
    private final String  displayString;

    RequestType(Integer id, String displayString){
        this.id = id;
        this.displayString = displayString;
    }

    public static RequestType valueOf(int id){
        for (RequestType v : values()) {
            if (v.getId() == id) {
                return v;
            }
        }

        throw new IllegalArgumentException("no such enum object for the id: " + id);
    }

    public Integer getId(){
        return id;
    }

    public String getDisplayString(){
        return displayString;
    }
}