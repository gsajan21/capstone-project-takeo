package com.sajan.pms.service;

import com.sajan.pms.dto.CheckoutRequest;
import com.sajan.pms.model.Order;

import java.util.Optional;

public interface CheckoutService {

    Optional<Order> checkout(CheckoutRequest  request);

}
