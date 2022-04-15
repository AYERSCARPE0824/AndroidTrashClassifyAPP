package com.car.information;


import com.car.pojo.Lights;
import com.car.result.CarHouseResult;
import com.car.result.DriverResult;
import com.car.result.EnvironmentInfoResult;
import com.car.result.RecResult;
import com.car.result.StationsResult;

public class NecessaryInformation {
    public static String host = "192.168.1.100";
    public static RecResult recResult;
    public static boolean picRecStatus;
    public static boolean wordRecStatus;

    public static StationsResult stationsResult;
    public static boolean stationsStatus;

    public static EnvironmentInfoResult environmentInfoResult;
    public static boolean environmentInfoStatus;

    public static DriverResult driverResult;
    public static boolean driverStatus;

    public static CarHouseResult carHouseResult;
    public static boolean carHouseStatus;


    public static boolean temp_setted = false;
    public static double temp_Max;
    public static double temp_Min;
    public static boolean humidity_setted = false;
    public static int humidity_Max;
    public static int humidity_Min;
    public static boolean co2_setted = false;
    public static int co2_Max;
    public static int co2_Min;

    public static Lights lights = new Lights();
    public static boolean hasSet ;
    public static String result_recognition;
}
