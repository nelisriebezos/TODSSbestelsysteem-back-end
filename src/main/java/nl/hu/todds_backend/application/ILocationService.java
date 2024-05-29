package nl.hu.todds_backend.application;

import nl.hu.todds_backend.domain.Location;

import java.util.List;

public interface ILocationService {
    public List<Location> getAllLocations();
    public Location getLocationById(Long id);
    public Location persistLocation(Location location);
    public boolean deleteLocation(Location location);
}
