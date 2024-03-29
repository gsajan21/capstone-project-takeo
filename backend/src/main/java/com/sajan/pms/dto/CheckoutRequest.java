package com.sajan.pms.dto;

import com.sajan.pms.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutRequest {
    private Integer userId;
    private Integer addressId;
}
