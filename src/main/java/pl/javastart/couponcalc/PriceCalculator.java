package pl.javastart.couponcalc;

import java.util.List;

public class PriceCalculator {

    public double calculatePrice(List<Product> products, List<Coupon> coupons) {
        double sumToPay;
        if (products == null || products.isEmpty()) {
            return 0;
        } else if (coupons == null || coupons.isEmpty()) {
            sumToPay = getAllProductsPrice(products);
        } else {
            double discountBest = choseTheBestDiscountForBasket(products, coupons);
            sumToPay = getAllProductsPrice(products) - discountBest;
        }
        return Math.round(sumToPay * 100.0) / 100.0;
    }

    private static double getAllProductsPrice(List<Product> products) {
        double sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        return sum;
    }

    private double choseTheBestDiscountForBasket(List<Product> products, List<Coupon> coupons) {
        if (coupons != null) {
            double discountBest = 0;
            for (Coupon coupon : coupons) {
                double discountSum = 0;
                for (Product product : products) {
                    if (product.getCategory().equals(coupon.getCategory()) || coupon.getCategory() == null) {
                        discountSum += product.getPrice() * coupon.getDiscountValueInPercents() / 100;
                    }
                }
                if (discountSum > discountBest) {
                    discountBest = discountSum;
                }
            }
            return discountBest;
        } else {
            return 0;
        }
    }
}
