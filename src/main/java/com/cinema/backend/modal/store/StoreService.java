package com.cinema.backend.modal.store;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {
    public final StoreRepository storeRepository;
    public final StoreMapper mapper;
    public Integer create(StoreRequest storeRequest) {
        Store store = mapper.toStore(storeRequest);
        return storeRepository.save(store).getId();
    }

    public Integer update(Integer id, StoreRequest storeRequest) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy cửa hàng có id = " + id));

        if(!("Manage_Store").equals(store.getName())) {
            store.setName(storeRequest.name());
        }
        store.setAddress(storeRequest.address());
        store.setPhone(storeRequest.phone());

        return storeRepository.save(store).getId();
    }
}
