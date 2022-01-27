package com.food.api.batch.vendor;

import com.food.api.domain.Vendor;
import com.food.api.repository.VendorRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class VendorProcessor implements ItemProcessor<Vendor, Vendor> {

    @Autowired
    private VendorRepository vendorRepository;

    @Override
    public Vendor process(Vendor vendor) {
        Optional<Vendor> vendorOptional = vendorRepository.findById(vendor.getId());
        vendorOptional.ifPresent(v -> {
            v.setName(vendor.getName());
            v.setDescription(vendor.getDescription());
        });
        return vendor;
    }

}
