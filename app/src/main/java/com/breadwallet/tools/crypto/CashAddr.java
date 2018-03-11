package com.breadwallet.tools.crypto;

import android.support.annotation.NonNull;
import android.util.Log;

import com.breadwallet.BuildConfig;

public class CashAddr {

    private static final String CHARSET = "qpzry9x8gf2tvdw0s3jn54khce6mua7l";

    static {
        System.loadLibrary("core");
    }

    private String value;

    private CashAddr(String cashAddr) {
        this.value = cashAddr;
    }

    public static CashAddr fromLegacy(String legacyAddr) {
        return legacyAddr == null ? null : new CashAddr(BRCashAddrEncode(legacyAddr));
    }

    public static CashAddr parse(String cashAddr) {
        if (!(cashAddr.equals(cashAddr.toLowerCase()) || cashAddr.equals(cashAddr.toUpperCase()))) {
            throw new IllegalArgumentException("cannot_mix_case");
        }

        final String[] parts = cashAddr.split(":");
        if (parts.length != 2 || !parts[0].equals(BuildConfig.CASHADDR_PREFIX)) {
            throw new IllegalArgumentException("invalid_prefix");
        }

        byte[] buffer = cashAddr.getBytes();
        for (byte b : buffer) {
            if (b < 0x21 || b > 0x7e) {
                throw new IllegalArgumentException("illegal_char");
            }
        }

        if (!BRCashAddrValidate(cashAddr)) {
            throw new IllegalArgumentException("wrong_checksum");
        }

        return new CashAddr(cashAddr);
    }

    public static boolean validate(String cashAddr) {
        return BRCashAddrValidate(cashAddr);
    }

    private static native String BRCashAddrDecode(String cashAddr);

    private static native String BRCashAddrEncode(String legacyAddr);

    private static native boolean BRCashAddrValidate(String cashAddr);

    public String toLegacy() {
        return BRCashAddrDecode(this.value);
    }

    @Override
    public String toString() {
        return value;
    }

    public String toUnprefixedString() {
        return value.replace(BuildConfig.CASHADDR_PREFIX + ":", "");
    }
}
