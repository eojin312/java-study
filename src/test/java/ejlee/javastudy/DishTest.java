package ejlee.javastudy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
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

    @Test
    public void 중간연산테스트 () {
        // given
        List<Dish> menuList = new ArrayList<>();
        Dish chicken = Dish.builder().name("후라이드 치킨").price(21000).build();
        Dish seasoningChicken = Dish.builder().name("BHC 양념 치킨").price(22000).build();
        Dish seasoningChicken2 = Dish.builder().name("교촌 양념 치킨").price(23000).build();
        Dish seasoningChicken3 = Dish.builder().name("또래오래 양념 치킨").price(34000).build();
        Dish seasoningChicken4 = Dish.builder().name("BBQ 양념 치킨").price(22000).build();
        Dish seasoningChicken5 = Dish.builder().name("피나치공 양념 치킨").price(20000).build();
        Dish seasoningChicken6 = Dish.builder().name("찜닭 (먹고싶음)").price(29000).build();
        Dish soyChicken = Dish.builder().name("간장 치킨").price(22000).build();
        Dish honeyChicken = Dish.builder().name("허니 콤보").price(21000).build();

        menuList.add(chicken);
        menuList.add(seasoningChicken);
        menuList.add(seasoningChicken2);
        menuList.add(seasoningChicken3);
        menuList.add(seasoningChicken4);
        menuList.add(seasoningChicken5);
        menuList.add(seasoningChicken6);
        menuList.add(soyChicken);
        menuList.add(honeyChicken);

        // when
        List<String> filterList = menuList.stream()
                .filter(d -> {
                    System.out.println("22000원 같거나 이상 음식명 : " + d.getName());
                    return d.getPrice() >= 22000;
                })
                .map(d -> {
                    System.out.println("뭐지 출력을 두번 함 : " + d.getName());
                    return d.getName();
                }) // filter 와 map 은 서로 다른 연산이지만 병합됨
                .limit(5)
                .collect(Collectors.toList());

        // then
        // stream 내부에서 limit 을 5 로 맞췄기 때문에
        assertEquals(5, filterList.size());
    }
}
