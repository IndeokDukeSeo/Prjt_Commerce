package com.zerobase.cms.order.application;


import com.zerobase.cms.order.client.UserClient;
import com.zerobase.cms.order.client.user.ChangeBalanceForm;
import com.zerobase.cms.order.client.user.CustomerDto;
import com.zerobase.cms.order.domain.model.ProductItem;
import com.zerobase.cms.order.domain.redis.Cart;
import com.zerobase.cms.order.exception.CustomException;
import com.zerobase.cms.order.exception.ErrorCode;
import com.zerobase.cms.order.service.ProductItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class OrderApplication {
    private final CartApplication cartApplication;
    private final UserClient userClient;
    private final ProductItemService productItemService;

    @Transactional
    public void order(String token, Cart cart) {
        //1. 기존 카트 삭제
        //2. 또는 선택 주문
        Cart orderCart = cartApplication.refreshCart(cart);
        if (orderCart.getMessages().size() > 0) {
            throw new CustomException(ErrorCode.ORDER_FAILED);
        }

        CustomerDto customerDto = userClient.getCustomerInfo(token).getBody();

        int totalPrice = getTotalPrice(cart);
        if (customerDto.getBalance() < totalPrice) {
            throw new CustomException(ErrorCode.ORDER_FAILED_NO_MONEY);
        }

        //Roll-back 전략고려
        userClient.changeBalance(token,
                ChangeBalanceForm.builder()
                        .from("USER")
                        .message("Order")
                        .money(-totalPrice)
                        .build());

        for (Cart.Product product : orderCart.getProducts()) {
            for (Cart.ProductItem cartItem : product.getItems()) {
                ProductItem productItem = productItemService.getProductItem(cartItem.getId());
                productItem.setCount(productItem.getCount() - cartItem.getCount());
            }
        }
    }


    private Integer getTotalPrice(Cart cart) {

        return cart.getProducts().stream().flatMapToInt(product ->
                        product.getItems().stream().flatMapToInt(
                                productItem -> IntStream.of(productItem.getPrice() * productItem.getCount())))
                .sum();
    }

    //물건 주문가능상태 확인
    //가격변동이 있는지 확인
    //고객의 돈이 충분한지
    //상품의 재고 관리
}
