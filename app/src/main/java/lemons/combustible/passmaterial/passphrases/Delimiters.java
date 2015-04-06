package lemons.combustible.passmaterial.passphrases;

import android.text.TextUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by hiv on 06.04.15.
 */
public class Delimiters implements Serializable {
    private static final List<String> BUILT_IN_DELIMITERS = new ArrayList<String>();
    public static Delimiters DEFAULT;

    static {
        BUILT_IN_DELIMITERS.add("/");
        BUILT_IN_DELIMITERS.add("\\");
        BUILT_IN_DELIMITERS.add("|");
        BUILT_IN_DELIMITERS.add("~");
        BUILT_IN_DELIMITERS.add("`");
        BUILT_IN_DELIMITERS.add(":");
        BUILT_IN_DELIMITERS.add(";");
        BUILT_IN_DELIMITERS.add("\'");
        BUILT_IN_DELIMITERS.add("\"");
        BUILT_IN_DELIMITERS.add(",");
        BUILT_IN_DELIMITERS.add(".");
        BUILT_IN_DELIMITERS.add("[");
        BUILT_IN_DELIMITERS.add("]");
        BUILT_IN_DELIMITERS.add("(");
        BUILT_IN_DELIMITERS.add(")");
        BUILT_IN_DELIMITERS.add("!");
        BUILT_IN_DELIMITERS.add("@");
        BUILT_IN_DELIMITERS.add("#");
        BUILT_IN_DELIMITERS.add("$");
        BUILT_IN_DELIMITERS.add("$");
        BUILT_IN_DELIMITERS.add("%");
        BUILT_IN_DELIMITERS.add("^");
        BUILT_IN_DELIMITERS.add("&");
        BUILT_IN_DELIMITERS.add("[");
        BUILT_IN_DELIMITERS.add("[");
        BUILT_IN_DELIMITERS.add("-");
        BUILT_IN_DELIMITERS.add("_");
        BUILT_IN_DELIMITERS.add("+");
        BUILT_IN_DELIMITERS.add("=");
        Map<String, Boolean> delimiters = new HashMap<String, Boolean>();
        for (String del : BUILT_IN_DELIMITERS) {
            delimiters.put(del, true);
        }
        DEFAULT = new Delimiters().withDelimiters(delimiters);
    }

    @JsonProperty("delimiters")
    private Map<String, Boolean> mDelimiters;

    public static final Random getRandom() {
        return LazyRandomHolder.INSTANCE;
    }

    @JsonIgnore
    private Delimiters withDelimiters(Map<String, Boolean> delimiters) {
        mDelimiters = delimiters;
        return this;
    }

    @JsonProperty("delimiters")
    public Map<String, Boolean> getDelimiters() {
        return mDelimiters;
    }

    @JsonProperty("delimiters")
    public void setDelimiters(Map<String, Boolean> delimiters) {
        mDelimiters = delimiters;
    }

    public String getRandomDelimiter() {
        List<String> allowed = new ArrayList<String>();
        for (Map.Entry<String, Boolean> entry : getDelimiters().entrySet()) {
            if (entry != null && !TextUtils.isEmpty(entry.getKey())) {
                if (entry.getValue()) {
                    String k = entry.getKey();
                    allowed.add(k);
                }
            }
        }
        int max = allowed.size();
        int rand = getRandom().nextInt(max);
        String d = "/";
        if (rand > 0) {
            d = allowed.get(rand);
        }
        return d;
    }

    private static final class LazyRandomHolder {
        private static final Random INSTANCE = new Random();
    }
}
