package com.breadwallet.presenter.entities;


import com.breadwallet.tools.crypto.CashAddr;
import com.breadwallet.tools.util.Fun;
import com.breadwallet.tools.util.Utils;

import static com.breadwallet.tools.util.Fun.map;

/**
 * BreadWallet
 * <p>
 * Created by Mihail Gutan <mihail@breadwallet.com> on 1/13/16.
 * Copyright (c) 2016 breadwallet LLC
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

public class TxItem {
    public static final String TAG = TxItem.class.getName();
    private long timeStamp;
    private int blockHeight;
    private byte[] txHash;
    private long sent;
    private long received;
    private long fee;
    private CashAddr to[];
    private CashAddr from[];
    private long balanceAfterTx;
    private long outAmounts[];
    private boolean isValid;
    private int txSize;

    private TxItem() {
    }

    public TxItem(long timeStamp, int blockHeight, byte[] hash, long sent,
                  long received, long fee, String to[], String from[],
                  long balanceAfterTx, int txSize, long[] outAmounts, boolean isValid) {
        this.timeStamp = timeStamp;
        this.blockHeight = blockHeight;
        this.txHash = hash;
        this.sent = sent;
        this.received = received;
        this.fee = fee;
        this.to = new CashAddr[to.length];
        map(to, new Fun.Map<String, CashAddr>() {
            @Override
            public CashAddr map(String value) {
                return CashAddr.fromLegacy(value);
            }
        }).toArray(this.to);
        this.from = new CashAddr[from.length];
        map(from, new Fun.Map<String, CashAddr>() {
            @Override
            public CashAddr map(String value) {
                return CashAddr.fromLegacy(value);
            }
        }).toArray(this.from);
        this.balanceAfterTx = balanceAfterTx;
        this.outAmounts = outAmounts;
        this.isValid = isValid;
        this.txSize = txSize;
    }

    public static String getTAG() {
        return TAG;
    }

    public int getBlockHeight() {
        return blockHeight;
    }

    public long getFee() {
        return fee;
    }

    public int getTxSize() {
        return txSize;
    }

    public CashAddr[] getFrom() {
        return from;
    }

    public byte[] getTxHash() {
        return txHash;
    }

    public String getTxHashHexReversed() {
        return Utils.reverseHex(Utils.bytesToHex(txHash));
    }

    public long getReceived() {
        return received;
    }

    public long getSent() {
        return sent;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public CashAddr[] getTo() {
        return to;
    }

    public long getBalanceAfterTx() {
        return balanceAfterTx;
    }

    public long[] getOutAmounts() {
        return outAmounts;
    }

    public boolean isValid() {
        return isValid;
    }
}
