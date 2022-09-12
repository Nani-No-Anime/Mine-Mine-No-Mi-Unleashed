
package xyz.pixelatedw.mineminenomi.api.entities.TraderEntityResource;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Generated("jsonschema2pojo")
public class TraderEntityResource {

    private List<Item> armor = null;
    private List<Item> hands = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public TraderEntityResource() {
    }

    /**
     * 
     * @param lootTable
     * @param armor
     * @param hands
     * @param texture
     */
    public TraderEntityResource(List<Item> armor, List<Item> hands) {
        super();
        this.armor = armor;
        this.hands = hands;
    }

    public List<Item> getArmor() {
        return armor;
    }

    public void setArmor(List<Item> armor) {
        this.armor = armor;
    }

    public TraderEntityResource withArmor(List<Item> armor) {
        this.armor = armor;
        return this;
    }

    public List<Item> getHands() {
        return hands;
    }

    public void setHands(List<Item> hands) {
        this.hands = hands;
    }

    public TraderEntityResource withHands(List<Item> hands) {
        this.hands = hands;
        return this;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public TraderEntityResource withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(TraderEntityResource.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("armor");
        sb.append('=');
        sb.append(((this.armor == null)?"<null>":this.armor));
        sb.append(',');
        sb.append("hands");
        sb.append('=');
        sb.append(((this.hands == null)?"<null>":this.hands));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
