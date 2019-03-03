package com.lyh.service.serviceImpl;

import com.lyh.service.MultiplyService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MultiplyServiceImpl implements MultiplyService {
    public double multiply(double n1, double n2) {
        Logger logger = Logger.getLogger("calculator");

        logger.log(Level.FINEST, "Multiplying " + n1 + " with " + n2);

        return n1 * n2;
    }
}
