/*
 * Copyright (C) 2015 Matthew Lee
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.mthli.knife;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.QuoteSpan;

public class KnifeQuoteSpan extends QuoteSpan {
    private static final int DEFAULT_STRIPE_WIDTH = 2;
    private static final int DEFAULT_GAP_WIDTH = 2;
    private static final int DEFAULT_COLOR = 0xff0000ff;

    private int quoteColor = DEFAULT_COLOR;
    private int quoteStripeWidth = DEFAULT_STRIPE_WIDTH;
    private int quoteGapWidth = DEFAULT_GAP_WIDTH;

    public KnifeQuoteSpan(int quoteColor, int quoteStripeWidth, int quoteGapWidth) {
        if (quoteColor != 0) {
            this.quoteColor = quoteColor;
        }

        if (quoteStripeWidth != 0) {
            this.quoteStripeWidth = quoteStripeWidth;
        }

        if (quoteGapWidth != 0) {
            this.quoteGapWidth = quoteGapWidth;
        }
    }

    @Override
    public int getLeadingMargin(boolean first) {
        return quoteStripeWidth + quoteGapWidth;
    }

    @Override
    public void drawLeadingMargin(Canvas c, Paint p, int x, int dir,
                                  int top, int baseline, int bottom,
                                  CharSequence text, int start, int end,
                                  boolean first, Layout layout) {
        Paint.Style style = p.getStyle();
        int color = p.getColor();

        p.setStyle(Paint.Style.FILL);
        p.setColor(quoteColor);
        c.drawRect(x, top, x + dir * quoteGapWidth, bottom, p);

        p.setStyle(style);
        p.setColor(color);
    }
}
