package ejlee.javastudy;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
public class Dish {
    private String name;
    private int price;
}
