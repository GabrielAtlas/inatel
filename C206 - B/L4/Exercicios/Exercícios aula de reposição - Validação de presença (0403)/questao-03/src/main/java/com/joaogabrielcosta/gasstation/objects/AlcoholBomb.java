package com.joaogabrielcosta.gasstation.objects;

public class AlcoholBomb implements FuelBomb {

    @Override
    public double calculatePrice(int liters) {
        double defaultPrice = 4.90 * liters;
        if(liters <= 20) return (defaultPrice - (defaultPrice * 0.03));
        return defaultPrice - (defaultPrice * 0.05);
    }

}
