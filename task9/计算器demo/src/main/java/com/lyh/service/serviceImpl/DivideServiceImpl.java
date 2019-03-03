package com.lyh.service.serviceImpl;

import com.lyh.service.DivideService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DivideServiceImpl implements DivideService {
    public double divide(double n1, double n2) {
        Logger logger = Logger.getLogger("calculator");

        logger.log(Level.FINEST, "Dividing " + n1 + " with " + n2);

        return n1 / n2;
    }
}
