package com.example.ratelimiter;

public class HitCounter
{
    private Long timestampInMillSeconds;
    private Integer size;
    private Integer tokensLeft;
    private Integer extraCredit;

    public HitCounter(Integer tokens)
    {
        this.timestampInMillSeconds = System.currentTimeMillis();
        this.size = tokens;
        this.tokensLeft = this.size;
        this.extraCredit = 0;
    }

    public synchronized boolean hit(long timestamp)
    {
        if(timestamp - timestampInMillSeconds >= Constants.TIME_LIMIT) {
            InitializeAndUpdateCredits();
        }

        if(tokensLeft > 0)
        {
            tokensLeft--;
            return true;
        }
        else if(extraCredit > 0)
        {
            extraCredit--;
            return true;
        }
        return false;
    }

    private void InitializeAndUpdateCredits()
    {
        this.timestampInMillSeconds = System.currentTimeMillis();
        this.extraCredit += this.tokensLeft;
        this.tokensLeft = size;
    }

}
