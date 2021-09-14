package ejlee.javastudy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DishTest {

    @Test
    void 스트림테스트() {
        // given
        List<Dish> menuList = new ArrayList<>();
        Dish chicken = Dish.builder().name("치킨").price(21000).build();
        Dish shish = Dish.builder().name("초밥").price(10000).build();
        Dish pig = Dish.builder().name("고기").price(14000).build();
        menuList.add(chicken);
        menuList.add(shish);
        menuList.add(pig);

        // when
        List<Dish> filterList = menuList.stream()
                .filter(m -> m.getPrice() >= 14000)
                .sorted(Comparator.comparing(Dish :: getPrice))
                .collect(Collectors.toList());

        // then
        assertTrue(filterList.size() >= 2);
    }
}
