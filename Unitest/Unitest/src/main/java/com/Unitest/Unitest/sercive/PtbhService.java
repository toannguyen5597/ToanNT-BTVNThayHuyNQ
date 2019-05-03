package com.Unitest.Unitest.sercive;

import com.Unitest.Unitest.repository.PtbhRepository;

public class PtbhService implements PtbhRepository {
    @Override
    public Float[] ptbh(int a, int b, int c) {
        //Float result[] = new Float[2];
        if(a == 0 && b == 0) return null;
        else if(a == 0){
            Float result[] = new Float[1];
            result[0]= (float)(-c/b);
            return result;
        }
        else{
            Float delta = (float)((b*b)-(4*a*c));
            if(delta < 0) return null;
            if(delta == 0) {
                Float result[] = new Float[1];
                result[0] = (float)(-b/(2*a));
                return result;
            }
            if(delta > 0){
                Float result[] = new Float[2];
                result[0] = (float)((-b-Math.sqrt(delta))/(2*a));
                result[1] = (float)((-b+Math.sqrt(delta))/(2*a));
                return result;
            }
        }
        return null;
    }
}
