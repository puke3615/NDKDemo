//
// Created by dell on 2016/1/20.
//

#include "pk_ndk_Holder.h"

JNIEXPORT jstring JNICALL Java_pk_ndk_Holder_print
        (JNIEnv *env, jobject obj) {
    return (*env)->NewStringUTF(env, "print");
}

JNIEXPORT jint JNICALL Java_pk_ndk_Holder_plus
        (JNIEnv *env, jobject obj, jint a, jint b) {
    return a + b;
}

/**
 * JNI的方法签名注意：
 * 如果是类的话，签名为【L类路径;】
 * 例：String --> Ljava/lang/String;
 *    Map --> Ljava/util/Map;
 *    注解后面的分号要加上！
 */
JNIEXPORT jint JNICALL Java_pk_ndk_Holder_testCallJava
        (JNIEnv *env, jobject obj) {
    //获取class
    jclass cls = (*env)->FindClass(env, "pk/ndk/Holder");
    //获取构造方法
    jmethodID initMethodId = (*env)->GetMethodID(env, cls, "<init>", "()V");
    //通过构造方法新建一个对象
    jobject entity = (*env)->NewObject(env, cls, initMethodId);

    //获取静态属性Id
    jfieldID numberFieldId = (*env)->GetStaticFieldID(env, cls, "number", "I");
    //取出该静态属性的值
    jint number = (*env)->GetStaticIntField(env, cls, numberFieldId);
    //在现有值的基础上加1
    (*env)->SetStaticIntField(env, cls, numberFieldId, number + 1);

    //获取方法Id
    jmethodID addNumberMethodId = (*env)->GetMethodID(env, cls, "addNumber", "(I)V");
    //执行该方法
    (*env)->CallVoidMethod(env, entity, addNumberMethodId, 10);

    //获取静态方法Id
    jmethodID addStaticNumberMethodId = (*env)->GetStaticMethodID(env, cls, "addStaticNumber", "(ILpk/ndk/Holder;)V");
    //执行该方法
    (*env)->CallStaticVoidMethod(env, cls, addStaticNumberMethodId, 4, entity);

    //获取show()方法ID
    jmethodID showMethodId = (*env)->GetMethodID(env, cls, "show", "()V");
    //调用新建对象的show()方法
    (*env)->CallVoidMethod(env, entity, showMethodId);

    return -1;
}

JNIEXPORT jlong JNICALL Java_pk_ndk_Holder_C_1sub
        (JNIEnv *env, jclass cls, jlong max) {
    jlong sum = 0;
    int i;
    for (i = 0; i < max; i ++) {
        sum += i;
    }
    return sum;
}
