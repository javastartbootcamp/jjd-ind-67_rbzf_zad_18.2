package pl.javastart.couponcalc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceCalculatorTest {

    @Test
    public void shouldReturnZeroForNoProducts() {
        // given
        PriceCalculator priceCalculator = new PriceCalculator();

        // when
        double result = priceCalculator.calculatePrice(null, null);

        // then
        assertThat(result).isEqualTo(0.);
    }

    @Test
    public void shouldReturnPriceForSingleProductAndNoCoupons() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 5.99, Category.FOOD));

        // when
        double result = priceCalculator.calculatePrice(products, null);

        // then
        assertThat(result).isEqualTo(5.99);
    }

    @Test
    public void shouldReturnPriceForSingleProductAndOneCoupon() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 5.99, Category.FOOD));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(Category.FOOD, 20));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result).isEqualTo(4.79);
    }

    @Test
    public void shouldReturnPriceForAllProductsAndOneCouponWithoutCategory() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Kabanosy", 40, Category.FOOD));
        products.add(new Product("Ceramiczny zestaw sniadaniowy dla 2 osob", 60, Category.HOME));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(null, 20));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result).isEqualTo(80);
    }

    @Test
    public void shouldReturnPriceForAllProductsAndOneCouponWithCategory() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Kabanosy", 40, Category.FOOD));
        products.add(new Product("Ceramiczny zestaw sniadaniowy dla 2 osob", 60, Category.HOME));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(Category.FOOD, 20));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result).isEqualTo(92);
    }

    @Test
    public void shouldReturnPriceForAllProductsAndMultiplyCouponsWithCategory() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 6, Category.FOOD));
        products.add(new Product("Opony", 100, Category.CAR));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(Category.FOOD, 50));
        coupons.add(new Coupon(Category.CAR, 10));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result).isEqualTo(96);
    }

    @Test
    public void shouldReturnPriceForAllProductsAndMultiplyCouponsWithOrWithoutCategory() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 6, Category.FOOD));
        products.add(new Product("Kabanosy", 40, Category.FOOD));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(Category.FOOD, 50));
        coupons.add(new Coupon(null, 10));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result).isEqualTo(23);
    }
}