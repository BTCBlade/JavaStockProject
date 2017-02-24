package data;

/**
  Created by klongrich on 2/23/17.
 **/

public class livedata
{
    static getcurrentprice price;

    public static void main(String [] args)
    {
        float p;

        price = new getcurrentprice("SPY");
        p = price.currentprice();
        price.googleSearch("Kyle Longrich");

        System.out.println(p);
    }

}
