package Assesment_Secure.demo.services;

import org.springframework.stereotype.Service;

@Service
public class FileProcessingService {
    public String transform(String input) {
        StringBuilder result = new StringBuilder();
        char[] chars = input.toCharArray();

        int i = 0;
        while (i < chars.length) {
            char current = Character.toLowerCase(chars[i]); //Lowering the Char cause application MUST be case-insensitive.
            if (current == 'a') {
                int count = 1;
                int j = i + 1;
                // Count all consecutive 'a's (case-insensitive)
                while (j < chars.length && Character.toLowerCase(chars[j]) == 'a') {
                    count++;
                    j++;
                }

                if (count == 1) {
                    // Rule 1: Single 'a' becomes '#'
                    result.append('#');
                    i++;
                } else {
                    // Rule 2: Keep the first 'a' as 'a', rest as '$'
                    result.append('a'); // keep the first 'a'
                    for (int k = 1; k < count; k++) {
                        result.append('$');
                    }
                    i += count;
                }
            } else {
                result.append(chars[i]);
                i++;
            }
        }

        return result.toString();
    }
}
