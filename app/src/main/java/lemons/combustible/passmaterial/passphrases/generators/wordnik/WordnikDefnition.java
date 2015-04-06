package lemons.combustible.passmaterial.passphrases.generators.wordnik;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import lemons.combustible.passmaterial.passphrases.Word;

/**
 * Created by hiv on 06.04.15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "textProns",
        "sourceDictionary",
        "exampleUses",
        "relatedWords",
        "labels",
        "citations",
        "word",
        "partOfSpeech",
        "sequence",
        "text",
        "score",
        "attributionText"
})
public class WordnikDefnition implements Word {

    @JsonProperty("textProns")
    private List<Object> textProns = new ArrayList<Object>();
    @JsonProperty("sourceDictionary")
    private String sourceDictionary;
    @JsonProperty("exampleUses")
    private List<Object> exampleUses  = new ArrayList<Object>();
    @JsonProperty("relatedWords")
    private List<Object> relatedWords = new ArrayList<Object>();
    @JsonProperty("labels")
    private List<Object> labels       = new ArrayList<Object>();
    @JsonProperty("citations")
    private List<Object> citations    = new ArrayList<Object>();
    @JsonProperty("word")
    private String  word;
    @JsonProperty("partOfSpeech")
    private String  partOfSpeech;
    @JsonProperty("sequence")
    private String  sequence;
    @JsonProperty("text")
    private String  text;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("attributionText")
    private String  attributionText;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The textProns
     */
    @JsonProperty("textProns")
    public List<Object> getTextProns() {
        return textProns;
    }

    /**
     * @param textProns The textProns
     */
    @JsonProperty("textProns")
    public void setTextProns(List<Object> textProns) {
        this.textProns = textProns;
    }

    /**
     * @return The sourceDictionary
     */
    @JsonProperty("sourceDictionary")
    public String getSourceDictionary() {
        return sourceDictionary;
    }

    /**
     * @param sourceDictionary The sourceDictionary
     */
    @JsonProperty("sourceDictionary")
    public void setSourceDictionary(String sourceDictionary) {
        this.sourceDictionary = sourceDictionary;
    }

    /**
     * @return The exampleUses
     */
    @JsonProperty("exampleUses")
    public List<Object> getExampleUses() {
        return exampleUses;
    }

    /**
     * @param exampleUses The exampleUses
     */
    @JsonProperty("exampleUses")
    public void setExampleUses(List<Object> exampleUses) {
        this.exampleUses = exampleUses;
    }

    /**
     * @return The relatedWords
     */
    @JsonProperty("relatedWords")
    public List<Object> getRelatedWords() {
        return relatedWords;
    }

    /**
     * @param relatedWords The relatedWords
     */
    @JsonProperty("relatedWords")
    public void setRelatedWords(List<Object> relatedWords) {
        this.relatedWords = relatedWords;
    }

    /**
     * @return The labels
     */
    @JsonProperty("labels")
    public List<Object> getLabels() {
        return labels;
    }

    /**
     * @param labels The labels
     */
    @JsonProperty("labels")
    public void setLabels(List<Object> labels) {
        this.labels = labels;
    }

    /**
     * @return The citations
     */
    @JsonProperty("citations")
    public List<Object> getCitations() {
        return citations;
    }

    /**
     * @param citations The citations
     */
    @JsonProperty("citations")
    public void setCitations(List<Object> citations) {
        this.citations = citations;
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

    /**
     * @return The partOfSpeech
     */
    @JsonProperty("partOfSpeech")
    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    /**
     * @param partOfSpeech The partOfSpeech
     */
    @JsonProperty("partOfSpeech")
    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    /**
     * @return The sequence
     */
    @JsonProperty("sequence")
    public String getSequence() {
        return sequence;
    }

    /**
     * @param sequence The sequence
     */
    @JsonProperty("sequence")
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    /**
     * @return The text
     */
    @JsonProperty("text")
    public String getText() {
        return text;
    }

    /**
     * @param text The text
     */
    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return The score
     */
    @JsonProperty("score")
    public Integer getScore() {
        return score;
    }

    /**
     * @param score The score
     */
    @JsonProperty("score")
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * @return The attributionText
     */
    @JsonProperty("attributionText")
    public String getAttributionText() {
        return attributionText;
    }

    /**
     * @param attributionText The attributionText
     */
    @JsonProperty("attributionText")
    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public CharSequence getWordText() {
        return getWord();
    }

    @Override
    public CharSequence getWordDefinition() {
        return getText();
    }

    @Override
    public void setWordDefinition(CharSequence sequence) {
        if (sequence != null) {
            setWord(sequence.toString());
        }
    }

    @Override
    public boolean hasDefinition() {
        return getWordDefinition() != null;
    }
}
