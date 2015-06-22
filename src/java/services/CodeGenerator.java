/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 6:51:32 PM  : Jun 18, 2015
 */

package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *Generate random 5 digit code  for each booking.
 * Use as booking code in payments.
 * @author kelli
 */
public class CodeGenerator {
    
    private List<Integer> numbers = new ArrayList();
    private int x=0;
    public  CodeGenerator(){
        for (int i = 1000; i < 10000; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
    }
    
    public int getNextInt(){
        return numbers.get(x++);
    }
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("Code: "+new CodeGenerator().getNextInt());
        }
    }
}
