package com.joaogabrielcosta.gasstation.objects;

public class GasolineBomb implements FuelBomb {

    @Override
    public double calculatePrice(int liters) {
        double defaultPrice = 6.00 * liters;
        if(liters <= 20) return (defaultPrice - (defaultPrice * 0.04));
        return defaultPrice - (defaultPrice * 0.06);
    }

}
