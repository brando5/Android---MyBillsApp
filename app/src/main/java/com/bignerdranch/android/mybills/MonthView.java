package com.bignerdranch.android.mybills;

import android.widget.ProgressBar;

import java.util.List;
import java.util.UUID;

/**
 * Created by Brandon on 2/5/2018.
 */

public class MonthView {

    private UUID mId;
    private String mName;
    private ProgressBar mProgressBar;
    private List<Envelope> mEnvelopes;


    public MonthView(String name) {
        mId = UUID.randomUUID();
        mName = name;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
