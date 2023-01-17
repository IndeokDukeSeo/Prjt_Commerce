package com.zerobase.cms.order.application;

import com.zerobase.cms.order.domain.model.Product;
import com.zerobase.cms.order.domain.product.AddProductCartForm;
import com.zerobase.cms.order.domain.product.AddProductForm;
import com.zerobase.cms.order.domain.product.AddProductItemForm;
import com.zerobase.cms.order.domain.redis.Cart;
import com.zerobase.cms.order.repository.ProductRepository;
import com.zerobase.cms.order.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartApplicationTest {

    @Autowired
    private CartApplication cartApplication;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @Test
    void ADD_TEST_MODIFY() {
        Long customerId = 100L;
        cartApplication.clearCart(customerId);

        Product p = add_product();
        Product result = productRepository.findWithProductItemsById(p.getId()).get();

        assertNotNull(result);

        assertEquals(result.getName(), "나이키");
        assertEquals(result.getDescription(), "신발입니다");
        assertEquals(result.getProductItems().size(), 3);
        assertEquals(result.getProductItems().get(0).getName(), "나이키0");
        assertEquals(result.getProductItems().get(0).getPrice(), 10000);
//        assertEquals(result.getProductItems().get(0).getCount(), 1);


        Cart cart = cartApplication.addCart(customerId, makeAddForm(result));
        //데이터 확인
        assertEquals(cart.getMessages().size(),0);

        cart = cartApplication.getCart(customerId);
        assertEquals(cart.getMessages().size(),1);

    }

    AddProductCartForm makeAddForm(Product p) {
        AddProductCartForm.ProductItem productItem =
                AddProductCartForm.ProductItem.builder()
                        .id(p.getProductItems().get(0).getId())
                        .name(p.getProductItems().get(0).getName())
                        .count(5)
                        .price(20000)
                        .build();
        return AddProductCartForm.builder()
                .id(p.getId())
                .sellerId(p.getSellerId())
                .name(p.getName())
                .description(p.getDescription())
                .items(List.of(productItem))
                .build();
    }

    Product add_product() {
        Long sellerId = 1L;

        AddProductForm form = makeProductForm("나이키", "신발입니다", 3);

        return productService.addProduct(sellerId, form);

    }

    private static AddProductForm makeProductForm(String name, String desc, int itemCount) {
        List<AddProductItemForm> itemForms = new ArrayList<>();
        for (int i = 0; i < itemCount; i++) {
            itemForms.add(makeProductItemForm(null, name + i));

        }
        return AddProductForm.builder()
                .name(name)
                .description(desc)
                .items(itemForms)
                .build();
    }

    private static AddProductItemForm makeProductItemForm(Long productId, String name) {
        return AddProductItemForm.builder()
                .productId(productId)
                .name(name)
                .price(10000)
                .count(10)
                .build();
    }

}