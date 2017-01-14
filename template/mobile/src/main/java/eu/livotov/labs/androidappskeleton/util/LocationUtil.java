package eu.livotov.labs.androidappskeleton.util;

import android.Manifest;
import android.location.Location;
import android.support.annotation.RequiresPermission;

import eu.livotov.labs.android.robotools.location.RTLocation;
import eu.livotov.labs.androidappskeleton.core.App;
import eu.livotov.labs.androidappskeleton.event.location.LocationChangedEvent;
import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.functions.Action1;

/**
 * Created by dlivotov on 17/08/2016.
 */

public class LocationUtil
{
    @RequiresPermission(anyOf = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION})
    public static LocationRequestResult requestLocation()
    {
        if (!App.checkPermissions(Manifest.permission.ACCESS_COARSE_LOCATION))
        {
            return LocationRequestResult.NoPermission;
        }
        else if (!RTLocation.isLocationEnabled(App.getContext()))
        {
            return LocationRequestResult.NoLocationEnabled;
        }
        else if (!RTLocation.areGoogleServicesPresent(App.getContext()))
        {
            return LocationRequestResult.NoGoogleServices;
        }
        else
        {
            final ReactiveLocationProvider provider = new ReactiveLocationProvider(App.getContext());
            provider.getLastKnownLocation().subscribe(new Action1<Location>()
            {
                @Override
                public void call(Location location)
                {
                    App.postEvent(new LocationChangedEvent(location));
                }
            });
            return LocationRequestResult.Enqueued;
        }
    }

    public enum LocationRequestResult
    {
        Enqueued, NoPermission, NoGoogleServices, NoLocationEnabled;
    }
}