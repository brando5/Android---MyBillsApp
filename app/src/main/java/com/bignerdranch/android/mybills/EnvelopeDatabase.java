package com.bignerdranch.android.mybills;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Brandon on 2/9/2018.
 */

public class EnvelopeDatabase {
    private static final String TEST_FLAG = "Envelope Test is";

    private static EnvelopeDatabase sEnvelopeDatabase;
    private List<Envelope> mEnvelopes;

    public static EnvelopeDatabase get(Context context) {
        if (sEnvelopeDatabase == null) {
            sEnvelopeDatabase = new EnvelopeDatabase(context);
        }
        return sEnvelopeDatabase;
    }

    private EnvelopeDatabase(Context context) {
        mEnvelopes = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            Envelope envelope = new Envelope(i);
            mEnvelopes.add(envelope);
        }
    }

    public List<Envelope> getEnvelopes() {
        return mEnvelopes;
    }

    public Envelope getEnvelope(UUID id) {
        for (Envelope envelope : mEnvelopes) {
            if (envelope.getId().equals(id)) {
                return envelope;
            }
        }
        return null;
    }
}
