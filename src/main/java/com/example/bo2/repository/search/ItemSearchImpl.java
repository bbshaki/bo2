package com.example.bo2.repository.search;

import com.example.bo2.entity.Item;
import com.example.bo2.entity.QItem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class ItemSearchImpl extends QuerydslRepositorySupport implements ItemSearch {

    public ItemSearchImpl() {
        super(Item.class);
    }

    @Override
    public Page<Item> searchAll(String[] types, String keyword, Pageable pageable) {
        QItem item = QItem.item;

        JPQLQuery<Item> query = from(item);

        if ((types != null && types.length > 0) && keyword != null){
            BooleanBuilder booleanBuilder = new BooleanBuilder();

            for(String string : types){
                switch (string){
                    case "n":
                        booleanBuilder.or(item.itemName.contains(keyword));
                        break;
                    case "e":
                        booleanBuilder.or(item.itemEx.contains(keyword));
                        break;
                    case "s":
                        booleanBuilder.or(item.seller.contains(keyword));
                        break;
                }
            }
            query.where(booleanBuilder);
            System.out.println("검색 조건 추가 : " + query);
        }
        query.where(item.ino.gt(0L));
        System.out.println("0보다 큰 조건 ino가" + query);

        this.getQuerydsl().applyPagination(pageable, query);

        List<Item> itemList = query.fetch();
        itemList.forEach(item1 -> log.info(item1));
        Long count = query.fetchCount();

        System.out.println(count);

        return new PageImpl<>(itemList, pageable, count);
    }
}
















