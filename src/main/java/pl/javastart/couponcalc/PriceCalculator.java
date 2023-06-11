package pl.javastart.couponcalc;

import java.util.List;

public class PriceCalculator {

    public double calculatePrice(List<Product> products, List<Coupon> coupons) {
        double sum = 0;
        if (products == null) {
            return 0;
        } else if (coupons == null) {
            for (Product product : products) {
                sum += product.getPrice();
            }
        } else if (coupons.size() == 1 && coupons.get(0).getCategory() == null) {
            for (Product product : products) {
                sum += product.getPrice() * (100 - coupons.get(0).getDiscountValueInPercents()) / 100;
            }
        } else if (coupons.size() == 1 && coupons.get(0).getCategory() != null) {
            for (Product product : products) {
                if (product.getCategory().equals(coupons.get(0).getCategory())) {
                    sum += product.getPrice() * (100 - coupons.get(0).getDiscountValueInPercents()) / 100;
                } else {
                    sum += product.getPrice();
                }
            }
        } else {
            Coupon theBestCoupon = choseTheBestCouponForBasket(products, coupons);
            for (Product product : products) {
                if (product.getCategory().equals(theBestCoupon.getCategory())) {
                    sum += product.getPrice() * (100 - theBestCoupon.getDiscountValueInPercents()) / 100;
                } else {
                    sum += product.getPrice();
                }
            }
        }
        return Math.round(sum * 100.0) / 100.0;
    }

    private Coupon choseTheBestCouponForBasket(List<Product> products, List<Coupon> coupons) {
        double discountBest = 0;
        int bestIndex = 0;
        for (int i = 0; i < coupons.size(); i++) {
            double discountSum = 0;
            for (Product product : products) {
                if (product.getCategory().equals(coupons.get(i).getCategory())) {
                    discountSum += product.getPrice() * coupons.get(i).getDiscountValueInPercents() / 100;
                }
            } 
            if (discountSum > discountBest) {
                discountBest = discountSum;
                bestIndex = i;
            }
        }
        return coupons.get(bestIndex);
    } 
}
