package com.bignerdranch.android.mybills;


import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by Brandon on 2/9/2018.
 */

public class MonthViewDatabase {
    private static final String TEST_FLAG = "Month Test is";

    private static MonthViewDatabase sMonthViewDatabase;
    private List<MonthView> mMonthViews;

    public static MonthViewDatabase get(Context context) {
        if (sMonthViewDatabase == null) {
            sMonthViewDatabase = new MonthViewDatabase(context);
        }
        return sMonthViewDatabase;
    }

    private MonthViewDatabase(Context context) {
        mMonthViews = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 12; i++) {
            calendar.set(Calendar.MONTH, i);
            String test = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
            Log.d(TEST_FLAG, test);
            MonthView month = new MonthView(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US));
            mMonthViews.add(month);
        }
    }

    public List<MonthView> getMonthViews() {
        return mMonthViews;
    }

    public MonthView getMonthView(UUID id) {
        for (MonthView monthView : mMonthViews) {
            if (monthView.getId().equals(id)) {
                return monthView;
            }
        }
        return null;
    }

}
