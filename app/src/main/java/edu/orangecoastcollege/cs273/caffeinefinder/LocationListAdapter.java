package edu.orangecoastcollege.cs273.caffeinefinder;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to provide custom adapter for the <code>CaffeineLocation</code> list.
 */
public class LocationListAdapter extends ArrayAdapter<CaffeineLocation> {

    private Context mContext;
    private List<CaffeineLocation> mLocationsList = new ArrayList<>();
    private int mResourceId;



    /**
     * Creates a new <code>LocationsListAdapter</code> given a mContext, resource id and list of caffeineLocations.
     *
     * @param c The mContext for which the adapter is being used (typically an activity)
     * @param rId The resource id (typically the layout file name)
     * @param caffeineLocations The list of caffeineLocations to display
     */
    public LocationListAdapter(Context c, int rId, List<CaffeineLocation> caffeineLocations) {
        super(c, rId, caffeineLocations);
        mContext = c;
        mResourceId = rId;
        mLocationsList = caffeineLocations;
    }

    /**
     * Gets the view associated with the layout.
     * @param pos The position of the CaffeineLocation selected in the list.
     * @param convertView The converted view.
     * @param parent The parent - ArrayAdapter
     * @return The new view with all content set.
     */
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        final CaffeineLocation selectedCaffeineLocation = mLocationsList.get(pos);


        LayoutInflater inflater =
                (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        LinearLayout offeringListLinearLayout =
                (LinearLayout) view.findViewById(R.id.locationListLinearLayout);

        TextView locationListNameTextView =
                (TextView) view.findViewById(R.id.locationListNameTextView);
        TextView locationListAddressTextView =
                (TextView) view.findViewById(R.id.locationListAddressTextView);
        TextView locationListPhoneTextView =
                (TextView) view.findViewById(R.id.locationListPhoneTextView);

        offeringListLinearLayout.setTag(selectedCaffeineLocation);

        locationListNameTextView.setText(selectedCaffeineLocation.getName());
        locationListAddressTextView.setText(selectedCaffeineLocation.getFullAddress());
        locationListPhoneTextView.setText(selectedCaffeineLocation.getPhone());

        return view;
    }
}
