package com.breadwallet.tools;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.breadwallet.BuildConfig;
import com.breadwallet.presenter.activities.BreadActivity;
import com.breadwallet.tools.crypto.CashAddr;
import com.breadwallet.wallet.BRWalletManager;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)

public class CashAddrTests {

    @Test
    public void testLegacyP2PKHAddressConversion(){
        final String legacy = "12PRZjrLo5yqnHMmUCtPUse4kCyuneby3S";
        final String cash = BuildConfig.CASHADDR_PREFIX + ":qq8ntjzxt2zqpvxqhra53uqttg3rf9m50y6kers5hq";

        CashAddr result = CashAddr.fromLegacy(legacy);
        assertThat(result.toString(), is(equalTo(cash)));
        assertThat(result.toLegacy(), is(equalTo(legacy)));
    }

    @Test
    public void testLegacyP2SHAddressConversion(){
        final String legacy = "3ECKq7onkjnRQR2nNe5uUJp2yMsXRmZavC";
        final String cash = BuildConfig.CASHADDR_PREFIX + ":pzyjepg4rmm8yx8v0sc6svac255ta2md2y9l0edptq";

        CashAddr result = CashAddr.fromLegacy(legacy);
        assertThat(result.toString(), is(equalTo(cash)));
        assertThat(result.toLegacy(), is(equalTo(legacy)));
    }

    @Test
    public void testInvalidCashAddr(){
        final String invalid = BuildConfig.CASHADDR_PREFIX + ":pzyjepg5rmm8yx8v0sc6svac255ta2md2y9l0edptq";
        assertThat(CashAddr.validate(invalid), is(false));
    }
}
