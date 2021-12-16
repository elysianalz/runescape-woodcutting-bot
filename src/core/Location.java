package core;


import org.osbot.rs07.api.map.Area;


class Location
{
    private String key;
    private String value;
    private boolean bank;
    private Area location;
    private Area bankLocation;

    public Location(String key, String value, boolean bank, Area location, Area varrockWest)
    {
        this.key = key;
        this.value = value;
        this.bank = bank;
        this.location = location;
        this.bankLocation = varrockWest;
    }

    @Override
    public String toString()
    {
        return key;
    }

    public String getKey()
    {
        return key;
    }

    public String getValue()
    {
        return value;
    }
    
    public boolean getBank()
    {
    	return bank;
    }
    
    public Area getLocation()
    {
    	return location;
    }
    
    public Area getBankLocation()
    {
    	return bankLocation;
    }
}
