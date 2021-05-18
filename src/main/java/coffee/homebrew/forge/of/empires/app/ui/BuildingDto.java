package coffee.homebrew.forge.of.empires.app.ui;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * enthält die Attribute des BuildingDto-Objekts
 * @author L
 */

public class BuildingDto
{
    @JsonProperty("id")
    private int id;
    @JsonProperty("count")
    private int count;
    @JsonProperty("bonus")
    private int bonus;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public int getBonus()
    {
        return bonus;
    }

    public void setBonus(int bonus)
    {
        this.bonus = bonus;
    }
}