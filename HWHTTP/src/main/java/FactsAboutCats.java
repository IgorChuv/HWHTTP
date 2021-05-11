import com.fasterxml.jackson.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonIgnoreProperties({ "status", "__v","source","type","deleted" })
public class FactsAboutCats {

    private final Date updatedAt;
    private final Date createdAt;
    private final String _id;
    private final String user;
    private final String text;
    private final boolean used;

    public FactsAboutCats(

            @JsonProperty("updatedAt") Date updatedAt,
            @JsonProperty("createdAt") Date createdAt,
            @JsonProperty("_id")String _id,
            @JsonProperty("user")String user,
            @JsonProperty("used") boolean used,
            @JsonProperty("text")String text) {

        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this._id = _id;
        this.user = user;
        this.used = used;
        this.text = text;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String get_id() {
        return _id;
    }

    public String getUser() {
        return user;
    }
    public boolean getUsed() {
        return used;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy 'at 'hh:mm:ss");
        return "Fact about cats:\n" +
                "  Created: " + formatForDateNow.format(createdAt) + "\n" +
                "  Updated: " + formatForDateNow.format(updatedAt) + "\n" +
                "  User id: " + _id + "\n" +
                "  User: " + user + "\n" +
                "  Fact: '" + text + "'\n";
    }
}
