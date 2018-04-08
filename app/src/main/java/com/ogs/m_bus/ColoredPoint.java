package com.ogs.m_bus;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by dell on 05-04-2018.
 */

class ColoredPoint {
    public LatLng coords;
    public int color;

    public ColoredPoint(LatLng coords, int color) {
        this.coords = coords;
        this.color = color;
    }
}
