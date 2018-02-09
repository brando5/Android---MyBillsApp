package com.bignerdranch.android.mybills;

import java.util.UUID;

/**
 * Created by Brandon on 2/5/2018.
 */

public class Envelope {

    private UUID mId;
    private int mBalance;

    public Envelope(int balance) {
        mId = UUID.randomUUID();
        mBalance = balance;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public int getBalance() {
        return mBalance;
    }

    public void setBalance(int balance) {
        mBalance = balance;
    }
}
