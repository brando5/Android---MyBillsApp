package com.bignerdranch.android.mybills;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

/**
 * Created by Brandon on 2/5/2018.
 */

// SET UP RECYCLER VIEW

public class MonthFragment extends Fragment {

    private static final String ARG_MONTH_ID = "month_id";

    private RecyclerView mRecyclerView;
    private MonthView mMonthView;
    private TextView mNameTextView;
    private EnvelopeAdapter mEnvelopeAdapter;

    public static MonthFragment newInstance (UUID uuid) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_MONTH_ID, uuid);

        MonthFragment monthFragment = new MonthFragment();
        monthFragment.setArguments(args);
        return monthFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID id = (UUID)getArguments().getSerializable(ARG_MONTH_ID);
        mMonthView = MonthViewDatabase.get(getActivity()).getMonthView(id);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.month_fragment, container, false);

        mNameTextView = v.findViewById(R.id.month_name);
        mNameTextView.setText(mMonthView.getName());

        mRecyclerView = v.findViewById(R.id.envelope_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        List<Envelope> envelopes = EnvelopeDatabase.get(getActivity()).getEnvelopes();
        if (mEnvelopeAdapter == null) {
            mEnvelopeAdapter = new EnvelopeAdapter(envelopes);
            mRecyclerView.setAdapter(mEnvelopeAdapter);
        }
        else {
            mEnvelopeAdapter.setEnvelopes(envelopes);
            mEnvelopeAdapter.notifyDataSetChanged();
        }
        return v;
    }

    private class EnvelopeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mBalanceTextView;
        private Envelope mEnvelope;
        private ProgressBar mProgressBar;

        public EnvelopeHolder(View itemView) {
            super(itemView);

            mBalanceTextView = itemView.findViewById(R.id.balance);
            mProgressBar = itemView.findViewById(R.id.envelop_progress_bar);

        }

        public void bindEnvelope(Envelope envelope) {
            mEnvelope = envelope;
            mBalanceTextView.setText(String.valueOf(mEnvelope.getBalance()));
            mProgressBar.setMax(10);
            mProgressBar.setProgress(mEnvelope.getBalance());
        }

        @Override
        public void onClick(View v) {
            // For if you click on an envelope, pull up detailed view
        }
    }

    private class EnvelopeAdapter extends RecyclerView.Adapter<EnvelopeHolder> {

        private List<Envelope> mEnvelopes;

        public EnvelopeAdapter(List<Envelope> envelopes) {
            mEnvelopes = envelopes;
        }

        @Override
        public EnvelopeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.item_envelope, parent, false);
            return new EnvelopeHolder(v);
        }

        @Override
        public void onBindViewHolder(EnvelopeHolder holder, int position) {
            Envelope envelope = mEnvelopes.get(position);
            holder.bindEnvelope(envelope);
        }

        @Override
        public int getItemCount() {
            return mEnvelopes.size();
        }

        public void setEnvelopes(List<Envelope> envelopes) {
            mEnvelopes = envelopes;
        }

    }
}
