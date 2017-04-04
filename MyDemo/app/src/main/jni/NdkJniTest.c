#include "com_example_xy_jni_NdkJniUtil.h"

/*
 * Class:     com_example_xy_jni_NdkJniUtil
 * Method:    getCLanguageString
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_xy_jni_NdkJniUtil_getCLanguageString
        (JNIEnv *env, jobject obj) {
//    printf("This is jni from xy");
    return (*env->)NewStringUTF(env, "You get c language string: xy");
}
