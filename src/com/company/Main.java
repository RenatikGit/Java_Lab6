package com.company;
import java.io.PrintStream;
import java.nio.channels.InterruptedByTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static class AnyDimensions{
        Integer width;
        Integer height;
        Integer length;
        public AnyDimensions(Integer width, Integer height, Integer length) {
            this.width = width;
            this.height = height;
            this.length = length;
        }
    }

    public static class Transport {
        String model;
        String color;
        Integer power;
        AnyDimensions dimensions;

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Integer getPower() {
            return power;
        }

        public void setPower(Integer power) {
            this.power = power;
        }

        public AnyDimensions getDimensions() {
            return dimensions;
        }

        public void setDimensions(Integer width, Integer height, Integer length) {
            this.dimensions = new AnyDimensions(width, height, length);
        }

        public void getDescription()
        {
            System.out.printf("Это класс : %s, модель: %s %n", this.getClass().getName().split("\\$")[1], getModel());
            System.out.printf("Мощность = %d, цвет = %s%n", getPower(), getColor());
            System.out.println("Габариты :");
            System.out.printf("\tДлина  = %d%n", dimensions.length);
            System.out.printf("\tВысота = %d%n", dimensions.height);
            System.out.printf("\tШирина = %d%n%n", dimensions.width);
        }
    }


    public static abstract class LandTransport extends Transport
    {
        Integer numberOfDoors;

        public Integer getNumberOfDoors() {
            return numberOfDoors;
        }

        public void setNumberOfDoors(Integer numberOfDoors) {
            this.numberOfDoors = numberOfDoors;
        }

        @Override
        public void getDescription()
        {
            System.out.printf("Это класс : %s, модель: %s %n", "'Сухопутный транспорт'", getModel());
            System.out.printf("Мощность = %d, цвет = %s%n", getPower(), getColor());
            System.out.println("Габариты :");
            System.out.printf("\tДлина  = %d%n", dimensions.length);
            System.out.printf("\tВысота = %d%n", dimensions.height);
            System.out.printf("\tШирина = %d%n", dimensions.width);
            System.out.printf("Дверей = %d%n%n", getNumberOfDoors());
        }

        public abstract void go();
    }


    public static class SportsCar extends LandTransport
    {
        @Override
        public void go()
        {
            System.out.println("Dust flies from under the wheels");
        }
    }

    public static class ElectricCar extends  LandTransport
    {
        @Override
        public void go()
        {
            System.out.println("It's easier to breathe in the city");
        }
    }


    public static abstract class SeaTransport extends Transport
    {
        Integer liftingCapacity;

        public Integer getLiftingCapacity() {
            return liftingCapacity;
        }

        public void setLiftingCapacity(Integer liftingCapacity) {
            this.liftingCapacity = liftingCapacity;
        }

        @Override
        public void getDescription()
        {
            System.out.printf("Это класс : %s, модель: %s %n", "'Морской транспорт'", getModel());
            System.out.printf("Мощность = %d, цвет = %s%n", getPower(), getColor());
            System.out.println("Габариты :");
            System.out.printf("\tДлина  = %d%n", dimensions.length);
            System.out.printf("\tВысота = %d%n", dimensions.height);
            System.out.printf("\tШирина = %d%n", dimensions.width);
            System.out.printf("Грузоподъемность = %d%n%n", getLiftingCapacity());
        }

        public abstract void go();
    }


    public static class SportSeaTransport extends SeaTransport
    {
        @Override
        public void go()
        {
            System.out.println("Like flying on the waves");
        }
    }

    public static class ElectricSeaTransport extends SeaTransport
    {
        @Override
        public void go()
        {
            System.out.println("You saved a lot of fish lives");
        }
    }

    public static abstract class AbstractFabric
    {
        public abstract SeaTransport createYacht();
        public abstract LandTransport createCar();

    }

    public static class EcoFabric extends AbstractFabric
    {
        @Override
        public SeaTransport createYacht()
        {
            SeaTransport yacht = new ElectricSeaTransport();
            yacht.setModel("E Princess 59");
            yacht.setColor("Blue");
            yacht.setDimensions(3,2,5);
            yacht.setPower(1000);
            return  yacht;
        }
        @Override
        public LandTransport createCar()
        {
            LandTransport car = new ElectricCar();
            car.setModel("Tesla");
            car.setColor("Red");
            car.setDimensions(2,1,4);
            car.setPower(800);
            return  car;
        }

    }


    public static class SportFabric extends AbstractFabric
    {
        @Override
        public SeaTransport createYacht()
        {
            SeaTransport yacht = new SportSeaTransport();
            yacht.setModel("Sport Princess 59");
            yacht.setColor("Blue");
            yacht.setDimensions(3,2,5);
            yacht.setPower(9000);
            return  yacht;
        }
        @Override
        public LandTransport createCar()
        {
            LandTransport car = new SportsCar();
            car.setModel("Tesla");
            car.setColor("Red");
            car.setDimensions(2,1,4);
            car.setPower(800);
            return  car;
        }

    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random random = new Random();
        AbstractFabric fabric;
        System.out.println("Вы за эко или за спорт?");
        System.out.println("[1/0]");
        int N = in.nextInt();
        if (N == 1)
            fabric = new EcoFabric();
        else
            fabric = new SportFabric();
        LandTransport car = fabric.createCar();
        SeaTransport yacht = fabric.createYacht();
        car.go();
        yacht.go();
    }
}
