package digital.lett.netshoeswebscraper.entity;

import java.awt.*;
import java.math.BigDecimal;

public record Product(
        String name,
        String description,
        BigDecimal price,
        String imageUrl
) {
}
