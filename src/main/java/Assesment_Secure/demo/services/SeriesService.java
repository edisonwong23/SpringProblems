package Assesment_Secure.demo.services;

import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class SeriesService {

    public BigInteger computeNthTerm(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n must be at least 1");
        }

        // BigInteger.ONE is a constant in Java's BigInteger class, representing the value 1.
        //i = 2 → even → term = 1 × 2 = 2
        //i = 3 → odd  → term = 2 + 3 = 5
        //i = 4 → even → term = 5 × 4 = 20
        //i = 5 → odd  → term = 20 + 5 = 25
        BigInteger term = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            term = (i % 2 == 0)
                    ? term.multiply(BigInteger.valueOf(i)) //Multiplies two BigIntegers when Even
                    : term.add(BigInteger.valueOf(i)); //Adds two BigIntegers when Odd
        }
        return term;
    }

}
