package data;

import java.io.*;
import java.net.*;

/**
  Created by klongrich on 2/23/17.
 **/

public class livedata
{
    static getcurrentprice price;

    public static void main(String [] args) throws IOException
    {
        float p;

        price = new getcurrentprice();
        p = price.currentprice();
        
        System.out.println(p);
    }

}
