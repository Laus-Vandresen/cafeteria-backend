package br.com.cafeteria.service;

import br.com.cafeteria.dto.OrderDto;
import br.com.cafeteria.enuns.OrderStatusEnum;
import br.com.cafeteria.mock.MockUtils;
import br.com.cafeteria.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderRepository orderRepository;

    @Before
    public void mockConfig() {
        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(MockUtils.getOrderEntityMock()));
        Mockito.when(orderRepository.findAll()).thenReturn(MockUtils.getAllOrdersMock());
    }

    @Test
    public void getTotalPriceTest() {
        OrderDto orderDto = orderService.getTotalPrice(1L);
        Assertions.assertThat(orderDto.getTotalPrice()).isEqualTo(BigDecimal.valueOf(350D));
        Assertions.assertThat(orderDto.getStatus()).isEqualTo(OrderStatusEnum.PAID);
    }

    @Test
    public void getAllOrdersTest() {
        List<OrderDto> allOrders = orderService.getAllOrders();
        Assertions.assertThat(allOrders.size()).isEqualTo(2);
        Assertions.assertThat(allOrders.get(0).getStatus()).isEqualTo(OrderStatusEnum.OPENED);
    }

}
