package com.lyh.service.serviceImpl;

import com.lyh.service.*;
import org.oasisopen.sca.annotation.Reference;
import org.oasisopen.sca.annotation.Service;

@Service(CalculatorService.class)
public class CalculatorServiceImpl implements CalculatorService {

    private AddService addService;

    private SubtractService subtractService;

    private MultiplyService multiplyService;

    private DivideService divideService;


    @Reference

    public void setAddService(AddService addService) {

        this.addService = addService;

    }


    @Reference

    public void setSubtractService(SubtractService subtractService) {

        this.subtractService = subtractService;

    }


    @Reference

    public void setDivideService(DivideService divideService) {

        this.divideService = divideService;

    }


    @Reference

    public void setMultiplyService(MultiplyService multiplyService) {

        this.multiplyService = multiplyService;

    }


    public double add(double n1, double n2) {

        return addService.add(n1, n2);

    }


    public double subtract(double n1, double n2) {

        return subtractService.subtract(n1, n2);

    }


    public double multiply(double n1, double n2) {

        return multiplyService.multiply(n1, n2);

    }


    public double divide(double n1, double n2) {

        return divideService.divide(n1, n2);

    }
}
