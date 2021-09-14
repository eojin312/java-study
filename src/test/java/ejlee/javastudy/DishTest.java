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

    // 퀴즈 4-1
    @Test
    public void 중간연산과최종연산구분 () {
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
        long price = menuList.stream()
                .filter(d -> d.getPrice() > 20000) // Return 타입이 Stream 이므로 중간 연산자
                .distinct() // Return 타입이 Stream 이므로 중간 연산자
                .limit(3) // Return 타입이 Stream 이므로 중간 연산자
                .count(); // count () 내부로 들어가보니 Return 타입이 long 인 것을 확인, 즉 최종 연산자
        // then
        assertEquals(3, price);
    }

    @Test
    public void 프레디게이트로필터링 () {
        // given
        List<Food> foodList = new ArrayList<>();

        Food chicken = Food.builder().name("치킨").calories(3000).vegetarian(false).type(Food.Type.MEAT).build();
        Food salad = Food.builder().name("샐러드").calories(600).vegetarian(true).type(Food.Type.OTHER).build();
        Food hamburger = Food.builder().name("햄버거").calories(2200).vegetarian(false).type(Food.Type.OTHER).build();
        Food meat = Food.builder().name("고기").calories(1200).vegetarian(false).type(Food.Type.MEAT).build();
        Food fish = Food.builder().name("연어").calories(900).vegetarian(false).type(Food.Type.FISH).build();

        foodList.add(chicken);
        foodList.add(salad);
        foodList.add(hamburger);
        foodList.add(meat);
        foodList.add(fish);

        // when
        List<Food> filterList = foodList.stream().filter(Food::isVegetarian).collect(Collectors.toList());

        // then
        System.out.println(filterList);
    }
}
