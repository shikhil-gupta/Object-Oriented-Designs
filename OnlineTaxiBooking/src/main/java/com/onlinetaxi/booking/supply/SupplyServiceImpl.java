package com.onlinetaxi.booking.supply;


/**
 * Supply service should keep the locations of Drivers in the memory only.
 * Based on sharding logic only few services of supply shpuld have the data of particular drivers.
 * All the Driver Partner Client should keep on emiting their GPS location event and that event might be
 * pushing in Kafka Queue and concumer keep on pushing driver location to suuply services.
 */
public class SupplyServiceImpl implements ISupplyService {
}
