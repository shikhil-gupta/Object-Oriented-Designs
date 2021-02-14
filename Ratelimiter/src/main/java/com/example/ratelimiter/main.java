package com.example.ratelimiter;

public class main
{
    public static void main(String[] args) {

        try {
            RateLimiter rateLimiter = new RateLimiter();

            System.out.println(rateLimiter.isAllow("1"));
            System.out.println(rateLimiter.isAllow("1"));
            Thread.sleep(1000);
            System.out.println(rateLimiter.isAllow("1"));
            System.out.println(rateLimiter.isAllow("1"));
            System.out.println(rateLimiter.isAllow("1"));
            System.out.println(rateLimiter.isAllow("1"));
            System.out.println(rateLimiter.isAllow("1"));
            System.out.println(rateLimiter.isAllow("1"));
        }
        catch (Exception ex)
        {
            System.out.println();
        }
    }
}
