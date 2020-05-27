package com.example.ochronasuchu;

import android.text.InputFilter;
import android.text.Spanned;
//Filtr do formularzy, dopuszcza dwie cyfry przed przecinikiem i jedną po
public class DecibelInputFilter implements InputFilter {
    private final int maxDigitsBeforeDecimalPoint=2;
    private final int maxDigitsAfterDecimalPoint=1;

    @Override
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {
        StringBuilder builder = new StringBuilder(dest);
        builder.replace(dstart, dend, source
                .subSequence(start, end).toString());
        if (!builder.toString().matches(
                "(0|([1-9]{1})([0-9]{0,"+(maxDigitsBeforeDecimalPoint-1)+"})?)?(\\.[0-9]{0,"+maxDigitsAfterDecimalPoint+"})?"

        )) {
            if(source.length()==0)
                return dest.subSequence(dstart, dend);
            return "";
        }
        return null;
    }
}


