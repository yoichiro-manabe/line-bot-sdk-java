package com.example.bot.spring.echo.reuse;

import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by yoichiro.manabe on 2016/11/21.
 */
public class ItemService {

    private ItemRepository itemRepository;

    public ItemService(){
        itemRepository = new ItemRepository();
    }

    public Optional<Item> findItem(String itemName){
        return itemRepository.getItems().stream().filter(item -> {
            return !StringUtils.isEmpty(itemName) && item.getItemName()
                                                         .contains(itemName);
        }).findFirst();
    }

    public ItemCollection findAllItem(){
        return new ItemCollection( itemRepository.getItems());
    }
}
