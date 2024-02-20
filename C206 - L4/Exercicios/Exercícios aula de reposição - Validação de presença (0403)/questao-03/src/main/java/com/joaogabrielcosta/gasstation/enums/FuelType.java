package com.joaogabrielcosta.gasstation.enums;

import com.joaogabrielcosta.gasstation.objects.AlcoholBomb;
import com.joaogabrielcosta.gasstation.objects.FuelBomb;
import com.joaogabrielcosta.gasstation.objects.GasolineBomb;

public enum FuelType {

    GASOLINE('G', new GasolineBomb()),
    ALCOHOL('A', new AlcoholBomb());

    private final char identifier;
    private final FuelBomb fuelBomb;

    FuelType(char identifier, FuelBomb fuelBomb) {
        this.identifier = identifier;
        this.fuelBomb = fuelBomb;
    }

    public FuelBomb getFuelBomb() {
        return fuelBomb;
    }

    public char getIdentifier() {
        return identifier;
    }


}
