package com.Unitest.Unitest.sercive;

import com.Unitest.Unitest.repository.CalculateRepository;
public class CalculateService implements CalculateRepository {

    @Override
    public int sum(int a, int b) {
        return (a+b);
    }

    @Override
    public int multiple(int a, int b) {
        return (a*b);
    }

    @Override
    public Float device(int a, int b) {
        if(b == 0) return null;
        return (((float)a)/b);
    }
}
