//
// Created by Bartłomiej on 27.02.2018.
//

#include <jni.h>
#include <stdint.h>
#include <BRCashAddr.h>
#include "CashAddr.h"

JNIEXPORT jstring JNICALL Java_com_breadwallet_tools_crypto_CashAddr_BRCashAddrEncode(
        JNIEnv *env,
        jclass type,
        jstring legacyAddr_) {
    const char *legacyAddr = (*env)->GetStringUTFChars(env, legacyAddr_, 0);

    char cashAddr[55];
    const size_t size = BRCashAddrEncode(cashAddr, legacyAddr);

    (*env)->ReleaseStringUTFChars(env, legacyAddr_, legacyAddr);

    if (size == 0) return NULL;
    return (*env)->NewStringUTF(env, cashAddr);
}

JNIEXPORT jstring JNICALL
Java_com_breadwallet_tools_crypto_CashAddr_BRCashAddrDecode(
        JNIEnv *env,
        jclass type,
        jstring cashAddr_) {
    const char *cashAddr = (*env)->GetStringUTFChars(env, cashAddr_, 0);

    char legacyAddr[36];
    const size_t size = BRCashAddrDecode(legacyAddr, cashAddr);

    (*env)->ReleaseStringUTFChars(env, cashAddr_, cashAddr);

    if (size == 0) return NULL;
    return (*env)->NewStringUTF(env, legacyAddr);
}

JNIEXPORT jboolean JNICALL
Java_com_breadwallet_tools_crypto_CashAddr_BRCashAddrValidate(
        JNIEnv *env,
        jclass type,
        jstring cashAddr_) {
    const char *cashAddr = (*env)->GetStringUTFChars(env, cashAddr_, 0);

    int result = BRCashAddrValidate(cashAddr);

    (*env)->ReleaseStringUTFChars(env, cashAddr_, cashAddr);

    return (jboolean) (result ? JNI_TRUE : JNI_FALSE);
}