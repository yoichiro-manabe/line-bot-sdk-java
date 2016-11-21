package com.example.bot.spring.echo.reuse;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoichiro.manabe on 2016/11/21.
 */
public class ItemCollection{

    private List<Item> items = new ArrayList<>();

    public ItemCollection(){
    }

    public ItemCollection(List<Item> items){
        this.items = items;
    }

    public void add(Item item){
        this.items.add(item);
    }

    public List<Item> get(){
        return new ArrayList<>(items);
    }

    public String message(){

        String separator = System.getProperty("line.separator");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            String template = "%s[%s] %d" + separator;
            sb.append(String.format(template, item.getItemName(), item.getClass(), item.getItemPrice()));
        }
        return sb.toString().substring(0, 30) + "...などがあります。";
    }
}
