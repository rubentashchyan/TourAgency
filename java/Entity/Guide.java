package Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Guide {
    @JsonProperty("id")
    private String guideID;
    private String name;
    private String language;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guide guide)) return false;
        return Objects.equals(getGuideID(), guide.getGuideID()) && Objects.equals(getName(), guide.getName()) && Objects.equals(getLanguage(), guide.getLanguage());
    }

    @Override
    public String toString() {
        return "Guide{" +
                "guideID='" + guideID + '\'' +
                ", name='" + name + '\'' +
                ", language='" + language + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGuideID(), getName(), getLanguage());
    }
}
