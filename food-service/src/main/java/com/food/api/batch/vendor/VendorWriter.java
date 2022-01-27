package com.food.api.batch.vendor;

import com.food.api.domain.Vendor;
import com.food.api.repository.VendorRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class VendorWriter implements ItemWriter<Vendor> {

    @Autowired
    private VendorRepository vendorRepository;

    @Override
    @Transactional
    public void write(List<? extends Vendor> vendors) {
        vendorRepository.saveAll(vendors);
    }
}
