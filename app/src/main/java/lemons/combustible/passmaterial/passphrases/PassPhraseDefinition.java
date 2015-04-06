package lemons.combustible.passmaterial.passphrases;

import android.view.View;
import android.widget.TextView;

import com.bustiblelemons.logging.Logger;
import com.bustiblelemons.recycler.AbsRecyclerHolder;

import java.util.List;

import butterknife.InjectView;
import butterknife.Optional;
import lemons.combustible.passmaterial.passphrases.generators.wordnik.WordnikGenerator;
import rx.Observer;
import rx.Subscription;

/**
 * Created by hiv on 06.04.15.
 */
public class PassPhraseDefinition extends AbsRecyclerHolder<PassPhrase>
        implements Observer<Word> {

    private static final Logger log = new Logger(PassPhraseDefinition.class);
    @Optional
    @InjectView(android.R.id.title)
    TextView mTitleView;
    @Optional
    @InjectView(android.R.id.primary)
    TextView mDefinitionView;
    private Subscription mSub;

    public PassPhraseDefinition(View view) {
        super(view);
    }

    @Override
    public void onBindData(PassPhrase item, int position) {
        if (item != null) {
            List<Word> words = item.getWords();
            if (words != null) {
                // because we heave a peek through header and a view with the passphrase
                int pos = position - 2;
                if (pos > 0 && pos < words.size() && words.get(pos) != null) {
                    Word word = words.get(pos);
                    if (mTitleView != null) {
                        CharSequence title = word.getWordText();
                        mTitleView.setText(title);
                    }
                    if (!word.hasDefinition()) {
                        mSub = WordnikGenerator.getGenerator().getDefinition(this, word);
                    } else {
                        setDefinition(word.getWordDefinition());
                    }
                }
            }
        }
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(Word word) {
        log.d("onNext %s", word);
        if (word != null) {
            CharSequence definition = word.getWordDefinition();
            setDefinition(definition);
        }
    }

    private void setDefinition(CharSequence definition) {
        if (mDefinitionView != null) {
            mDefinitionView.setText(definition);
        }
    }
}
