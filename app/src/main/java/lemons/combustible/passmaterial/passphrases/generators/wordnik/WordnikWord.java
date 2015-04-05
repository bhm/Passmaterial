package lemons.combustible.passmaterial.passphrases.generators.wordnik;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

import lemons.combustible.passmaterial.passphrases.Word;

/**
 * Created by hiv on 05.04.15.
 */
@JsonPropertyOrder({
        "id",
        "word"
})
public class WordnikWord implements Word {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("word")
    private String  word;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The word
     */
    @JsonProperty("word")
    public String getWord() {
        return word;
    }

    /**
     * @param word The word
     */
    @JsonProperty("word")
    public void setWord(String word) {
        this.word = word;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @Override
    public String toString() {
        return "WordnikWord{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public CharSequence getWordText() {
        return getWord();
    }
}
