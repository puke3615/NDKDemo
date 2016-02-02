//
// Created by dell on 2016/1/22.
//

#include "pk_ndk_Holder.h"

JNIEXPORT jlong JNICALL Java_pk_ndk_Holder_CC_1sub
        (JNIEnv *env, jclass cls, jlong max) {
    jlong sum = 0;
    for (int i = 0; i < max; i++) {
        sum += i;
    }
    return sum;
}