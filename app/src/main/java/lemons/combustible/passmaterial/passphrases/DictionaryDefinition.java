package lemons.combustible.passmaterial.passphrases;

/**
 * Created by hiv on 05.04.15.
 */
public class DictionaryDefinition {

    private String       mName;
    private CharSequence mDefinition;

    @Override
    public String toString() {
        return "DictionaryDefinition{" +
                "mName='" + mName + '\'' +
                ", mDefinition=" + mDefinition +
                '}';
    }

    public String getName() {
        return mName;
    }

    public DictionaryDefinition withName(String arg) {
        mName = arg;
        return this;
    }

    public CharSequence getDefinition() {
        return mDefinition;
    }

    public DictionaryDefinition withDefinition(CharSequence arg) {
        mDefinition = arg;
        return this;
    }

}
