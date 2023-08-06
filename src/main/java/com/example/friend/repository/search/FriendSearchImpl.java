package com.example.friend.repository.search;

import com.example.friend.domain.Friend;
import com.example.friend.domain.QFriend;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class FriendSearchImpl extends QuerydslRepositorySupport implements FriendSearch {

    public FriendSearchImpl() {
        super(Friend.class);
    }

    @Override
    public Page<Friend> search1(Pageable pageable) {
        QFriend friend = QFriend.friend;
        JPQLQuery<Friend> query = from(friend); //select * from board where title like %"1"%
        query.where(friend.name.contains("1"));                 // where ~

        //pagin
        this.getQuerydsl().applyPagination(pageable,query);


        List<Friend> list = query.fetch();
        long count = query.fetchCount();



        return new PageImpl<>(list,pageable,count);
    }

    @Override
    public Page<Friend> searchAll(String[] types, String keyword, Pageable pageable) {
        QFriend friend = QFriend.friend;
        JPQLQuery<Friend> query = from(friend);
        // select * from board

        // where
        // (title = %java% or content = %java% or writer = %java%);
        if( (types != null && types.length > 0) && keyword != null ){ //검색 조건과 키워드가 있다면

            BooleanBuilder booleanBuilder = new BooleanBuilder(); // (

            for(String type: types){

                switch (type){
                    case "n":
                        booleanBuilder.or(friend.name.contains(keyword));
                        break;
                    case "p":
                        booleanBuilder.or(friend.phone.contains(keyword));
                        break;
                    case "e":
                        booleanBuilder.or(friend.email.contains(keyword));
                        break;
                }
            }//end for
            query.where(booleanBuilder);
        }//end if

        //bno > 0
        query.where(friend.fno.gt(0L));

        //select * from board
        // where
        // (title = %java% or content = %java% or writer = %java%)
        // and bno > 0L;

        System.out.println("query ===> " + query);

        //paging                            0, 10,bno.desc
        this.getQuerydsl().applyPagination(pageable, query);

        List<Friend> list = query.fetch();

        long count = query.fetchCount();

        return new PageImpl<>(list, pageable, count);
    }
}
