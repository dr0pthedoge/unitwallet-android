//
// Created by Bartłomiej on 27.02.2018.
//

#ifndef UNITWALLET_ANDROID_CASHADDR_H
#define UNITWALLET_ANDROID_CASHADDR_H

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT jstring JNICALL
Java_com_breadwallet_tools_crypto_CashAddr_BRCashAddrEncode(
        JNIEnv *env,
        jclass type,
        jstring legacyAddr_);

JNIEXPORT jstring JNICALL
Java_com_breadwallet_tools_crypto_CashAddr_BRCashAddrDecode(
        JNIEnv *env,
        jclass type,
        jstring cashAddr_);

JNIEXPORT jboolean JNICALL
Java_com_breadwallet_tools_crypto_CashAddr_BRCashAddrValidate(
        JNIEnv *env,
        jclass type,
        jstring cashAddr_);

#ifdef __cplusplus
}
#endif

#endif //UNITWALLET_ANDROID_CASHADDR_H
