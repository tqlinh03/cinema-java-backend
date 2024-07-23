package com.cinema.backend.modal.store;

import org.springframework.stereotype.Service;

@Service
public class StoreMapper {
    public Store toStore(StoreRequest storeRequest) {
        return Store.builder()
                .name(storeRequest.name())
                .address(storeRequest.address())
                .phone(storeRequest.phone())
                .build();
    }

}
